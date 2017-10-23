package _01_register.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import _00_init.bean.Member_Bean;

public interface Register_Service_Inerface {

	public boolean idExists(String mail)throws NamingException, SQLException;

	void addNewMember(Member_Bean mb);

	void sendMail(String mail, String password) throws IOException, AddressException, MessagingException;
	
	public Member_Bean checkAccountPassword(String mail, String password);
}
