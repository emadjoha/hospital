package com.spring.boot.security.jwtsecurity.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prize")
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type_prize")
    private String typePrize;
    @Column(name = "value_prize")
    private Long valuePrize;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt = new Date();


    public Prize(String typePrize, Long valuePrize) {
        this.typePrize = typePrize;
        this.valuePrize = valuePrize;
    }

    public Prize() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypePrize() {
        return typePrize;
    }

    public void setTypePrize(String typePrize) {
        this.typePrize = typePrize;
    }

    public Long getValuePrize() {
        return valuePrize;
    }

    public void setValuePrize(Long valuePrize) {
        this.valuePrize = valuePrize;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
