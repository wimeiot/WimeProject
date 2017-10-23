package _07_aboutUs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _00_init.bean.JoinCowerker_Bean;
import _07_aboutUs.model.JoinCoworker_DAO;
import _07_aboutUs.model.JoinCoworker_Service;

/**
 * Servlet implementation class JoinCoworker_Servlet
 */
@WebServlet("/_07_aboutUs/JoinCoworker_Servlet")
public class JoinCoworker_Servlet extends HttpServlet {
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
		
//		JoinCoworker_DAO dao = new JoinCoworker_DAO();
//		ArrayList<JoinCowerker_Bean> nameArray = dao.getSelectAll();
//		Map<String, ArrayList<JoinCowerker_Bean>> map = new HashMap<>();
//		
//		int i;
//		for(i=0; i<nameArray.size(); i++) {
//			map.put("i", nameArray);
//		}
//		request.setAttribute("beanList", nameArray); 
		
		String name = request.getParameter("name");
		String sexual = request.getParameter("sexual");
		String birthday = request.getParameter("birthday");
		String message = request.getParameter("message");
		
		System.out.println("===============================");
		System.out.println("名字，name = "+name);
		System.out.println("性別，sexual = "+sexual);
		System.out.println("生日，birthday = "+birthday);
		System.out.println("訊息，message = "+message);
		
		Map<String, String> errorMsgMap = new HashMap<>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		
		if(name == null || name.trim().length() == 0) {
			errorMsgMap.put("nameEmptyError", "姓名不可以空白喔!");
			System.out.println("姓名不可以空白喔! check");
		}
		
		if(sexual == null || sexual.trim().length() == 0) {
			errorMsgMap.put("sexualEmptyError", "性別不可以空白喔!");
			System.out.println("性別不可以空白喔! check");
		}
		
		if(birthday == null || birthday.trim().length() == 0) {
			errorMsgMap.put("birthdayEmptyError", "生日不可以空白喔!");
			System.out.println("生日不可以空白喔! check");
		}
		
		if(errorMsgMap.isEmpty()) {
			JoinCoworker_Service service = new JoinCoworker_Service();
			try {
				service.sendApplyMail(name, sexual, birthday, message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			errorMsgMap.put("success", "感謝您耐心填寫<br>"
					+ "您的申請已寄出，我們將主動聯絡您^_^<br>"
					+ "Wime全體員工敬上<br>"
					+ "<div class='btnyellow'><a href='/WimeProject/_07_aboutUs/aboutUs.html'>回到關於我</a></div>");
		}
		if(!errorMsgMap.isEmpty()) {
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;
		}
	}
}
