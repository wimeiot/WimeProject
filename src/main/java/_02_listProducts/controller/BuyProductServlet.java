package _02_listProducts.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _02_listProducts.model.OrderItem_Bean;
import _03_ShoppingCart.model.ShoppingCart;
@WebServlet("/_02_listProducts/BuyProduct.do")
public class BuyProductServlet extends HttpServlet {
	// 當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyProductServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_00_login/Login.jsp");
			rd.forward(request, response);
			return;
		}
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
		// 如果找不到ShoppingCart物件
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 將此新建ShoppingCart的物件放到session物件內
			session.setAttribute("ShoppingCart", cart);   // ${ShoppingCart.zzz}
		}
		String a            = request.getParameter("a");//判斷頁面跳轉
		String name 		= request.getParameter("name");
		String pageNo 		= request.getParameter("pageNo");
		String qtyStr 		= request.getParameter("qty");
		String idStr 		= request.getParameter("productID");
		String priceStr 	= request.getParameter("price");
//		String numberStr 	= request.getParameter("number");
		
		
		if (pageNo == null){
			pageNo = "1";
		}
		int qty = 0 ; 
		int productID = 0 ;
		int price = 0 ;
//		int number=0;
		
		
		try{
			// 進行資料型態的轉換
			qty = Integer.parseInt(qtyStr.trim());
			productID = Integer.parseInt(idStr.trim());
			price = Integer.parseInt(priceStr.trim());
//			number = Integer.parseInt(numberStr.trim());
			
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		// 將訂單資料封裝到OrderItemBean內
		OrderItem_Bean oi = new OrderItem_Bean(name, qty, productID, price);
		// 將OrderItemBean加入ShoppingCart的物件內
		cart.addToCart(productID, oi);
		
		if(a.equals("a")){
		RequestDispatcher rd = request.getRequestDispatcher("/_02_listProducts/productInformation.jsp?id="+productID);
		rd.forward(request, response);
		

		return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/_02_listProducts/DisplayPageProducts?pageNo=" + pageNo);
			rd.forward(request, response);
			
			return;
		}
	}
}