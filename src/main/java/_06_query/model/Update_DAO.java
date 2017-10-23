package _06_query.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _00_init.bean.Member_Bean;
import _00_login.model.Login_DAO;




public class Update_DAO implements Update_DAO_Interface {
	Login_DAO lgd;
	private DataSource ds = null;
	private static String mail = null;
	
	public Update_DAO() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		lgd = new Login_DAO();
	}
	
	public Collection<Member_Bean> getMemberData() throws SQLException {
		Collection<Member_Bean> coll = new ArrayList<Member_Bean>();
		
		String SELECT_BY_MAIL = " Select CM_id, CM_name, CM_address, "
				+ " CM_phone, CM_mail , CM_mobile from customer where CM_mail = ? ";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_MAIL);
			stmt.setString(1, mail);			
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				Member_Bean temp = new Member_Bean();
				temp.setId(rset.getInt("CM_id"));
				temp.setName(rset.getString("CM_name"));
				temp.setAddress(rset.getString("CM_address"));
				temp.setPhone(rset.getString("CM_phone"));
				temp.setMobile(rset.getString("CM_mobile"));
				temp.setMail(rset.getString("CM_mail"));
				coll.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		return coll;
		
	}
	
	synchronized public int updateMember(Member_Bean mb) throws SQLException {
		PreparedStatement pstmt1 = null;
		Connection conn = ds.getConnection();
		int r = 0;
		try {
			String sql1 = " UPDATE customer "
					+ " SET "
					+ "     CM_name = ?, "
					+ "     CM_address = ?, "
					+ "     CM_phone = ?, "
					+ "     CM_mobile = ? "
					+ " WHERE CM_mail = ? ";
					
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, mb.getName());			
			pstmt1.setString(2, mb.getAddress());
			pstmt1.setString(3, mb.getPhone());
			pstmt1.setString(4, mb.getMobile());
			pstmt1.setString(5, mail);
			System.out.println(mb.getName());
			System.out.println(mb.getAddress());
			System.out.println(mb.getPhone());
			System.out.println(mb.getMobile());
			System.out.println(mail);
			r = pstmt1.executeUpdate();

			System.out.println("修改一筆customer紀錄，是否成功=" + r);
		} finally {
			// 關閉相關的物件
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("關閉相關物件時發生例外: " + e);
				}
			}
		}
		return r;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	


}
