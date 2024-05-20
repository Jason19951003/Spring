package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.model.po.User;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MeetingRoom> findAllRooms() {
		String sql = "select roomId, roomName, roomSize from meetingroom";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MeetingRoom.class));
	}

	@Override
	public Optional<MeetingRoom> getRoom(Integer roomId) {
		String sql = "select roomId, roomName, roomSize from meetingroom where roomId = ?";
		try {
			MeetingRoom room = jdbcTemplate.queryForObject(sql, 
					new BeanPropertyRowMapper<>(MeetingRoom.class), roomId);

			return Optional.of(room);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.of(null);
	}

	@Override
	public Integer addRoom(MeetingRoom room) {
		String sql = "insert into MeetingRoom (roomId, roomName, roomSize) values(?,?,?)";
		int rowCount = jdbcTemplate.update(sql, room.getRoomId(), room.getRoomName(), room.getRoomSize());
		return rowCount;
	}

	@Override
	public Integer addBookRoom(BookingMeetingRoom bookingMeetingRoom) {
		String sql = "insert into BookingMeetingRoom(roomId, userId, bookingDate) values(?,?,?)";
		int rowCount = jdbcTemplate.update(sql, bookingMeetingRoom.getRoomId(), 
				bookingMeetingRoom.getUserId(), bookingMeetingRoom.getBookingDate());
		return rowCount;
	}

	@Override
	public Integer cancelBooking(Integer bookingId) {
		String sql = "delete from BookingMeetingRoom where bookingId = ?";
		int rowCount = jdbcTemplate.update(sql, bookingId);
		return rowCount;
	}

	@Override
	public List<BookingMeetingRoomDto> findAllBookingMeetingRooms() {
		String sql = "SELECT "
				+ "	b.bookingId,b.roomId, b.userId, b.bookingDate, b.createDate, "
				+ "    r.roomId, r.roomName, r.roomSize, "
				+ "    u.id, u.name "
				+ "FROM  "
				+ "BookingMeetingRoom b "
				+ "LEFT JOIN MeetingRoom r ON r.roomId = b.roomId "
				+ "LEFT JOIN user u on b.userId =u.id";
		// 自定義對應邏輯與規則
		RowMapper<BookingMeetingRoomDto> mapper = new RowMapper<>() {
			
			@Override
			public BookingMeetingRoomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer bookingId = rs.getInt("bookingId");
				Integer roomId = rs.getInt("roomId");
				Integer userId = rs.getInt("userId");
				String bookingDate = rs.getString("bookingDate");
				Timestamp createDate = rs.getTimestamp("createDate");
				String roomName = rs.getString("roomName");
				Integer roomSize = rs.getInt("roomSize");
				String name = rs.getString("name"); // userName
				
				MeetingRoom room = new MeetingRoom(roomId, roomName, roomSize);
				User user = new User(userId, name);
				// 注入資料
				BookingMeetingRoomDto dto = new BookingMeetingRoomDto();
				dto.setBookingId(bookingId);
				dto.setRoomId(roomId);
				dto.setUserId(userId);
				dto.setBookingDate(bookingDate);
				dto.setCreateDate(createDate);
				dto.setMeetingRoom(room);
				dto.setUser(user);
				return dto;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public Integer updateBookingUserId(Integer bookingId, Integer userId) {
		String sql = "update BookingMeetingRoom set userId=? where bookingId=?";
		int rowCount = jdbcTemplate.update(sql, userId, bookingId);
		return rowCount;
	}
	
}
