package mvc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mvc.user.dao.BaseDataDao;
import mvc.user.model.dto.UserDto;
import mvc.user.model.po.Education;
import mvc.user.model.po.Interest;
import mvc.user.model.po.User;
import mvc.user.model.response.ApiResponse;
import mvc.user.service.UserService;

/**
 * 定義 URI 服務
 * ---------------------------------------------------------------------------------
 * Method | URI          | Description
 * ---------------------------------------------------------------------------------
 * GET    | /rest/user   | 取得所有使用者 json 資料
 * GET    | /rest/user/1 | 根據 userId 取得單筆使用者 json 資料
 * POST   | /rest/user   | 新增使用者資料, 會自動夾帶 User 的 json 物件資料上來
 * PUT    | /rest/user/1 | 修改指定 userId 的使用者資料, 會自動夾帶要修改的 User 的 json 物件資料上來
 * DELETE | /rest/user/1 | 刪除指定 userId 的使用者紀錄
 * ---------------------------------------------------------------------------------
 * URL 範例: GET http://localhost:8080/SpringMVC/mvc/rest/user
 */


@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Autowired
	private UserService userService;

	@Autowired
	private BaseDataDao baseDataDao;
	
	Gson gson = new Gson();
	
	@GetMapping("/educations")
	public ResponseEntity<ApiResponse<List<Education>>> queryEducations() {
		List<Education> educations = baseDataDao.findAllEducations();
		ApiResponse apiResponse = new ApiResponse<>(true, "query education success", educations);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/interests")
	public ResponseEntity<ApiResponse<List<Interest>>> queryInterests() {
		List<Interest> interests = baseDataDao.findAllInterests();
		ApiResponse apiResponse = new ApiResponse<>(true, "query interest success", interests);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<UserDto>>> queryAllUsers() {
		List<UserDto> users = userService.findUserDtos();
		ApiResponse apiResponse = new ApiResponse<>(true, "success", users);
		// 回傳JSON 字串
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> getUser(@PathVariable("id") Integer id) {
		try {
			User user = userService.getUser(id);
			ApiResponse apiResponse = new ApiResponse<>(true, "success", user);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			ApiResponse apiResponse = new ApiResponse<>(false, e.getMessage(), null);
			return ResponseEntity.ok(apiResponse);
		}
		
	}
	
	
	// 新增紀錄
	@PostMapping
	public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user) {
		// 將 userJsonString 轉 User 物件
		Integer userId = userService.addUserAndGetId(user);
		if (userId != null) {
			user.setId(userId);
			ApiResponse apiResponse = new ApiResponse<>(true, "add success", user);
			return  ResponseEntity.ok(apiResponse);
		}
		ApiResponse apiResponse = new ApiResponse<>(false, "add fail", user);
		return ResponseEntity.ok(apiResponse);
	}
	
	// 修改紀錄 PUT
	@PutMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
		Boolean state =userService.updateUser(userId, user);
		user.setId(userId);
		String message = state ? "update success" : "update fail";
		ApiResponse apiResponse = new ApiResponse<>(state, message, user);
		return ResponseEntity.ok(apiResponse);
	}
	
	// 刪除紀錄 Delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable("userId") Integer userId) {
		User user = null;
		try {
			user = userService.getUser(userId);
			Boolean state = userService.deleteUser(userId);
			String message = state ? "delete success" : "delete fail";
			ApiResponse apiResponse = new ApiResponse<>(state, message, user);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse<>(false, e.toString(), user);
			return ResponseEntity.ok(apiResponse);
		}
	}
}
