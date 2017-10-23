package _05_respond.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;


public interface respond_DAO_Interface {
	

	public int saveMember(MemberBean mb)throws SQLException, AddressException, IOException, MessagingException, NamingException;

	MemberBean selectOrdno(String ordno) throws NamingException, SQLException;
}
