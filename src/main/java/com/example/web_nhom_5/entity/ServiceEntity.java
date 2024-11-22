package com.example.web_nhom_5.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
public class ServiceEntity {
    @Id
    @Column(name = "service_code", nullable = false, unique = true)
    private String codeName;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_price", nullable = false)
    private long servicePrice;

    @Column(name = "service_detail", nullable = false)
    private String serviceDetail;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookingServiceEntity> bookingServices;
}
