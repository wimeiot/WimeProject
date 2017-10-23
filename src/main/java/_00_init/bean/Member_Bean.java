package _00_init.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Member_Bean implements Serializable{
	private int id;							    // INT AUTO_INCREMENT NOT NULL /*PK,FK使用者ID*/
	private java.sql.Timestamp registDate;      // DATETIME NOT NULL DEFAULT NOW(),/*註冊日期*/
	private String password;        			// VARCHAR(32) NOT NULL,      /*密碼*/
	private String name;          		   		// VARCHAR(30) NOT NULL,	  /*姓名*/
	private String mail;          		  		// VARCHAR(30) NOT NULL,      /*e-mail*/
	private String phone;           			// VARCHAR(10),		          /*室內電話*/
	private String mobile;          			// VARCHAR(10),		          /*手機*/
	private String address;         			// VARCHAR(50),		      	  /*地址*/
	private java.sql.Date birthday; 			// DATE,			          /*生日*/
	private String gender;          			// CHAR(1),	              	  /*性別*/
	private String verified;                    //CHAR(1) DEFAULT 'F',     	  /*驗證*/
	
	//======================LoginDOA要用到的建構子========================
	//======================id也丟進去========================
	public Member_Bean(int id, String name, String mail, String phone, String mobile, String address, String gender,
			String password, Date birthday, Timestamp registDate) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.gender = gender;
		this.password = password;
		this.birthday = birthday;
		this.registDate = registDate;
	}
	
	
	//=======================for Insertable (!id && !registDate)========================
	public Member_Bean(String password, String name, String mail, String phone, String mobile, String address,
			Date birthday, String gender) {
		super();
		this.password = password;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	
	//======================for verified? (all)========================
	public Member_Bean(int id, Timestamp registDate, String password, String name, String mail, String phone,
			String mobile, String address, Date birthday, String gender, String verified) {
		super();
		this.id = id;
		this.registDate = registDate;
		this.password = password;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
		this.verified = verified;
	}

	//=======================for Update (!id && !registDate)========================
	public Member_Bean(String name, String address, String phone, String mobile, String mail) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
	}
	
	public Member_Bean() {
		
	}
	
	//=======================getter & setter========================
	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(java.sql.Timestamp registDate) {
		this.registDate = registDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
