package com.spring.boot.security.jwtsecurity.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "percent")
public class Percent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt = new Date();
    @Column(name = "percent_value")
    private Double percentValue;

    public Percent() {
    }

    public Percent(Double percentValue) {
        this.percentValue = percentValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentValue() {
        return percentValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setPercentValue(Double percentValue) {
        this.percentValue = percentValue;
    }
}
