package _05_respond.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
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
import _05_respond.model.respond_DAO_Interface;
import _05_respond.model.respond_Service;
import _05_respond.model.respond_Service_Interface;

/**
 * Servlet implementation class Servicecontroller
 */

@WebServlet("/_05_respond/respond.do")
public class respond_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//文字資料轉內碼
		
		// 定義存放錯誤訊息的 Collection物件
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);
		Map<String,String> errorMsg = new HashMap<>();
		//準備存放錯誤訊息的Map
		Map<String,String> msgOk = new HashMap<>();
		//準備存放註冊成功訊息的Map
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); //顯示錯誤訊息
		session.setAttribute("MsgOk", msgOk);  //顯示正常訊息

		session = request.getSession(false);
		if(session != null) {
		String codeInSession = (String)session.getAttribute("RandomString");
		}

		String name = "";          		   		// VARCHAR(30) NOT NULL,	  /*姓名*/
		String email = "";       		  		// VARCHAR(30) NOT NULL,      /*e-mail*/
		String ordno = "";
		String question = "";         		// INT(2) NOT NULL,    	      /*問題種類*/
		String description = "";          			// TEXT NOT NULL,	      	  /*留言*/
		
		
		

		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		// 讀取使用者所輸入，由瀏覽器送來的 email 欄位內的資料，注意大小寫
		email = request.getParameter("email");

		if (email == null || email.trim().length() == 0) {
			errorMsg.put("errorEmail", "請輸入e-mail帳號");
		} else if (email.indexOf("@") == -1) {
			errorMsg.put("errorEmailFormat", "e-mail格式xxx@xxxx.xxx");
		}

		// 讀取使用者所輸入，由瀏覽器送來的 name 欄位內的資料
		name = request.getParameter("name");
		// 檢查使用者所輸入的資料
		if(name == null || name.trim().length() == 0) {
			errorMsg.put("errorName", "請輸入性名");
		}

		// 讀取使用者所輸入，由瀏覽器送來的 ordno 欄位內的資料
		ordno = request.getParameter("ordno");
		// 檢查使用者所輸入的資料
		
		// 讀取使用者所輸入，由瀏覽器送來的 question 欄位內的資料		
		question = request.getParameter("question");
		// 檢查使用者所輸入的資料	
		
		// 讀取使用者所輸入，由瀏覽器送來的 description 欄位內的資料
		description = request.getParameter("description");
		// 檢查使用者所輸入的資料
		if (description == null || description.trim().length() == 0) {
			errorMsg.put("errorDescription", "請簡述說明您反應的問題，200字以內。");
		}
		
		// 如果有錯誤，呼叫view元件，送回錯誤訊息
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/InsertMemberError.jsp");
			rd.forward(request, response);
			return;
		}

		respond_Service rs = new respond_Service();

		try {
			
			if(rs.idExists(ordno)) {
				System.out.println("2.訂單單號不同true/不同false= "+ "false");
				errorMsg.put("errorIDDup", "此訂單不存在");
			}else {
				System.out.println("3.訂單單號相同true/不同false= "+ "true");
				MemberBean mb = new MemberBean(question, name, email, 
						ordno, description);
			// 將MemberBean member立即寫入Database
			respond_DAO rd = new respond_DAO();
			// 存入資料庫時，自動把資料寫到RegisterService的memberLister
			int n = rd.saveMember(mb);
			System.out.println("新增課服回報資料 = " + n);
			if(n == 1) {
				rs.sendMail(name, email, ordno, question, description);
				RequestDispatcher rdp = request.getRequestDispatcher("/_05_respond/success.jsp");
				// 請容器代為呼叫下一棒程式
				rdp.forward(request, response);

				msgOk.put("InsertOK", "<font color = 'orangr'>新增成功，請開始使用本系統</font>");
				String result= "信件已寄出~";


			}else {
				errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
		  }
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/InsertMemberError.jsp");
				rd.forward(request, response);
			}
		} catch (NamingException e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error );
			RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/InsertMemberError.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error);
			RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/InsertMemberError.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error);
			RequestDispatcher rd = request.getRequestDispatcher("/_05_respond/InsertMemberError.jsp");
			rd.forward(request, response);
		}


	}

}

