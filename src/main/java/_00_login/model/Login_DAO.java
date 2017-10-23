package _00_login.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _00_init.bean.Member_Bean;


public class Login_DAO implements Login_DAO_Interfce {
	static private List<Member_Bean> memberList = new ArrayList<Member_Bean>();
	public Login_DAO() {}
	
	//=======================查詢Email========================
	@Override
	public Member_Bean selectEmail(String email) throws NamingException, SQLException {
		String sql = "select * from Customer where CM_Mail = ?";
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member_Bean mb = null;
	
		//=======================連線字串========================
		try {
			conn = ds.getConnection();
			System.out.println("sql = " +sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
		//=======================把撈到的會員資料放到mb裡========================
			if(rs.next()) {
//				int pKey = rs.getInt("seqNo");//這是什麼?
				int ID = rs.getInt("CM_ID");
				java.sql.Timestamp RegistDate = rs.getTimestamp("CM_RegistDate");
				String Name = rs.getString("CM_Name");
				String Mail = rs.getString("CM_Mail").trim();
				String Phone = rs.getString("CM_Phone");
				String Mobile = rs.getString("CM_Mobile");
				String Address = rs.getString("CM_Address");
				java.sql.Date Birthday = rs.getDate("CM_Birthday");
				String Gender = rs.getString("CM_Gender");
				String Password = rs.getString("cm_password").trim();
				mb = new Member_Bean(ID, Name, Mail, Phone, Mobile, Address, Gender, Password, Birthday, RegistDate);
				
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return mb;
	}
	
	//=======================更新密碼========================
	@Override
	
	public Member_Bean updatePSW(String email, String newPSW) throws NamingException, SQLException {
		String sql = "update customer set CM_Password = ? where CM_Mail = ?";
		int n;
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member_Bean mb = null;
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
//			mb = selectEmail(email);
			String encryptedString = GlobalService.encryptString(newPSW);
			pstmt.setString(1, GlobalService.getMD5Endocing(encryptedString));
			pstmt.setString(2, email);
			
			n = pstmt.executeUpdate();
			System.out.println("已將會員:"+email+"，原本密碼為:"+newPSW+"，加密密碼為:"+GlobalService.getMD5Endocing(encryptedString)+"，上傳" + n + "筆資料");
		}  finally {
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
		}

		return mb;
	}


}
