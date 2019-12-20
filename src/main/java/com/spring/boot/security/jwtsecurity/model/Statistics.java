package com.spring.boot.security.jwtsecurity.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statistics",schema = "dfd6ia5jmgjmm")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt = new Date();
    @Column(name = "top_external")
    private String topExternal;
    @Column(name = "top_internal")
    private String topInternal;
    @Column(name = "top_discount")
    private String topDiscount;

    public Statistics(String topExternal, String topInternal, String topDiscount) {
        this.topExternal = topExternal;
        this.topInternal = topInternal;
        this.topDiscount = topDiscount;
    }

    public Statistics() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTopExternal() {
        return topExternal;
    }

    public void setTopExternal(String topExternal) {
        this.topExternal = topExternal;
    }

    public String getTopInternal() {
        return topInternal;
    }

    public void setTopInternal(String topInternal) {
        this.topInternal = topInternal;
    }

    public String getTopDiscount() {
        return topDiscount;
    }

    public void setTopDiscount(String topDiscount) {
        this.topDiscount = topDiscount;
    }
}
