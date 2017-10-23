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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _00_init.bean.Member_Bean;
import _00_login.model.Login_DAO;

/**
 * Servlet implementation class ResetPswServlet
 */ 
@WebServlet("/_00_login/ResetPswServlet")
public class ResetPsw_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	private void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		
		
		//=======================存放錯誤訊息的購物車========================
		Map<String, String> errorMsgMap = new HashMap<>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		
		//=======================讀取重設密碼畫面表格資料========================
		String psw01 = request.getParameter("psw01");
		String psw02 = request.getParameter("psw02");
		System.out.println("psw01="+psw01);
		System.out.println("psw02="+psw02);
//		String email = (String) session.getAttribute("LoginOK");
		Member_Bean mb = (Member_Bean) session.getAttribute("LoginOK");
		
				
		//=======================檢查兩個欄位是否空白========================
		//=======================若空白放錯誤訊息到errorMsgMap========================
		if(psw01 == null || psw01.trim().length() == 0) {
			errorMsgMap.put("Psw01EmptyError", "重設密碼兩個欄位不可以空白喔!");
		}
		if(psw02 == null || psw02.trim().length() == 0) {
			errorMsgMap.put("Psw02EmptyError", "重設密碼兩個欄位不可以空白喔!");
		}
		
		if(psw01.trim().length() < 6 || psw01.trim().length() > 12) {
			errorMsgMap.put("Psw01LengthError", "密碼長度不可少於6位數，多餘12位數喔!");
		}
		if(psw02.trim().length() < 6 || psw02.trim().length() > 12) {
			errorMsgMap.put("Psw02LengthError", "密碼長度不可少於6位數，多餘12位數喔!");
		}
		
		
		//=======================檢查兩個欄位是否相同========================
		//=======================若空白放錯誤訊息到errorMsgMap========================
		if(!psw01.equals(psw02)) {
			errorMsgMap.put("notEqualError", "兩個欄位輸入的值不同，請重新輸入");
		}
		
		//=======================如果 errorMsgMap 不是空的，表示有錯誤，交棒給resetPSW.jsp========================
		if(!errorMsgMap.isEmpty()) {
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;
		}
		String email = mb.getMail();
		//=======================如果 errorMsgMap 是空的，update新密碼，並且交棒給login.jsp========================
		if(errorMsgMap.isEmpty()) {
			Login_DAO dao = new Login_DAO();
			try {
				dao.updatePSW(email, psw01);
				errorMsgMap.put("UpdateSuccess","修改成功!<a href='../index.jsp'>回首頁</a>");
				System.out.println("帳號 : " + email + "密碼" + psw01 +"，上傳成功!");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(gson.toJson(errorMsgMap));
			out.close();
//			
//			response.sendRedirect("../index.jsp");
			return;
		}
	}

}
