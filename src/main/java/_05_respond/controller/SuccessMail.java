package _05_respond.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _05_respond.model.MemberBean;
import _05_respond.model.respond_DAO;
import _05_respond.model.respond_Service;


/**
 * Servlet implementation class SuccessMail
 */
@WebServlet("/_05_respond/SuccessMail.do")
public class SuccessMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String DEFAULT_EMAIL	= "(未輸入)";
	final String DEFAULT_NAME 	= "訪客";
	final String DEFAULT_ORDNO 	= "(未輸入)";
	final String DEFAULT_QUESTION 	= "(未輸入)";
	final String DEFAULT_DESCRIPTION 	= "(未輸入)";


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processRequest(request, response);
	}
//
	public void processRequest(HttpServletRequest request,
			             HttpServletResponse response) throws IOException, 
		 ServletException {
	    // 說明瀏覽器送來之文字資料的編碼
		request.setCharacterEncoding("UTF-8");


		String name = request.getParameter("name");
		// name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
	    // 如果瀏覽器沒有送來名為userName的資料 或 瀏覽器有送來名為userName的資料，但
	    // 去掉該資料的頭尾空白後，長度為 0		
		if (name == null || name.length() == 0) {
			name = DEFAULT_NAME;
		}
		String email = request.getParameter("email");
	    // 如果瀏覽器沒有送來名為eMail的資料 或 瀏覽器有送來名為eMail的資料，但
	    // 去掉該資料的頭尾空白後，長度為 0		
		if (email == null ||  email.length() == 0) {
			email = DEFAULT_EMAIL;
		}
		String question = request.getParameter("question");
////	    // 如果瀏覽器沒有送來名為tel的資料 或 瀏覽器有送來名為tel的資料，但
////	    // 去掉該資料的頭尾空白後，長度為 0		
//		if (question == null || question.length() == 0) {
//			question = DEFAULT_QUESTIONTYPE;
//		}
		String ordno = request.getParameter("ordno");
		
		if (ordno == null || ordno.length() == 0) {
			ordno = DEFAULT_ORDNO;
		}
		String description = request.getParameter("description");
	
		if (description == null || description.length() == 0) {
			description = DEFAULT_DESCRIPTION;
		}
//		
		String[] fruits = request.getParameterValues("fruit");
	    // 將四樣資訊裝入CustomerBean型別的物件cust內		
		MemberBean mb = new MemberBean(name, email, ordno, question, description);
	    // 將cust物件暫存到請求物件內，成為它的屬性物件，屬性物件可以與別的程式共用。		
        request.setAttribute("MemberBean",mb);
        
        // 程式的執行順序移轉到/ch02/displayCustomerInfo.jsp內        
		RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/success.jsp");
		rd.forward(request, response);		
	}
	
}

