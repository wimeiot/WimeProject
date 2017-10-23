package _07_aboutUs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _07_aboutUs.model.AboutUs_Service;

/**
 * Servlet implementation class AboutUs_Servlet
 */
@WebServlet("/_07_aboutUs/AboutUs_Servlet")
public class AboutUs_Servlet extends HttpServlet {
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

		// =======================存放錯誤訊息的購物車========================
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);// "識別字串"

		// =======================讀取表格資料========================
		String identity = request.getParameter("identity");
		String companyName = request.getParameter("companyName");
		String deitoria = request.getParameter("deitoria");
		String capital = request.getParameter("capital");
		String sexaul = request.getParameter("sexaul");
		String principle = request.getParameter("principle");
		String contecter = request.getParameter("contecter");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		System.out.println("=============打印結果==============");
		System.out.println("1.身分，identity = " + identity);
		System.out.println("2.公司名稱，companyName = " + companyName);
		System.out.println("3.公司統編，deitoria = " + deitoria);
		System.out.println("4.資本額，capital = " + capital);
		System.out.println("5.負責人，principle = " + principle);
		System.out.println("6.負責人性別，sexaul = " + sexaul);
		System.out.println("7.聯絡人，contecter = " + contecter);
		System.out.println("8.聯絡電話，phone = " + phone);
		System.out.println("9.聯絡信箱，email = " + email);
		System.out.println("留言版，message = " + message);

		// =======================若空白放錯誤訊息到errorMsgMap========================
		// =======================1.公司身分========================
		if (identity == null || identity.trim().length() == 0) {
			errorMsgMap.put("identityEmptyError", "公司身分欄位不可以空白喔!");
			System.out.println("公司身分欄位不可以空白喔! check");
		}
		
		// =======================2.公司名稱========================
		if (companyName == null || companyName.trim().length() == 0) {
			errorMsgMap.put("companyNameEmptyError", "公司名稱不可以空白喔!");
			System.out.println("公司名稱不可以空白喔! check");
		}
		
		// =======================3.公司統編========================
		if (deitoria == null || deitoria.trim().length() == 0) {
			errorMsgMap.put("deitoriaEmptyError", "公司統編不可以空白喔!");
			System.out.println("公司統編不可以空白喔! check");
		}
		
		// =======================4.資本額========================
		if (capital == null || capital.trim().length() == 0) {
			errorMsgMap.put("capitalEmptyError", "公司資本額不可以空白喔!");
			System.out.println("公司資本額不可以空白喔! check");
		}
		
		// =======================5.負責人========================
		if (principle == null || principle.trim().length() == 0) {
			errorMsgMap.put("principleEmptyError", "公司負責人不可以空白喔!");
			System.out.println("公司負責人不可以空白喔! check");
		}
		
		// =======================6.負責人性別========================
		if (sexaul == null || sexaul.trim().length() == 0) {
			errorMsgMap.put("sexaulEmptyError", "負責人性別不可以空白喔!");
			System.out.println("負責人性別不可以空白喔! check");
		}
		
		// =======================7.聯絡人========================
		if (contecter == null || contecter.trim().length() == 0) {
			errorMsgMap.put("contecterEmptyError", "公司聯絡人不可以空白喔!");
			System.out.println("公司聯絡人不可以空白喔! check");
		}
		
		// =======================8.聯絡電話========================
		if (phone == null || phone.trim().length() == 0) {
			errorMsgMap.put("phoneEmptyError", "連絡電話不可以空白喔!");
			System.out.println("聯絡電話不可以空白喔! check");
		}
		
		// =======================9.聯絡信箱========================
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("emailEmptyError", "聯絡信箱人不可以空白喔!");
			System.out.println("聯絡信箱不可以空白喔! check");
		}
		// =======================如果 errorMsgMap是空的========================
		if (errorMsgMap.isEmpty()) {
			AboutUs_Service service = new AboutUs_Service();
			try {
				service.sendApplyMail(identity, companyName, deitoria, capital, sexaul, principle, contecter, phone, email, message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			errorMsgMap.put("success", "感謝您耐心填寫<br>"
					+ "您的申請已寄出，我們將主動聯絡您^_^<br>"
					+ "Wime全體員工敬上<br>"
					+ "<div class='btnyellow'><a href='/WimeProject/_07_aboutUs/aboutUs.html'>回到關於我</a></div>");
		}
		//=======================送出結果========================
		if(!errorMsgMap.isEmpty()) {
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;
		}
	}
}
