package com.example.web_nhom_5.service.implement;

import com.example.web_nhom_5.conventer.RoomMapper;
import com.example.web_nhom_5.dto.request.RoomCreateRequestDTO;
import com.example.web_nhom_5.dto.request.RoomUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.entity.LocationEntity;
import com.example.web_nhom_5.entity.RoomEntity;
import com.example.web_nhom_5.exception.ErrorCode;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.repository.LocationRepository;
import com.example.web_nhom_5.repository.RoomRepository;
import com.example.web_nhom_5.service.LocationService;
import com.example.web_nhom_5.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public RoomResponse addRoom(RoomCreateRequestDTO room) {

        RoomEntity roomEntity = roomMapper.roomCreateDtoToEntity(room);

        LocationEntity locationEntity = locationRepository.findById(room.getLocationCode())
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        roomEntity.setLocation(locationEntity);

        locationEntity.getRooms().add(roomEntity); // Mặc dù bạn gọi getRooms() để lấy danh sách các phòng, nhưng khi bạn thêm một roomEntity vào danh sách này, danh sách rooms trong locationEntity đã thay đổi.
        // Vì Hibernate quản lý thực thể locationEntity, mọi thay đổi trong danh sách này sẽ được theo dõi và đồng bộ hóa.

        return roomMapper.roomEntityToRoomResponse(roomRepository.save(roomEntity));
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(roomMapper::roomEntityToRoomResponse).toList();
    }

    @Override
    public RoomResponse updateRoom(RoomUpdateRequestDTO room, Long roomId) {
        // Lấy RoomEntity hiện tại từ roomId
        RoomEntity roomEntity = getRoomById(roomId);

        // Lấy LocationEntity cũ và xóa phòng khỏi danh sách rooms trong location
        LocationEntity oldLocationEntity = locationRepository.findById(roomEntity.getLocation().getLocationCode())
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        oldLocationEntity.getRooms().remove(roomEntity);

        // Cập nhật thông tin cho RoomEntity
        roomMapper.updateRoom(roomEntity, room);

        // Lấy LocationEntity mới
        LocationEntity newLocationEntity = locationRepository.findById(room.getLocationCode())
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        roomEntity.setLocation(newLocationEntity);

        // Thêm phòng vào danh sách rooms của LocationEntity mới
        newLocationEntity.getRooms().add(roomEntity);

        // Lưu lại RoomEntity
        return roomMapper.roomEntityToRoomResponse(roomRepository.save(roomEntity));
    }


    @Override
    public RoomEntity getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new WebException(ErrorCode.ROOM_NOT_FOUND));
    }

    @Override
    public void deleteRoom(Long roomId) {
        RoomEntity roomEntity = getRoomById(roomId);
        LocationEntity locationEntity = locationRepository.findById(roomEntity.getLocation().getLocationCode())
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        locationEntity.getRooms().remove(roomEntity);
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<RoomResponse> getAllRoomsByLocationCode(String locationCode) {
        List<RoomEntity> roomEntities = roomRepository.findAllByLocation_LocationCode(locationCode);
        return roomEntities.stream().map(roomMapper::roomEntityToRoomResponse).toList();
    }

    @Override
    public List<RoomResponse> getLimitedRooms() {
        List<RoomEntity> rooms = roomRepository.findAll(); // Lấy tất cả các phòng
        // Giới hạn chỉ lấy 4 phòng đầu tiên
        return rooms.stream().limit(4).map(roomMapper::roomEntityToRoomResponse).toList();
    }

}
