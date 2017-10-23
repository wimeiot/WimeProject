package _01_register.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _00_init.bean.Member_Bean;

public class Register_DAO implements Register_DAO_Interface {
	private static List<Member_Bean> memberList = new ArrayList<Member_Bean>(); 

	
	public Register_DAO() throws NamingException, SQLException  {
	
	}

	@Override
	public int saveMember(Member_Bean mb) throws SQLException, AddressException,
		IOException, MessagingException, NamingException {
		int r = 0;
		String sql = "INSERT INTO Customer "
				+ " (CM_Password, CM_Name, CM_Mail, CM_Phone, CM_Mobile, "
				+ " CM_Address, CM_BirthDay, CM_Gender ) "
				+ " values (?, ?, ?, ?, ?, ?, ?, ? )";
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);	
	try(Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)){
		
		String encryptedString = GlobalService.encryptString(mb.getPassword());
		pstmt.setString(1, GlobalService.getMD5Endocing(encryptedString));
	//	pstmt.setString(1, mb.getPassword());
		pstmt.setString(2, mb.getName());
		pstmt.setString(3, mb.getMail());
		pstmt.setString(4, mb.getPhone());
		pstmt.setString(5, mb.getMobile());
		pstmt.setString(6, mb.getAddress());
		pstmt.setDate(7, mb.getBirthday());
		pstmt.setString(8, mb.getGender());
		r = pstmt.executeUpdate();	
//		memberList.add(mb);
				
			if(r == 1) {
			// 寫入成功，應該將MemberBean立即加入LoginService的memberList內，否則最新的User將無法登入
				mb.setPassword(GlobalService.getMD5Endocing(encryptedString));
				Register_Service rs = new Register_Service();
				rs.addNewMember(mb);		
				rs.sendMail(mb.getMail(),mb.getPassword());
			}else {
				throw new SQLException("RegisterServiceDB:新增記錄數:0");
			}
				return r;
			}	
		}


	@Override
	public int verifiedMailTrue(String mail, String password) throws NamingException, SQLException {
		int n = 0;
		String sql = "UPDATE customer SET CM_Verified = 'T' where CM_Mail = ? and CM_Password = ?";
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);	
	try(Connection  conn = ds.getConnection();  
		PreparedStatement pstmt = conn.prepareStatement(sql)){
			
		if(mail != "" && password != ""){
			System.out.println(mail + " + " + password);
			pstmt.setString(1, mail);
			pstmt.setString(2, password);	
			n = pstmt.executeUpdate();			
			System.out.println("account = " + mail);
			System.out.println("password = " + password);
			System.out.println("n = " + n);
				
			return n;
		}
		return n;
	  }
	}

	@Override
	public Member_Bean selectEmail(String email) throws NamingException, SQLException {
		String sql = "SELECT * FROM customer WHERE CM_Mail = ?";
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);	
	try(Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)){
		pstmt.setString(1, email);
	try(ResultSet rs = pstmt.executeQuery()){
		System.out.println("selectEmail = " + email);
		
		
		//==============把撈到的會員資料放到memberList裡=============
		while (rs.next()) {
			int id = rs.getInt(1);
			Timestamp registDate = rs.getTimestamp(2);
			String password = rs.getString(3);
			String name = rs.getString(4);          		
			String mail = rs.getString(5);          		
			String phone = rs.getString(6);           		
			String mobile = rs.getString(7);          		
			String address = rs.getString(8);         		
			Date birthday = rs.getDate(9); 			
			String gender = rs.getString(10);        
			String verified = rs.getString(11); 
			
			Member_Bean mb = new Member_Bean(id, registDate, password, name, mail, phone, mobile, address, birthday, gender, verified);
			Register_Service rs1 = new Register_Service();
			rs1.addNewMember(mb);
			System.out.println("memberList.add(mb) = " + sql);
		
			return mb;
			}
		  }
		}	
		return null;
	}

	@Override
	public String checkUserID(String email) throws NamingException,SQLException {;
		String sql = "SELECT * FROM customer WHERE CM_Mail = ? ";
		String emailId = "";
		//=======================連線物件========================
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);	
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setString(1, email);
			try(ResultSet rs = pstmt.executeQuery();
				){
				if(rs.next()) {
					emailId = rs.getString("CM_Mail").trim();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			emailId = "Error: 資料庫異常，請檢查資料庫";
		}
		
		return emailId;
	}
	
	
}
