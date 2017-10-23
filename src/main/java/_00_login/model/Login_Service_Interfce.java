package _00_login.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import _00_init.bean.Member_Bean;

public interface Login_Service_Interfce {
	
	//確認帳號密碼是否正確
	public Member_Bean checkEmailPassword(String email, String password) throws SQLException, NamingException;
	
	//確認會員名單內是否有該email
	public Member_Bean findEmail(String email) throws NamingException, SQLException;
	
	//寄信叫用戶改新密碼
	public void sendNewPasswordMail(String email, String password) throws AddressException, IOException, MessagingException ;
	
}
