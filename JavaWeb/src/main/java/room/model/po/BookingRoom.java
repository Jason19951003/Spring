package room.model.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRoom {
	private Integer bookingId;
	private Integer roomId;
	private Integer userId;
	private String checkinDate;
	private Date createTime;
}
