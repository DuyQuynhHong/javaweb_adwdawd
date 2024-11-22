package com.example.web_nhom_5.service.implement;

import com.example.web_nhom_5.conventer.BookingServiceMapper;
import com.example.web_nhom_5.conventer.PaymentMapper;
import com.example.web_nhom_5.dto.request.BookingServiceCreateRequestDTO;
import com.example.web_nhom_5.dto.response.BookingServiceResponse;
import com.example.web_nhom_5.dto.response.ProcessPaymentResponse;
import com.example.web_nhom_5.entity.BookingRoomEntity;
import com.example.web_nhom_5.entity.BookingServiceEntity;
import com.example.web_nhom_5.entity.ServiceEntity;
import com.example.web_nhom_5.entity.UserEntity;
import com.example.web_nhom_5.enums.BookingStatus;
import com.example.web_nhom_5.exception.ErrorCode;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.repository.BookingServiceRepository;
import com.example.web_nhom_5.repository.ServiceRepository;
import com.example.web_nhom_5.repository.UserRepository;
import com.example.web_nhom_5.service.BookingServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingServiceServiceImpl implements BookingServiceService {

    @Autowired
    private BookingServiceRepository bookingServiceRepository;

    @Autowired
    private BookingServiceMapper bookingServiceMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public BookingServiceResponse addBookingService(BookingServiceCreateRequestDTO bookingServiceCreateRequestDTO) {
        BookingServiceEntity bookingServiceEntity = bookingServiceMapper.bookingServiceCreateToBookingServiceEntity(bookingServiceCreateRequestDTO);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(name).orElseThrow(()
                -> new WebException(ErrorCode.USER_NOT_EXISTED) );

//        UserEntity userEntity = userRepository.findById(bookingServiceCreateRequestDTO.getUserId())
//                .orElseThrow(() -> new WebException(ErrorCode.USER_NOT_EXISTED));
        ServiceEntity serviceEntity = serviceRepository.findById(bookingServiceCreateRequestDTO.getServiceCodeName())
                .orElseThrow(() -> new WebException(ErrorCode.SERVICE_NOT_FOUND));

        bookingServiceEntity.setService(serviceEntity);
        bookingServiceEntity.setUser(userEntity);

        userEntity.getBookingServices().add(bookingServiceEntity);
        serviceEntity.getBookingServices().add(bookingServiceEntity);

        bookingServiceEntity.setTotalPrice(serviceEntity.getServicePrice());
        BookingServiceEntity savedEntity = bookingServiceRepository.save(bookingServiceEntity);
        return bookingServiceMapper.bookingServiceEntityToBookingServiceResponse(savedEntity);
    }


    @Override
    public List<BookingServiceResponse> getAllBookingServiceList() {
        return bookingServiceRepository.findAll()
                .stream()
                .map(bookingServiceMapper::bookingServiceEntityToBookingServiceResponse)
                .toList();
    }

    @Override
    public BookingServiceEntity getBookingServiceById(long bookingId) {
        return bookingServiceRepository.findById(bookingId).orElseThrow(() -> new WebException(ErrorCode.BOOKING_NOT_FOUND));
    }

    @Override
    public List<BookingServiceResponse> getAllBookingServiceByStatus(BookingStatus status) {
        return bookingServiceRepository.findAllByStatus(status)
                .stream()
                .map(bookingServiceMapper::bookingServiceEntityToBookingServiceResponse)
                .toList();
    }

    @Override
    public BookingStatus getBookingStatusById(long bookingId) {
        BookingServiceEntity bookingServiceEntity = getBookingServiceById(bookingId);
        return bookingServiceEntity.getStatus();
    }

    @Override
    public BookingStatus updateBookingStatusById(long bookingId, BookingStatus newBookingStatus) {
        BookingServiceEntity bookingServiceEntity = getBookingServiceById(bookingId);
        if(newBookingStatus.equals(BookingStatus.CANCELLED) && bookingServiceEntity.isPaid()) {
            throw new WebException(ErrorCode.CANNOT_CANCEL_PAID_BOOKING);
        }
        if(newBookingStatus.equals(BookingStatus.CONFIRMED) && !bookingServiceEntity.isPaid()) {
            throw new WebException(ErrorCode.BOOKING_IS_NOT_PAID);
        }
        if(bookingServiceEntity.getStatus().equals(BookingStatus.COMPLETED) || bookingServiceEntity.getStatus().equals(BookingStatus.CANCELLED)) {
            throw new WebException(ErrorCode.DO_NOT_CHANGE_THIS);
        }

        bookingServiceEntity.setStatus(newBookingStatus);
        bookingServiceRepository.save(bookingServiceEntity);
        return bookingServiceEntity.getStatus();
    }

    @Override
    public void deleteBookingServiceById(long bookingId) {
        bookingServiceRepository.deleteById(bookingId);
    }


    @Override
    public List<BookingServiceResponse> getAllBookingServicesByServiceId(String codeName) {
        return bookingServiceRepository.findAllByService_CodeName(codeName)
                .stream()
                .map(bookingServiceMapper::bookingServiceEntityToBookingServiceResponse)
                .toList();
    }

    @Override
    public List<BookingServiceResponse> getAllBookingServicesByPaid(boolean paid) {
        return bookingServiceRepository.findAllByPaid(paid)
                .stream()
                .filter(bookingServiceEntity -> !bookingServiceEntity.getStatus().equals(BookingStatus.CANCELLED))
                .map(bookingServiceMapper::bookingServiceEntityToBookingServiceResponse)
                .toList();
    }

    @Override
    public List<BookingServiceResponse> getAllBookingServicesByUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new WebException(ErrorCode.USER_NOT_EXISTED));
        return bookingServiceRepository.findAllByUser_Id(userEntity.getId())
                .stream()
                .map(bookingServiceMapper::bookingServiceEntityToBookingServiceResponse)
                .toList();
    }

    @Override
    @Scheduled(fixedRate = 120000)
    public void cancelExpiredBooking() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusMinutes(10);
        List<BookingServiceEntity> expiredBookings = bookingServiceRepository.findAllByStatusAndCreatedAtBeforeAndPaid(BookingStatus.PENDING, oneHourAgo, false);
        expiredBookings.forEach(booking -> booking.setStatus(BookingStatus.CANCELLED));
        bookingServiceRepository.saveAll(expiredBookings);
        System.out.println("Canceled " + expiredBookings.size() + " expired bookings");
    }

    @Override
    public ProcessPaymentResponse processPayment(long bookingId, long amount) {
        BookingServiceEntity bookingServiceEntity = getBookingServiceById(bookingId);
        if (bookingServiceEntity.isPaid() || bookingServiceEntity.getStatus() == BookingStatus.COMPLETED || bookingServiceEntity.getStatus() == BookingStatus.CONFIRMED) {
            throw new WebException(ErrorCode.BOOKING_IS_PAID);
        }
        if (bookingServiceEntity.getStatus() == BookingStatus.CANCELLED) {
            throw new WebException(ErrorCode.BOOKING_HAS_BEEN_CANCELED);
        }
        if (amount <= 0 || amount < bookingServiceEntity.getTotalPrice()) {
            throw new WebException(ErrorCode.INVALID_NUM);
        }

        bookingServiceEntity.setPaid(true);
        bookingServiceRepository.save(bookingServiceEntity);
        ProcessPaymentResponse processPaymentResponse = paymentMapper.bookingServiceToPaymentResponse(bookingServiceEntity);
        processPaymentResponse.setAmount(bookingServiceEntity.getTotalPrice());
        processPaymentResponse.setSuccess(true);
        processPaymentResponse.setCashBack(amount - processPaymentResponse.getAmount());
        return processPaymentResponse;
    }
}
