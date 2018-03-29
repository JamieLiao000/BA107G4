package android.com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	private static DataSource ds=null;
	static {
			  try {
			   Context ctx = new InitialContext();
			   ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			  } catch (NamingException e) {
			   e.printStackTrace();
			  }
	 }
	 static final String FIND_BY_ACCOUNT_PASWD = "SELECT * FROM MEMBER WHERE ACC = ? AND PSW = ?";
	@Override
	public boolean isMember(String userId, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean isMember = false;
		try {
			conn=ds.getConnection();
			ps=conn.prepareStatement(FIND_BY_ACCOUNT_PASWD);
			ps.setString(1,userId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();/*查詢資料庫 傳回資料列*/
			isMember=rs.next();  /*.next()  有資料true | 沒有false*/
			return isMember;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;
		
	}
	
}
