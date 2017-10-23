package _00_login.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import _00_login.controller.JavaMailUtil;


public class Login_Service implements Login_Service_Interfce {
	
	//確認帳號密碼是否正確
	@Override
	public Member_Bean checkEmailPassword(String email, String password) throws SQLException, NamingException {
		Login_DAO dao = new Login_DAO();
		Member_Bean mb = dao.selectEmail(email);
		String encrypedString = GlobalService.encryptString(password.trim());
		String pswd = GlobalService.getMD5Endocing(encrypedString);
		String mbpswd = mb.getPassword().trim();
		if(mb != null && mbpswd.equals(pswd)) {
			return mb;
		} else {
			return null;
		}
	}
	
	//確認會員名單內是否有該email
	@Override
	public Member_Bean findEmail(String email) throws NamingException, SQLException {
		Login_DAO dao = new Login_DAO();
		Member_Bean mb = dao.selectEmail(email);
		if(mb != null) {
			return mb;
		}else {
			return null;
		}
		
	}
	
	//寄信叫用戶改新密碼
	@Override
	public void sendNewPasswordMail(String email, String password) throws AddressException, IOException, MessagingException {
		String from = "wimeiot@gmail.com";
		List<String> to = Arrays.asList(new String[] { email });
		List<String> cc = Arrays.asList(new String[] {});
		List<String> bcc = Arrays.asList(new String[] {});
		String subject = " Wime新密碼發送";
		String text = "<h1>親愛的客戶，您好: </h1>"
				+ "<h2>感謝您對WIME的愛戴^_^</h2>" 
				+ "<h2>請點選以下連結更改新密碼 : " 
				+ "<a href='http://localhost:8080/"+GlobalService.SYSTEM_NAME +"/ResetServlet?user="+ email +"&password="+ password +"'> 修改密碼  </a></h2>"
				+ "<br><br><font color='blue'> 再次感謝, </font><br>WIME全體團隊敬上";
		List<String> attachment = Arrays.asList(new String[] {});
		;
		JavaMailUtil util = new JavaMailUtil(from, to, cc, bcc, subject, text, attachment);
		if (util.send()) {
			
			System.out.println("發信成功");
		} else {
			System.out.println("發信失敗");
		}

	}

}
