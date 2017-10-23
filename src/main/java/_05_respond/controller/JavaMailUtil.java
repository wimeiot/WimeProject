package _05_respond.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtil {
	private static String userid;
	private static String password;
	
	MimeMessage message;
	
	String from = "";
	String subject = "";
	String text = "";
	List<String> to;
	List<String> cc;
	List<String> bcc;
	List<String> attachment;
	
	//設定信件內容 &副本
	
	public JavaMailUtil(String from, String subject, String text, 
			List<String> to, List<String> cc, List<String> bcc, List<String> attachment) {
		super();
		this.from = from;
		if(subject == null) {
			this.subject = "";
		}else {
			this.subject = subject;
		}
		if(text == null) {
			this.text = "";
		}else {
			this.text = text;
		}
		if(to == null) {
			this.to = new ArrayList<>();
		}else {
			this.to = to;
		}
		if(cc == null) {
			this.cc = new ArrayList<>();
		}else {
			this.cc = cc;
		}
		if(bcc == null) {
			this.bcc = new ArrayList<>();
		}else {
			this.bcc = bcc;
		}
		if(attachment == null) {
			this.attachment = new ArrayList<>();
		}else {
			this.attachment = attachment;
		}
	}
	
	public boolean send()throws IOException, AddressException, MessagingException {
			createSession();
			setAddresses();
			setSubject();
			addContent();
			Transport.send(message);
		return true;
	}
	
	private void addContent() throws MessagingException, IOException{
		Multipart multipart = new MimeMultipart();
		MimeBodyPart bodyPart0 = new MimeBodyPart();
		int idx1 = tokenCount(text, "<");//1
		int idx2 = tokenCount(text, ">");//1
		System.out.println(text);
		System.out.println(idx1);
		System.out.println(idx2);
		if(idx1 == idx2 && idx1 != 0) {
			bodyPart0.setText(text, "UTF-8", "html");
		}else {
			bodyPart0.setText(text, "UTF-8", "plain");
		}
		multipart.addBodyPart(bodyPart0);
		for(String file : attachment) {
			MimeBodyPart bodyPart2 = new MimeBodyPart();
			bodyPart2.attachFile(new File(file));
			bodyPart2.setFileName(file);
			multipart.addBodyPart(bodyPart2);
		}
		message.setContent(multipart);
	}

	private int tokenCount(String target, String token) {
		int lastIndex = 0;
		int count = 0;
		
		while(lastIndex != -1) {
			//傳回 this字串中自位置 fromIndex 開始所指定子字串 str 所在的位置。如找不到，則傳回 -1。
			lastIndex = target.indexOf(token, lastIndex);
			
			if(lastIndex != -1) {
				count++;
				lastIndex += token.length();
			}
		}
		
		return count;
	}

	//設定收件主旨
	private void setSubject() throws MessagingException{
		message.setSubject(subject);
		
	}
	
	//設定寄件者,收件者,收復本者,收密件副本者
	private void setAddresses() throws AddressException, MessagingException{
		//寄件者
		message.setFrom(new InternetAddress(from));
		//收件者
		List<Address> listTO = new ArrayList<>();
		for(String s : to) {
			listTO.add(new InternetAddress(s));
		}
		message.addRecipients(Message.RecipientType.TO, 
				listTO.toArray(new Address[0]));
		//收副本者
		List<Address> listCC = new ArrayList<>();
		for(String s : cc) {
		listCC.add(new InternetAddress(s));
		}
		message.addRecipients(Message.RecipientType.CC, 
		listCC.toArray(new Address[0]));
		//收密件副本者
		List<Address> listBCC = new ArrayList<>();
		for(String s : bcc) {
		listBCC.add(new InternetAddress(s));
		}
		message.addRecipients(Message.RecipientType.BCC, 
		listBCC.toArray(new Address[0]));
	}

	private void createSession() {
		Session session;
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		//設定連線時使用埠號(465)
		props.put("mail.smtp.socketFactory.port","465");
		//聲明要進行SSL(Secure Socket Layer)連線
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//Gmail要求必須進行身分驗證(Authentication)
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Authenticator au = new javax.mail.Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userid,password);
			}	
		};
		
		session = Session.getDefaultInstance(props, au);
		message = new MimeMessage(session);
		
	}
	
	
	private static String getFileName(String path) {
		return path.substring(path.lastIndexOf("\\") + 1);
	}
	
	
	static {
		userid = "taipeijava006@gmail.com";//利用此帳號寄信
		password = "sa123456";
	}
	
}
