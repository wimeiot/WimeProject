package _01_register.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import _00_init.bean.Member_Bean;


public interface Register_DAO_Interface {
	

	public int saveMember(Member_Bean mb)throws SQLException, AddressException, IOException, MessagingException, NamingException;

	public int verifiedMailTrue(String account , String password)throws NamingException, SQLException;

	public Member_Bean selectEmail(String email) throws NamingException, SQLException;
	
	public String checkUserID(String email) throws NamingException, SQLException;
}
