package _00_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _00_init.GlobalService;
import _00_init.bean.Member_Bean;
import _00_login.model.Login_Service;

@WebServlet("/_00_login/Login.do")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proccess(request, response);
	}

	private void proccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// =======================初始設定========================
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		HttpSession session = request.getSession();

		// =======================存放錯誤訊息的購物車========================
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);// "識別字串"

		// =======================讀取登入畫面表格資料========================
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		String rm = request.getParameter("rememberMe");

		System.out.println("email = " + email);
		System.out.println("psw = " + psw);

		String requestURI = (String) session.getAttribute("requestURI");// 記得登入前的位置
		// setAttribute在filter

		// =======================檢查帳號密碼欄位是否空白========================
		// =======================若空白放錯誤訊息到errorMsgMap========================
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("EmailEmptyError", "Email欄位不可以空白喔!");
			System.out.println("EmailEmptyError check");
		}
		if (psw == null || psw.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄位不能空白喔!");
			System.out.println("PasswordEmptyError check");
		}

		// =======================記得我 cookie========================
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;

		if (rm != null) {
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(30 * 30 * 60);// 最長未動作登出時間
			cookieUser.setPath(request.getContextPath());// 記得專案名稱

			cookiePassword = new Cookie("psw", psw);
			cookiePassword.setMaxAge(30 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(30 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else {
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(0);// 最長未動作登出時間
			cookieUser.setPath(request.getContextPath());// 記得專案名稱

			cookiePassword = new Cookie("psw", psw);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);

		// =======================如果 errorMsgMap
		// 不是空的，表示有錯誤，交棒給login.jsp========================
		if (!errorMsgMap.isEmpty()) {
			// RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			// rd.forward(request, response);
			// return;
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;
		}

		// =======================檢查密碼有無錯誤========================
		Login_Service loginS = new Login_Service();
		try {
			System.out.println("所輸入Email為:" + email + "，password為:" + psw);
			Member_Bean mb = loginS.checkEmailPassword(email, psw);
			String encrypedString = GlobalService.encryptString(psw.trim());
			String pswd = GlobalService.getMD5Endocing(encrypedString);
			System.out.println("加密後密碼為:" + pswd);

			if (mb != null) {
				session.setAttribute("LoginOK", mb);

			} else {
				errorMsgMap.put("LoginError", "您輸入的帳號不存在或密碼錯誤喔!");

			}
		} catch (NamingException e) {
			errorMsgMap.put("LoginError", "LoginServlet>>NamingException" + e.getMessage());
		} catch (SQLException e) {
			errorMsgMap.put("LoginError", "LoginServlet>>SQLException" + e.getMessage());
			e.printStackTrace();
		}

		// =======================如果 errorMsgMap
		// 是空的，forgot_psw.jsp========================
		// if(errorMsgMap.isEmpty()) {
			// errorMsgMap.put("success", "登入成功!點擊<a href='../index.jsp'>首頁</a>帶您回首頁");
		// }
		if (errorMsgMap.isEmpty()) {
			// =======================繼續登入前的動作========================
			if (requestURI != null) {
				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
//				response.sendRedirect(response.encodeRedirectURL(requestURI));
				errorMsgMap.put("success", requestURI);
//				return;
			} else {
				errorMsgMap.put("success", "../index.jsp");
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
//				return;
			}

			// response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
			// } else {
			// RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			// rd.forward(request, response);
			// return;
			// out.println(gson.toJson(errorMsgMap));
			// out.close();
			// return;
		}
		if (!errorMsgMap.isEmpty()) {
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;
		}

	}

}
