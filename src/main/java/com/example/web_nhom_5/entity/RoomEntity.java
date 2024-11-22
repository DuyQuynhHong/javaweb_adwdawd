package com.example.web_nhom_5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "room_price", nullable = false)
    private long roomPrice;

    @Column(name = "room_detail", nullable = false)
    private String roomDetail;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference // tranh loi vong lap vo han json, lien ket voi location.
    private LocationEntity location;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookingRoomEntity> bookingRooms;

}
