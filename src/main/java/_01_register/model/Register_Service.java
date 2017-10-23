package _01_register.model;

import java.io.IOException;
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
import _01_register.controller.JavaMailUtil;

public class Register_Service implements Register_Service_Inerface {
	private static List<Member_Bean> memberList = new ArrayList<Member_Bean>(); 

	
	@Override
	public void sendMail(String mail, String password) throws IOException, 
		AddressException ,MessagingException{
		String from ="taipeijava006@gmail.com";
		List<String> to = Arrays.asList(new String[] {mail.trim()});
		List<String> cc = Arrays.asList(new String[] {});
		List<String> bcc = Arrays.asList(new String[] {});
		String subject = "【wime商城】歡迎你加入會員";
		String text = "<h1> <font color='orange'>wime商城 </font>  感謝您的加入</h1>" + "<h2>您可以按下列連結感受最新的體驗</h2>"
				+ "<a href='http://localhost:8080/"+ GlobalService.SYSTEM_NAME +"/verifiedMail.do?user=" + mail + "&password=" + password
				+ "'>驗證</a><br>" + "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";;
		List<String> attachment = Arrays.asList(new String[] {});
		JavaMailUtil util = new JavaMailUtil(from, subject, text, to, cc, bcc, attachment);
		if(util.send()){
			System.out.println("發信成功");
		}else {
			System.out.println("發信失敗");	
		}
	}
	@Override
	public boolean idExists(String email) throws NamingException, SQLException {
		boolean exist = false;
		
		//==============檢查撈到的會員會員帳號是否相同=================
		Register_DAO rs = new Register_DAO();
		Member_Bean mb = rs.selectEmail(email);
			System.out.println("Mail = "+ email);
			if(mb != null && mb.getMail().equals((email).trim())){
				System.out.println("mb.getMail = "+ mb.getMail());
				exist = true;
			}else {
			    System.out.println("1.帳號相同true/不同false= "+ exist);
			   return exist;	  
			  }	
			return exist;
	}
	@Override
	public void addNewMember(Member_Bean mb) {
		memberList.add(mb);
	}
	
	@Override
	public Member_Bean checkAccountPassword(String mail, String password) {
		for(Member_Bean mb : memberList) {
			if(mb.getMail().trim().equals(mail.trim())) {
				if(mb.getPassword().trim().equals(password)) {
					return mb;
				}
			}
		}
			return null;
		}
	}
		
	
