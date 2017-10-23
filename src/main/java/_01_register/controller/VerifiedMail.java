package _01_register.controller;

import java.io.IOException;
import java.net.URLEncoder;
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
import _01_register.model.Register_DAO;
import _01_register.model.Register_Service;

/**
 * Servlet implementation class VerifiedMail
 */
@WebServlet("/verifiedMail.do")
public class VerifiedMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String mail = request.getParameter("user");
		String password = request.getParameter("password");
	
			 try {
				 Register_DAO rd = new Register_DAO();
				 int n = rd.verifiedMailTrue(mail, password);
					if(n == 1) {
						System.out.println("mail驗證成功 ");
						String result = "驗證成功";
						HttpSession session = request.getSession();
						Register_Service rs = new Register_Service();
						Member_Bean mb = rs.checkAccountPassword(mail, password);
						System.out.println("mb = " + mb);
							if(mb != null) {
								session.setAttribute("LoginOK", mb);

							}else {
								System.out.println("mb = null");
							}
							response.sendRedirect(
									response.encodeRedirectURL("/"+GlobalService.SYSTEM_NAME+"/_01_register/verified.jsp?VerifiedResultOK=" + URLEncoder.encode(result,"UTF-8")));
							return;
					}else {
					System.out.println("mail驗證失敗 ");
					String result = "驗證失敗";
					response.sendRedirect(
							response.encodeRedirectURL("/"+GlobalService.SYSTEM_NAME+"/_01_register/verified.jsp?VerifiedResultNO=" + URLEncoder.encode(result,"UTF-8")));
					}
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}


	}
	
}
