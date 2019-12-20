package com.spring.boot.security.jwtsecurity.controller;

import com.spring.boot.security.jwtsecurity.exceptions.NotFoundException;

import com.spring.boot.security.jwtsecurity.model.Discount;
import com.spring.boot.security.jwtsecurity.model.Prize;
import com.spring.boot.security.jwtsecurity.model.User;
import com.spring.boot.security.jwtsecurity.repository.PrizeRepository;
import com.spring.boot.security.jwtsecurity.repository.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/hospital/v1.1")
public class PrizeController {

    @Autowired
     PrizeRepository prizeRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/prizes")
    public List<Prize> getAllPrizes(){
        return prizeRepository.findAll();
    }
    @GetMapping("/users/{userId}/prizes")
    public List<Prize> getAllPrizesByUserId(@PathVariable(value = "userId") Long userId){
        if (!userRepository.existsById(userId)){
           throw  new NotFoundException("UserId " + userId + " not found");
        }
        return prizeRepository.findAllPrizesByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{userId}/prizes/{prizeId}")
    public Prize show(@PathVariable("userId") Long userId,@PathVariable("prizeId")Long prizeId)
    {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("UserId " + userId + " not found");
        }
        return prizeRepository.findById(prizeId).orElseThrow(() -> new NotFoundException("PrizeId " + prizeId + " not found"));
    }
    @PostMapping("/users/{userId}/prizes")
    public User create(@PathVariable(value = "userId")Long userId ,@RequestBody Prize prize){
        return userRepository.findById(userId).map(user -> {
            user.getPrizes().add(prize);
            return userRepository.save(user);
        }).orElseThrow(()->new NotFoundException("userId "+userId+" is not found"));
    }

    @PutMapping("/users/{userId}/prizes/{prizeId}")
    public Prize update(@PathVariable("userId") Long userId,@PathVariable("prizeId") Long prizeId,@RequestBody Prize prizeRequest)
    {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("userId " + userId + " not found");
        }
        return prizeRepository.findById(prizeId).map(prize -> {
            prize.setTypePrize(prizeRequest.getTypePrize());
            prize.setValuePrize(prizeRequest.getValuePrize());
            prize.setCreatedAt(new Date());
            return prizeRepository.save(prize);
        }).orElseThrow(() -> new NotFoundException("PrizeId " + prizeId + " not found"));
    }

    @DeleteMapping("/users/{userId}/prizes/{prizeId}")
    public ResponseEntity<?> delete(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "prizeId") Long prizeId) {
        return prizeRepository.findAllPrizesByUserIdAndPrizeId(userId, prizeId).map(prize -> {
            prizeRepository.delete(prize);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("User not found with id " + userId + " and prizeId " + prizeId));
    }
}
