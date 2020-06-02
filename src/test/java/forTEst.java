import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.test.stat.statDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class forTEst {
	
	
	 @Autowired
	 private statDao dao;
	 @Test 
	 public void testConnection() throws Exception { 
		 
		 dao.select1MinStat();
	 }
	 
	
//  private static Connection conn;
//  private static PreparedStatement pstmt;
//  private static ResultSet rs;
//  
//  public static void main(String[] args) throws SQLException {
//	  String dbURL="jdbc:mysql://localhost:3306/TACS_AGENT?serverTimezone=UTC";                             
//      String dbID="root";
//      String dbPassword="root";
//      try {
//		Class.forName("com.mysql.jdbc.Driver");
//		conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
//		System.out.println(conn);
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  }
  
  public void test2() {};
 
}
