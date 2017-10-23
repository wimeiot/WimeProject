package _05_respond.model;

import java.io.Serializable;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name; // VARCHAR(30) NOT NULL, /*姓名*/
	private String email; // VARCHAR(30) NOT NULL, /*e-mail*/
	private String ordno;
	private String question; // INT(2) NOT NULL, /*問題種類*/
	private String description; // TEXT NOT NULL, /*留言*/


	public MemberBean() {
		super();
	}

	public MemberBean(String ordno) {
		super();
		this.ordno = ordno;
	}

	public MemberBean(String name, String email, String ordno, String question, String description) {
		super();
		this.name = name;
		this.email = email;
		this.ordno = ordno;
		this.question = question;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
