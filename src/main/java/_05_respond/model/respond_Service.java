package _05_respond.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import _05_respond.controller.JavaMailUtil;
import _05_respond.model.MemberBean;
import _05_respond.model.respond_DAO;

public class respond_Service implements respond_Service_Interface {
	private static List<MemberBean> memberList = new ArrayList<MemberBean>(); 
	
	public void sendMail(String name, String email, String ordno, String question, String description) throws IOException, 
		AddressException ,MessagingException{
		String from ="taipeijava006@gmail.com";
		List<String> to = Arrays.asList(new String[] {email.trim()});
		List<String> cc = Arrays.asList(new String[] {});
		List<String> bcc = Arrays.asList(new String[] {});
		String subject = "【wime商城】信件已傳送";
		String text = "<h1> <font color='orange'>wime商城 </font> 您的信件已傳送</h1>" + "<h2>我們會盡速為您處理</h2>"
				+ name + email + ordno + question + description +
				 "<br><br><font color='blue'> 再次感謝您的指教, </font><br>工作小組敬上";
		List<String> attachment = Arrays.asList(new String[] {});
		JavaMailUtil util = new JavaMailUtil(from, subject, text, to, cc, bcc, attachment);
		if(util.send()){
			System.out.println("發信成功");
			
		}else {
			System.out.println("發信失敗");	
		}
	}
	@Override
	public boolean idExists(String ordno) throws NamingException, SQLException {
		boolean exist = true;
		
		//==============檢查撈到的會員會員帳號是否相同=================
		respond_DAO rs = new respond_DAO();
		MemberBean mb = rs.selectOrdno(ordno);
			System.out.println("Ordno = "+ ordno);
			if(mb != null && mb.getOrdno().equals((ordno).trim())){
				System.out.println("mb.getOrdno = "+ mb.getOrdno());
				exist = false;
			}else {
			    System.out.println("1.訂單相同true/不同false= "+ exist);
			   return exist;	  
			  }	
			return exist;
	}

	@Override
	public void addNewMember(MemberBean mb) {
		memberList.add(mb);
	}
		
	
	}
		
	
