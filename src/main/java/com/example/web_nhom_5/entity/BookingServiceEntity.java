package com.example.web_nhom_5.entity;

import com.example.web_nhom_5.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "booking_service")
@Data
public class BookingServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "is_paid", nullable = false)
    private boolean paid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Thêm thuộc tính này

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_code", nullable = false)
    @JsonBackReference
    private ServiceEntity service;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Thiết lập createdAt trước khi lưu vào DB
        this.paid = false;
        this.status = BookingStatus.PENDING;
    }
}
