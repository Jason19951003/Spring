package mvc.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mvc.user.model.po.Statistics;
import mvc.user.model.po.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private BaseDataDao baseDataDao;

	@Override
	public int addUser(User user) {
		String sql = "insert into user(name, age, birth, resume, education_id, gender_id) values(:name, :age, :birth, :resume, :educationId, :genderId)";
		// return jdbcTemplate.update(sql, user.getName(), user.getAge(),
		// user.getBirth(), user.getResume(), user.getEducationId(),
		// user.getGenderId());

		// 自動將 user 物件的屬性值給 sql 參數(?)使用
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);

		// KeyHolder
		KeyHolder keyHolder = new GeneratedKeyHolder();

		// keyHolder, new String[] {"id"} 將主鍵欄位 id 所自動生成的序號放到 keyHolder 中
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "id" });

		int userId = keyHolder.getKey().intValue(); // 最新新增紀錄的 user id

		// 新增該 user 的興趣紀錄
		for (Integer interestId : user.getInterestIds()) {
			baseDataDao.addInterest(userId, interestId);
		}

		return userId;
	}

	@Override
	public int updateUser(Integer userId, User user) {
		// 更新使用者(table: user)
		String sql = "update user set name=?, age=?, birth=?, resume=?, education_id=?, gender_id=? where id=?";
		int rowcount = jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getBirth(), user.getResume(),
				user.getEducationId(), user.getGenderId(), userId);
		// 更新使用者的興趣(table:user_interest)
		// 1. 先刪除該使用者的興趣
		baseDataDao.deleteInterestsByUserId(userId);
		// 2. 再新增使用者的興趣
		// 新增該 user 的興趣紀錄
		for (Integer interestId : user.getInterestIds()) {
			baseDataDao.addInterest(userId, interestId);
		}
		return rowcount;
	}

	@Override
	public int deleteUser(Integer userId) {
		// 1. 先刪除該使用者的興趣
		baseDataDao.deleteInterestsByUserId(userId);
		// 2. 再刪除該使用者
		String sql = "delete from user where id=?";
		return jdbcTemplate.update(sql, userId);
	}

	@Override
	public User getUserById(Integer userId) {
		String sql = "select id, name, age, birth, resume, education_id, gender_id from user where id=?";
		User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
		if (user != null)
			user.setInterestIds(getUserInterestIds(userId));
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		String sql = "select id, name, age, birth, resume, education_id, gender_id from user";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		users.forEach(user-> user.setInterestIds(getUserInterestIds(user.getId())));
		return users;
	}
	
	private Integer[] getUserInterestIds(Integer userId) {
		String interest_sql = "select user_id, interest_id from user_interest where user_id = ?";
		List<Map<String, Object>> interestList = jdbcTemplate.queryForList(interest_sql, userId);
		Integer[] interestIds = interestList.stream()
					.map(data->(Integer)data.get("interest_id"))
					.toArray(Integer[]::new);
		return interestIds;
	}

	@Override
	public List<Statistics> queryGenderStatisticst() {
		String sql = "SELECT a.gender_id AS id, b.item_name AS name, COUNT(*) AS count "
				+ "FROM user a, base_data b "
				+ "WHERE a.gender_id = b.item_id AND b.group_name = 'Gender' "
				+ "GROUP BY a.gender_id , b.item_name";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Statistics.class));
	}

	@Override
	public List<Statistics> queryEducationStatisticst() {
		String sql = "SELECT a.education_id AS id, b.item_name AS name, COUNT(*) AS count "
				+ "FROM user a, base_data b "
				+ "WHERE a.education_id = b.item_id AND b.group_name = 'Education' "
				+ "GROUP BY a.education_id , b.item_name";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Statistics.class));
	}

}
