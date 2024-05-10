package session05.bean;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBook {
	private Integer id;
	private String userName;
	private String content;
	private Date createDate;
	private Date updateDate;
	
	public GuestBook(String userName, String content) {
		this.userName = userName;
		this.content = content;
	}
}
