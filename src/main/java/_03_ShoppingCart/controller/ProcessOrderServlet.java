package _03_ShoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import _02_listProducts.model.OrderItem_Bean;
import _03_ShoppingCart.model.OrderDetail_Bean;
import _03_ShoppingCart.model.Order_Bean;
import _03_ShoppingCart.model.Order_DAO;
import _03_ShoppingCart.model.ShoppingCart;

// OrderConfirm.jsp 呼叫本程式
@WebServlet("/_03_ShoppingCart/ProcessOrder.do")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			String finalDecision = request.getParameter("finalDecision");
			HttpSession session = request.getSession(false);
			if (session == null) { // 使用逾時
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}
			Member_Bean mb = (Member_Bean) session.getAttribute("LoginOK");
			if (mb == null) {
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}
			ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
			if (sc == null) {
				// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
				// 導向首頁
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}
			
			// if (finalDecision.equals("CANCEL")){
			// session.removeAttribute("ShoppingCart");
			// response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			// return; // 一定要記得 return
			// }

			// int cmId;

			// int cmId;
			int totalPrice = (int) Math.ceil(sc.getSubtotal());
			java.util.Date d = new java.util.Date();
			java.sql.Timestamp date = new java.sql.Timestamp(d.getTime());
			java.sql.Timestamp date2 = new java.sql.Timestamp(d.getTime());
			java.sql.Timestamp date3 = new java.sql.Timestamp(d.getTime());
			// Timestamp date = mb.getRegistDate();
			// Timestamp date2 = mb.getRegistDate();
			// Timestamp date3 = mb.getRegistDate();

			int ifOut = 1;
			int ifArrive = 1;
			int ifMember = 1;
			String ord_Destination = "Destination";
			String BuyerName = mb.getName();
			String BuyerPhone = mb.getMobile();
			String BuyerEmail = mb.getMail();
			String BuyerAddr = mb.getAddress();
			String ReceiverName = request.getParameter("ReceiverName");
			String ReceiverPhone = request.getParameter("ReceiverPhone");
			String ReceiverAddr = request.getParameter("ReceiverAddr");

			Gson gson = new Gson();
//			PrintWriter out = response.getWriter();
			Map<String, String> errorMsg = new HashMap<>();
			request.setAttribute("err", errorMsg);
//		   	String BuyerName = request.getParameter("BuyerName");
//		   	String BuyerPhone  = request.getParameter("BuyerPhone");
//		   	String BuyerEmail  = request.getParameter("BuyerEmail");
//		   	String BuyerAddr  = request.getParameter("BuyerAddr");
//		   	String ReceiverName  = request.getParameter("ReceiverName");
//		   	String ReceiverPhone  = request.getParameter("ReceiverPhone");
//		   	String ReceiverAddr  = request.getParameter("ReceiverAddr");
		   	

			
			if (ReceiverName == null || ReceiverName.trim().length() == 0) {
				errorMsg.put("errorReceiverName", "姓名欄必須輸入");
			}

			if (ReceiverPhone == null || ReceiverPhone.trim().length() == 0) {
				errorMsg.put("errorReceiverPhone", "電話號碼欄必須輸入");
			}
			if (ReceiverAddr == null || ReceiverAddr.trim().length() == 0) {
				errorMsg.put("errorReceiverAddr", "地址欄必須輸入");
			}
			 
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				out.println(gson.toJson(errorMsg));
//				out.close();
				System.out.println("fuckyou"+request.getAttribute("err"));
				RequestDispatcher rd = request.getRequestDispatcher("OrderConfirm.jsp");
				rd.forward(request, response);
				return;
				
			}
			
			
			int cmId = mb.getId();

			List<OrderDetail_Bean> items = new ArrayList<OrderDetail_Bean>();
			Map<Integer, OrderItem_Bean> cart = sc.getContent();
			Set<Integer> set = cart.keySet();
			for (Integer k : set) {
				OrderItem_Bean oib = cart.get(k);
				String description = oib.getName().substring(0, 2) + " " +
				// 比較上下兩行的寫法
						oib.getDesc() + " " + oib.getName();

				OrderDetail_Bean oiDAO = new OrderDetail_Bean(oib.getProductID(), oib.getName(),oib.getQty(), oib.getPrice(),
						totalPrice);

				System.out.println("cm_id = " + mb.getId());
				System.out.println("ProcessOrder Ordid: " + oiDAO.getOrdId());
				System.out.println("getproductId: " + oiDAO.getproductId());
				System.out.println("getAmount: " + oiDAO.getAmount());
				System.out.println("getUnitPrice: " + oiDAO.getUnitPrice());
				System.out.println("getDiscount: " + totalPrice);
				items.add(oiDAO);
			}
			// OrderBean:封裝一筆訂單資料的容器(包含訂單主檔與訂單明細檔的資料)
			Order_Bean ob = new Order_Bean(cmId, totalPrice, date, ord_Destination, BuyerName, BuyerPhone, BuyerEmail,
					BuyerAddr, ReceiverName, ReceiverPhone, ReceiverAddr, date2, ifOut, date3, ifArrive, ifMember,
					items);
			Order_DAO order = new Order_DAO();
			order.insertOrder(ob);
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL("../_04_checkoutComplete/ThanksForOrdering.jsp"));
		} catch (NamingException e) {
			throw new ServletException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}