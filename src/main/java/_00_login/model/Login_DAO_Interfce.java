package _00_login.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import _00_init.bean.Member_Bean;

public interface Login_DAO_Interfce {

	//=======================查詢Email========================
	public Member_Bean selectEmail(String email) throws NamingException, SQLException ;
	//String sql = "select * from Customer where CM_Mail = ?";
	
	//=======================更新密碼========================
	public Member_Bean updatePSW(String email, String newPSW) throws NamingException, SQLException;
	//String sql = "update customer set CM_Password = ? where CM_Mail = ?";
	
}
