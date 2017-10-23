package _01_register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _00_init.bean.Member_Bean;
import _01_register.model.Register_DAO;

/**
 * Servlet implementation class Registercontroller
 */
@WebServlet("/_01_register/register.do")
public class Register_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
		
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");//文字資料轉內碼
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		Map<String,String> errorMsg = new HashMap<>();
		//準備存放錯誤訊息的Map
		
		Map<String,String> msgOk = new HashMap<>();
		//準備存放註冊成功訊息的Map
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("errorMsg", errorMsg); //顯示錯誤訊息
		session.setAttribute("MsgOk", msgOk);  //顯示正常訊息
		
		session = request.getSession(false);
		if(session != null) {
//		String codeInSession = (String)session.getAttribute("RandomString");
		}
		int id = 0;
		String mail = "";
		String password = "";
		String password2 = "";
		String name = "";
		String gender = "";
		String birthdayStr = "";
		String address = "";
		String phone = "";
		String mobile = "";
		Timestamp registDate = null;
		String verified= "F";
		

		mail = request.getParameter("email");
		password = request.getParameter("password1");
		password2 = request.getParameter("password2");
		name = request.getParameter("name");
		gender = request.getParameter("gender");
		birthdayStr = request.getParameter("birthday");
		address = request.getParameter("address");
		phone = request.getParameter("phone");
		mobile = request.getParameter("mobile");
		
		// 3. 檢查使用者輸入資料
//		if(mail== null || mail.trim().length() == 0){
//			errorMsg.put("errorEmail","請輸入e-mail帳號");
//		}else if (mail.indexOf("@") == -1) {
//			errorMsg.put("errorEmailFormat", "e-mail格式xxx@xxxx.xxx");
//		}
		Pattern mailR = Pattern.compile("^[a-zA-Z0-9._]+@([a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+)+[a-zA-Z]+$");
		Matcher mailM = mailR.matcher(mail);
		if(mailM.find() == false ) {
			errorMsg.put("errorEmailFormat", "請輸入正確的e-mail格式xxx@xxx.xxx");
		}
		
		
//		if(password == null || password.trim().length() < 6){
//			errorMsg.put("errorPassword1", "密碼欄不得小於6位數");
//		}
//		if(password2 == null || password2.trim().length() < 6){
//			errorMsg.put("errorPassword2", "密碼欄不得小於6位數");
//		}
		
		Pattern passwordR = Pattern.compile("^(?=.*[a-zA-Z]+)(?=.*\\d+)[a-zA-Z0-9]{6,16}$");
		Matcher passwordM1 = passwordR.matcher(password);
		Matcher passwordM2 = passwordR.matcher(password);
		if(passwordM1.find() == false ) {
			errorMsg.put("errorPassword1", "請輸入開頭是英文，6~14碼的密碼");
		}
		if(passwordM2.find() == false ) {
			errorMsg.put("errorPassword2", "請輸入開頭是英文，6~14碼的密碼");
		}
		if(password != null && password2 != null) {
			if(password.trim().length() > 5 && password2.trim().length() > 5){
				if(!password.trim().equals(password2.trim())){
					errorMsg.put("errorPasswordEmpty1", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty2", "密碼欄必須與確認欄一致");
				}
			}
		}

		
		if(name == null || name.trim().length() == 0) {
			errorMsg.put("errorName", "請輸入性名");
		}
		if(gender == null || gender.trim().length() == 0) {
			errorMsg.put("errorGender", "請勾選性別");
		}
//		System.out.println("birthdayStr = "+birthdayStr);
		
		java.sql.Date birthday = null;

		if(birthdayStr == null || birthdayStr.trim().length() == 0) {
			try {
			birthday = java.sql.Date.valueOf(birthdayStr);
			System.out.println("birthday = "+birthday);
			} catch (IllegalArgumentException e){
			errorMsg.put("errorBirthday", "請輸入生日");
			}
		
		}else{
			
			birthday = java.sql.Date.valueOf(birthdayStr);
			System.out.println("birthday = "+birthday);

		}
	
		if(address == null || address.trim().length() == 0) {
			errorMsg.put("errorAddress", "請輸入地址");
		}

		Pattern phoneP = Pattern.compile("^\\d{10,}$");
		Matcher phoneM = phoneP.matcher(phone);
		if(phoneM.find() == false ) {
			errorMsg.put("errorPhone", "請輸入10碼數字");
		}
		Pattern mobileP = Pattern.compile("^\\d{10,}$");
		Matcher mobileM = mobileP.matcher(mobile);
		if(mobileM.find() == false ) {
			errorMsg.put("errorMobile", "請輸入10碼數字");
		}
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			out.println(gson.toJson(errorMsg));
			out.close();
			return;
			
		}
		
		// 4. 進行Business Logic運算
		//*檢查帳號是否已經存在
		//*儲存會員資料
			
		try {
			Register_DAO rd = new Register_DAO();
			//寄信檢查，傳mail參數
			String checkUserID;
			checkUserID = rd.checkUserID(mail);
			if (checkUserID == "" & checkUserID.trim().length() == 0) {
				System.out.println("帳號check-->wait寄信");
				errorMsg.put("succesID", "成功");
				out.println(gson.toJson(errorMsg));
				out.close();
			} else {
				errorMsg.put("errorcheckID", "帳號重複，請重新輸入");
				out.println(gson.toJson(errorMsg));
				out.close();
				return;
			}
			// 將MemberBean member立即寫入Database
			Member_Bean mb = new Member_Bean(password2, name, mail, 
						phone, mobile, address, birthday, gender);
			
			int n = rd.saveMember(mb);
			System.out.println("新增會員加入資料 = " + n);
			
			if(n == 1) {
				
				session.setAttribute("LogInOK", mb);
				msgOk.put("InsertOK", "<font color = 'orangr'>新增成功，請開始使用本系統</font>");
//				String result= "驗證信已寄出~";
//				response.sendRedirect(
//					response.encodeRedirectURL("submitMail.jsp?submitResultOK=" + URLEncoder.encode(result,"UTF-8")));
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				

			}else {
				errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
				out.println(gson.toJson(errorMsg));
				out.close();
				return;
			}
			
			// 5.依照 Business Logic 運算結果來挑選適當的畫面

//			if (!errorMsg.isEmpty()) {
//				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				out.println(gson.toJson(errorMsg));
//				out.close();
//				
//			}
		} catch (NamingException e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error );
			out.println(gson.toJson(errorMsg));
			out.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error);
			out.println(gson.toJson(errorMsg));
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			errorMsg.put("errorIDDup", error);
			out.println(gson.toJson(errorMsg));
			out.close();
		}
		
	}
		
	}
		
