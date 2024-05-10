package room.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import room.model.dto.BookingRoomCount;
import room.model.po.BookingRoom;

public class BookingRoomDaoImpl implements BookingRoomDao {
	
	private JdbcTemplate jdbcTemplate = JdbcTemplateBuild.getInstance();
	
	@Override
	public BookingRoom findById(Integer bookingId) {
		String sql = "select booking_id, room_id, user_id, checkin_date, create_time from booking_room where booking_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookingRoom.class), bookingId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BookingRoom> findAll() {
		String sql = "select booking_id, room_id, user_id, checkin_date, create_time from booking_room";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookingRoom.class));
	}

	@Override
	public List<BookingRoom> findByUserId(Integer userId) {
		String sql = "select booking_id, room_id, user_id, checkin_date, create_time where user_id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookingRoom.class), userId);
	}

	@Override
	public int create(BookingRoom bookingRoom) {
		String sql = "insert into booking_room (room_id, user_id, checkin_date) values(?,?,?)";
		return jdbcTemplate.update(sql, bookingRoom.getRoomId(), bookingRoom.getUserId(), bookingRoom.getCheckinDate());
	}

	@Override
	public int update(Integer bookingId, BookingRoom bookingRoom) {
		String sql = "update booking_room set room_id = ?, user_id = ?, checkin_date = ? where booking_id = ?";
		return jdbcTemplate.update(sql, bookingRoom.getRoomId(), bookingRoom.getUserId(), bookingRoom.getCheckinDate(), bookingId);
	}

	@Override
	public int delete(Integer bookingId) {
		String sql = "delete from booking_room where booking_id = ?";
		return jdbcTemplate.update(sql, bookingId);
	}

	@Override
	public List<BookingRoomCount> getBookingRoomCounts() {
		String sql = "SELECT r.room_id,"
				+ "       r.room_name,"
				+ "       COALESCE(COUNT(b.booking_id), 0) AS booking_count "
				+ "FROM room r "
				+ "LEFT JOIN booking_room b ON r.room_id = b.room_id "
				+ "GROUP BY r.room_id, r.room_name";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(BookingRoomCount.class));
	}

}
