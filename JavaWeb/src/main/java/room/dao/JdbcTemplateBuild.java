package room.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// 利用單例模式(singleton) 建立JDBCTemplate 物件
public class JdbcTemplateBuild {
	private static JdbcTemplate jdbcTemplate;
	
	static {
		init();
	}
	
	private static void init() {
		String driveName = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/web?serverTimezone=Asia/Taipei";
		String username = "root";
		String password = "abc123";
		
		//資料源設定
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driveName);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		// 將資料源設定給 jdbcTemplate
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplateBuild() {
		
	}
	
	public static JdbcTemplate getInstance() {
		if (jdbcTemplate == null) init();
		return jdbcTemplate;
	}
}
