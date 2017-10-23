package _05_respond.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

public interface respond_Service_Interface {

	void sendMail(String name, String email, String ordno, String question, String description) throws IOException, AddressException, MessagingException;

	boolean idExists(String ordno) throws NamingException, SQLException;

	void addNewMember(MemberBean mb);
	
}
