package diegoLibraries.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utileria para poder realizar conexiones a bases de datos SQL.
 * @author Diego Olvera
 *
 */
public class JdbcConnector {
	 public static Connection getConnection(String url,String user,String pwd) {
		return getConnection("com.mysql.cj.jdbc.Driver", url,user,  pwd);
	 }
	/**
	 * Devuelve una conexión a la base de datos
	 * @param user Usuario.
	 * @param pass Contraseña.
	 * @param host Puerto.
	 * @param dataBaseName Nombre de la base de datos.
	 * @return Una nueva conexion en caso de éxito, de lo contrario null.
	 */
    public static Connection getConnection(String driverClassName,String url,String user,String pwd){
        try{
            Class.forName(driverClassName).newInstance();
            return DriverManager.getConnection(url,user,pwd);
        }
        catch(Exception e){
        	e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
		//the '&' in xml config is '&amp;'
		String url="jdbc:mysql://localhost:3306/ride_tracker?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="ride_tracker_user";
		String pwd="ZAPDLNSMrMh6wEpK";
		Connection conn =getConnection(url, user, pwd);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}