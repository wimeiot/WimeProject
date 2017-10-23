package _07_aboutUs.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import _00_login.controller.JavaMailUtil;

public class JoinCoworker_Service {
	public void sendApplyMail(String name, String sexual, String birthday, 
			String message) throws AddressException, IOException, MessagingException {
		String from = "wimeiot@gmail.com";
		List<String> to = Arrays.asList(new String[] {"kingfafa8520@gmail.com"});
		List<String> cc = Arrays.asList(new String[] {});
		List<String> bcc = Arrays.asList(new String[] {});
		String subject = " 應徵申請";
		String text = 
				  "<h1>申請資料如下: </h1>"
				+ "<p>應徵者職稱:"+ name +"</p>"
				+ "<p>應徵者姓名:"+ name +"</p>"
				+ "<p>性別:"+ sexual +"</p>"
				+ "<p>生日:"+ birthday +"</p>"
				+ "<p>訊息:"+ message +"</p>"
				
				+ "<p>面是資料如夾帶檔。</p>";
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
