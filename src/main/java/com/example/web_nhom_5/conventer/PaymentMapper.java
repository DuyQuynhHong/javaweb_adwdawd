package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.response.ProcessPaymentResponse;
import com.example.web_nhom_5.entity.BookingRoomEntity;
import com.example.web_nhom_5.entity.BookingServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {
    @Mapping(source = "id", target = "bookingId")
    @Mapping(source = "user.userName", target = "userName")
    ProcessPaymentResponse bookingRoomToPaymentResponse(BookingRoomEntity bookingRoomEntity);

    @Mapping(source = "id", target = "bookingId")
    @Mapping(source = "user.userName", target = "userName")
    ProcessPaymentResponse bookingServiceToPaymentResponse(BookingServiceEntity bookingServiceEntity);
}
