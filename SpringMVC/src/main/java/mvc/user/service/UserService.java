package mvc.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.user.dao.BaseDataDao;
import mvc.user.dao.UserDao;
import mvc.user.model.dto.UserDto;
import mvc.user.model.po.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseDataDao baseDataDao;
	
	public List<User> findUsers() {
		return userDao.findAllUsers();
	}

	public List<UserDto> findUserDtos() {
		List<UserDto> userDtos = new ArrayList<>();
		// PO
		List<User> users = findUsers();
		// PO 轉 DTO
		
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setAge(user.getAge());
			userDto.setBirth(user.getBirth());
			userDto.setResume(user.getResume());
			userDto.setEducation(baseDataDao.getEducationById(user.getId()));
			userDto.setGender(baseDataDao.getGenderById(user.getId()));
			userDto.setInterestIds(baseDataDao.findAllInterestsByUserId(user.getId()));
			
			userDtos.add(userDto);
		}
		
		return userDtos;
	}
	
	public User getUser(Integer userId) {
		return userDao.getUserById(userId);
	}

	public Boolean addUser(User user) {
		return userDao.addUser(user) > 0;
	}

	public Boolean updateUser(Integer userId, User user) {
		return userDao.updateUser(userId, user) > 0;
	}

	public Boolean deleteUser(Integer userId) {
		return userDao.deleteUser(userId) > 0;
	}
}
