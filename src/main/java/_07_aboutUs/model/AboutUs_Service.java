package _07_aboutUs.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import _00_login.controller.JavaMailUtil;

public class AboutUs_Service {
	public void sendApplyMail(String identity, String companyName, String deitoria, 
			String capital, String sexaul, String principle, String contecter, 
			String phone, String email, String message) throws AddressException, IOException, MessagingException {
		String from = "wimeiot5687183@gmail.com";
		List<String> to = Arrays.asList(new String[] {"wimeiot@gmail.com"});
		List<String> cc = Arrays.asList(new String[] {});
		List<String> bcc = Arrays.asList(new String[] {});
		String subject = " 廠商申請信";
		String text = "<h1>申請資料如下: </h1>"
				+ "<p>廠商身分:"+ identity +"</p>"
				+ "<p>公司名稱:"+ companyName +"</p>"
				+ "<p>資本額:"+ deitoria +"</p>"
				+ "<p>負責人:"+ capital +"</p>"
				+ "<p>負責人性別:" + sexaul + "</p>"
				+ "<p>聯絡人:"+ contecter +"</p>"
				+ "<p>連絡電話:"+ phone +"</p>"
				+ "<p>連絡信箱:"+ email +"</p>"
				+ "<p>廠商留言:"+ message +"</p>"
				+ "<p>公司資料如夾帶檔。</p>";
		List<String> attachment = Arrays.asList(new String[] {});
		;
		JavaMailUtil util = new JavaMailUtil(from, to, cc, bcc, subject, text, attachment);
		if (util.send()) {
			
			System.out.println("發信成功!");
			System.out.println("to address :"+to);
		} else {
			System.out.println("發信失敗!");
		}

	}
}
