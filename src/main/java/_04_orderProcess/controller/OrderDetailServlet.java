package _04_orderProcess.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _03_ShoppingCart.model.OrderDetail_Bean;
import _03_ShoppingCart.model.Order_Bean;
import _03_ShoppingCart.model.Order_DAO;
@WebServlet("/_04_orderProcess/orderDetail.do")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String orderNo = request.getParameter("orderNo");
		int no  = Integer.parseInt(orderNo.trim());   // ###
		try {
//			OrderDAO ordDAO = new OrderDAO();			
//			OrderBean ob = ordDAO.getOrder(no);
			Order_DAO ordDAO = new Order_DAO();
			Order_Bean ob = new Order_Bean();
			request.setAttribute("Order_Bean", ob);
			RequestDispatcher  rd = request.getRequestDispatcher("ShowOrderDetail.jsp");
			rd.forward(request, response);
			return;
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	public void displayOrderBean(Order_Bean ob) {
//		System.out.println("ob.getOrderNo()=" + ob.getOrdId());
//		System.out.println("ob.getUserID()=" + ob.getUserId());
//		System.out.println("ob.getDestination=" + ob.getOrdDestination());
//		System.out.println("ob.getOrderDate=" + ob.getOrdDate());
//		System.out.println("ob.getTotalAmount=" + ob.getTotalPrice());
//		System.out.println("ob.getReceiverName=" + ob.getReceiverName());
//		System.out.println("ob.getReceiverAddress=" + ob.getReceiverAddress());
//		System.out.println("ob.getIfMember=" + ob.getIfMember());
		System.out.println("==============訂單明細=================");
		List<OrderDetail_Bean> items = ob.getItems();
		for (OrderDetail_Bean oib : items) {
			System.out.println("---------------一筆明細---------------");
//			System.out.println("   oib.getSeqno()=" + oib.getSeqno());
//			System.out.println("   oib.getOrderNo()=" + oib.getOrderNo());
//			System.out.println("   oib.getBookId()=" + oib.getproductId());
//			System.out.println("   oib.getDescription()="
//					+ oib.getDescription());
//			System.out.println("   oib.getAmount()=" + oib.getAmount());
//			System.out.println("   oib.getUnitPrice()=" + oib.getUnitPrice());
//			System.out.println("   oib.getDiscount()=" + oib.getDiscount());
		}
	}
}