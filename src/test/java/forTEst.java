import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class forTEst {
	
	
//  private static Connection conn;
//  private static PreparedStatement pstmt;
//  private static ResultSet rs;
//  
//  public static void main(String[] args) throws SQLException {
//	  String dbURL="jdbc:mysql://localhost:3306/shop?serverTimezone=UTC";                             
//      String dbID="shop";
//      String dbPassword="shop";
//      try {
//		Class.forName("com.mysql.jdbc.Driver");
//		conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
//		System.out.println(conn);
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  }
//  
//  public void test2() {};
 
}
