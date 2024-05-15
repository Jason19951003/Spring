package mvc.user.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import mvc.user.model.po.Education;
import mvc.user.model.po.Gender;
import mvc.user.model.po.Interest;

// 呈現給前端的資料
@Data
public class UserDto {
	private Integer id; // 序號
	private String name; // 姓名
	private Integer age; // 年齡
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式
	private Date birth; // 生日
	private String resume; // 履歷
	private Education education; // 教育程度
	private Gender gender; // 性別
	private List<Interest> interestIds; // 興趣列表
	
	// 顯示所有興趣名稱JSTL使用
	public String getInterestNames() {
		if (interestIds != null) {
			return interestIds.stream().map(Interest::getName).collect(Collectors.joining(" "));
		}
		return "";
	}
}
