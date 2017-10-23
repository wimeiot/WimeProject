package _00_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _00_init.bean.Member_Bean;
import _00_login.model.Login_DAO;
import _00_login.model.Login_Service;


@WebServlet("/_00_login/ForgotServlet")
public class ForgotPsw_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	private void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//=======================初始設定========================
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		Login_DAO dao = new Login_DAO();
		Login_Service loginS = new Login_Service();
		
		HttpSession session = request.getSession();
		
		//=======================存放錯誤訊息的購物車========================
		Map<String, String> ErrorMsgMap = new HashMap<>();
		request.setAttribute("errorMsg", ErrorMsgMap);
		
		//=======================讀取忘記密碼畫面表格資料========================
		String email = request.getParameter("email");
		System.out.println("userEmail = "+email);
		
		//=======================檢查帳號欄位是否空白========================

		//=======================若空白放錯誤訊息到errorMsgMap========================
		if(email == null || email.trim().length() == 0) {
			ErrorMsgMap.put("EmptyError", "Email 欄位不可以空白喔!");
			System.out.println("EmptyError check");
		}
		

		
		//=======================檢查是否有該email帳號========================
		
		try {
//			MemberBean emailCheck = loginS.findEmail(userEmail);
			Member_Bean mb = dao.selectEmail(email);
			
			if(mb == null) {
				ErrorMsgMap.put("EmailNotExist", "此Email不是會員喔!");	
				System.out.println("EmailNotExist check");
			} else {
				//=======================取得該帳號的密碼寄送驗證信========================
				try {
					String password = mb.getPassword();
					loginS.sendNewPasswordMail(email, password);
					System.out.println("email寄出");
					ErrorMsgMap.put("EmailToYou", "驗證信已成功寄出，請至以下信箱收信，謝謝^_^<br>" + email);
					System.out.println("have this member check");
					//=======================沒有錯就寄信========================
					session.setAttribute("LoginOK", mb);
					
				} catch (AddressException e) {
					System.out.println("AddressException>>" + e.getMessage());
					e.printStackTrace();
				} catch (MessagingException e) {
					System.out.println("MessagingException>>" + e.getMessage());
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			ErrorMsgMap.put("EmailNotExist", "LoginServlet>>SQLException" + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			ErrorMsgMap.put("EmailNotExist", "LoginServlet>>NamingException" + e.getMessage());
			e.printStackTrace();
		}
		
		//=======================如果 errorMsgMap 是空的，forgot_psw.jsp========================
		
		if (!ErrorMsgMap.isEmpty()) {
			out.println(gson.toJson(ErrorMsgMap));
			out.close();
			return;
		}
		
			

	}

}
