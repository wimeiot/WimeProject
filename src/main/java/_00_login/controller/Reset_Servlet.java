package _00_login.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.GlobalService;
import _00_init.bean.Member_Bean;
import _00_login.model.Login_Service;

/**
 * Servlet implementation class ResetServlet
 */
@WebServlet("/ResetServlet")
public class Reset_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proccess(request, response);
	}

	private void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String email = request.getParameter("user");
		String password = request.getParameter("password");
		
		Login_Service loginS  = new Login_Service();
		
		Member_Bean mb;
		
		try {
			mb = loginS.checkEmailPassword(email, password);
			
			System.out.println( email + " : 內部驗證成功!開始設定新密碼");
			
			if( mb != null) {
				session.setAttribute("LoginOK", mb);
			} else {
				System.out.println("內部驗證失敗!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("/"+GlobalService.SYSTEM_NAME+"/_00_login/resetPSW.jsp");
	}

}
