package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.model.po.User;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private UserDao userDao;
	
	public List<MeetingRoom> findAllRooms() {
		return bookingDao.findAllRooms();
	}

	
	public Optional<MeetingRoom> getRoom(Integer id) {
		return bookingDao.getRoom(id);
	}

	
	public Integer addRoom(Integer roomId,String roomName, Integer roomSize) {
		MeetingRoom room = new MeetingRoom(roomId, roomName, roomSize);
		return bookingDao.addRoom(room);
	}

	
	public Integer addBookRoom(Integer roomId, Integer userId, String bookingDate) {
		BookingMeetingRoom bookingMeetingRoom = new BookingMeetingRoom();
		bookingMeetingRoom.setRoomId(roomId);
		bookingMeetingRoom.setUserId(userId);
		bookingMeetingRoom.setBookingDate(bookingDate);
		return bookingDao.addBookRoom(bookingMeetingRoom);
	}

	
	public Integer cancelBooking(Integer bookingId) {
		return bookingDao.cancelBooking(bookingId);
	}

	
	public List<BookingMeetingRoomDto> findAllBookingMeetingRooms() {
		return bookingDao.findAllBookingMeetingRooms();
	}

	
	public Integer updateBookingUserId(Integer bookingId, Integer userId) {
		return bookingDao.updateBookingUserId(bookingId, userId);
	}
	
	
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	
	public Optional<User> getUser(Integer id) {
		return userDao.getUser(id);
	}
	
	

}
