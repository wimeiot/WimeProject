package _01_register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _00_init.bean.Member_Bean;
import _01_register.model.Register_DAO;

/**
 * Servlet implementation class CheckUserIdServlet
 */
@WebServlet("/_01_register/CheckUserIdServlet")
public class CheckUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckUserIdServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NamingException, SQLException {

		request.setCharacterEncoding("UTF-8");// 文字轉內碼
		String email = request.getParameter("email");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String emailId = "";
		Gson gson = new Gson();

		Map<String, String> map = new HashMap<>();
		Pattern p = Pattern.compile("^[a-zA-Z0-9._]+@([a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+)+[a-zA-Z]+$");
		Matcher m = p.matcher(email);
		if (m.find() == false) {
			map.put("errorEmailFormat", "請輸入正確的e-mail格式xxx@xxx.xxx");
		} else {
			if (email != null && email.trim().length() != 0) {
				Register_DAO dao = new Register_DAO();
				emailId = dao.checkUserID(email);
				if (emailId == null) {
					emailId = "";
				}
				map.put("emailId", emailId);
			}
		}
		out.println(gson.toJson(map));
		out.close();
	}

}
