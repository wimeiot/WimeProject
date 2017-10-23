package _06_query.controller;

import java.io.IOException;
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

import _00_init.bean.Member_Bean;
import _06_query.model.Update_DAO;
import _06_query.model.Update_DAO_Interface;

@WebServlet("/_06_query/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		
//		Integer id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");

		request.setAttribute("ErrorMsgKey", errorMsg);
		session.setAttribute("MsgOK", msgOK);
		
		System.out.println("Servlet "+name);
		System.out.println("Servlet "+address);
		System.out.println("Servlet mail "  +mail);
		System.out.println("Servlet "+phone);
		System.out.println("Servlet "+mobile);
		
		// 2. 進行必要的資料轉換

		// 3. 檢查使用者輸入資料
		
		if (name == null || name.trim().length() == 0) {
			errorMsg.put("errorName","姓名欄必須輸入");
		}
		if (address == null || address.trim().length() == 0) {
			errorMsg.put("errorAddress","地址欄必須輸入");
		}
		if (phone == null || phone.trim().length() == 0) {
			errorMsg.put("errorTel","電話號碼欄必須輸入");
		}
		if (mobile == null || mobile.trim().length() == 0) {
			errorMsg.put("errorMob","手機號碼欄必須輸入");
		}
		
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/_06_query/query.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 4. 進行 Business Logic 運算
		Update_DAO_Interface udao = null;
		try {
			udao = new Update_DAO();
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			Member_Bean mem = new Member_Bean(name, address, phone, mobile, mail);
			udao.updateMember(mem);
		} catch (SQLException e) {
			errorMsg.put("errorSave","儲存資料時發生錯誤，請檢查，例外=" + e.getMessage());
			e.printStackTrace();
		}
		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		request.setAttribute("mail", mail);
		if (errorMsg.isEmpty()) {
			msgOK.put("sucessSave","修改成功");
			errorMsg.put("sucessSave","修改成功");
			RequestDispatcher rd = request.getRequestDispatcher("/_06_query/query.jsp");
			rd.forward(request, response);
			return;
		} else {
			errorMsg.put("failSave","修改失敗，請重新輸入");
			RequestDispatcher rd = request.getRequestDispatcher("/_06_query/query.jsp");
			rd.forward(request, response);
			return;
		}

	}
}