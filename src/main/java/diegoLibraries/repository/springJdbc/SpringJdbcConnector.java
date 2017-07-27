package diegoLibraries.repository.springJdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SpringJdbcConnector {
	
	public static JdbcTemplate getConnection(String url,String user,String pwd) {
		return getConnection("com.mysql.cj.jdbc.Driver", url,user,  pwd);
	}
	public static JdbcTemplate getConnection(String driverClassName,String url,String user,String pwd) {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
		return new JdbcTemplate(dataSource);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String driver="com.mysql.cj.jdbc.Driver";
		//the '&' in xml config is '&amp;'
		String url="jdbc:mysql://localhost:3306/ride_tracker?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="ride_tracker_user";
		String pwd="ZAPDLNSMrMh6wEpK";
		JdbcTemplate jdbc=getConnection(driver, url, user, pwd);
	}
}
