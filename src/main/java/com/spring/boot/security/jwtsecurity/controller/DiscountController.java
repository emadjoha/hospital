package com.spring.boot.security.jwtsecurity.controller;

import com.spring.boot.security.jwtsecurity.exceptions.NotFoundException;
import com.spring.boot.security.jwtsecurity.model.Discount;
import com.spring.boot.security.jwtsecurity.model.User;
import com.spring.boot.security.jwtsecurity.repository.DiscountRepository;
import com.spring.boot.security.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/hospital/v1.1")
public class DiscountController {


    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/discounts")
    public List<Discount> getAllDiscounts(){
        return discountRepository.findAll();
    }
    @GetMapping("/users/{userId}/discounts")
    public List<Discount> getAllDiscountsByUserId(@PathVariable(value = "userId") Long userId){
        if (!userRepository.existsById(userId)){
            throw  new NotFoundException("UserId " + userId + " not found");
        }
        return discountRepository.findAllDiscountsByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{userId}/discounts/{discountId}")
    public Discount show(@PathVariable("userId") Long userId,@PathVariable("discountId")Long discountId)
    {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("UserId " + userId + " not found");
        }
        return discountRepository.findById(discountId).orElseThrow(() -> new NotFoundException("DiscountId " + discountId + " not found"));
    }
    @PostMapping("/users/{userId}/discounts")
    public User create(@PathVariable(value = "userId")Long userId ,@RequestBody Discount discount){
        return userRepository.findById(userId).map(user -> {
            user.getDiscounts().add(discount);
            return userRepository.save(user);
        }).orElseThrow(()->new NotFoundException("userId "+userId+" is not found"));
    }

    @PutMapping("/users/{userId}/discounts/{discountId}")
    public Discount update(@PathVariable("userId") Long userId,@PathVariable("discountId") Long discountId,@RequestBody Discount discountRequest)
    {
        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("userId " + userId + " not found");
        }
        return discountRepository.findById(discountId).map(discount -> {
            discount.setValue(discountRequest.getValue());
            discount.setCreatedAt(new Date());
            return discountRepository.save(discount);
        }).orElseThrow(() -> new NotFoundException("DiscountId " + discountId + " not found"));
    }

    @DeleteMapping("/users/{userId}/discounts/{discountId}")
    public ResponseEntity<?> delete(@PathVariable (value = "userId") Long userId,
                                    @PathVariable (value = "discountId") Long discountId) {
        return discountRepository.findAllDiscountsByUserIdAndDiscountId(userId, discountId).map(discount -> {
            discountRepository.delete(discount);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("User not found with id " + userId + " and discountId " + discountId));
    }

}
