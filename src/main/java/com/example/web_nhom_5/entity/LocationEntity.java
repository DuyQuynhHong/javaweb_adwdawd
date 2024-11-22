package com.example.web_nhom_5.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
public class LocationEntity {
    @Id
    @Column(name = "location_code", nullable = false, unique = true)
    private String locationCode;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // tranh loi vong lap vo han json, giup lien ket giua cac thuc the.
    private List<RoomEntity> rooms;

}
