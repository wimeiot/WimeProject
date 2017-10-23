package _03_ShoppingCart.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import _02_listProducts.model.OrderItem_Bean;


public class ShoppingCart {

	private Map<Integer, OrderItem_Bean> cart = new LinkedHashMap< >();
	public ShoppingCart() {
	}
	public Map<Integer, OrderItem_Bean>  getContent() {  // ${ShoppingCart.content}
		return cart;
	}
	public void addToCart(int productID, OrderItem_Bean  oi) {
		if (oi.getQty() <= 0 ) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( cart.get(productID) == null ) {
		    cart.put(productID, oi);
		} else {
	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			OrderItem_Bean oib = cart.get(productID);
			// 加購的數量：oi.getQty()
			// 原有的數量：oib.getQty()			
			oib.setQty(oi.getQty() + oib.getQty());
		}
	}
	// 修改商品的數量
	public boolean modifyQty(int productID, OrderItem_Bean  oi) {
		if ( cart.get(productID) != null && oi.getQty() > 0 ) {
	       cart.put(productID, oi);
	       return true;
		} else {
		   return false;
		}
	}
	public boolean modifyQty(int productID, int  newQty) {
		if ( cart.get(productID) != null ) {
		   OrderItem_Bean  oi = cart.get(productID);
		   oi.setQty(newQty);
	       //cart.put(bookID, oi);
	       return true;
		} else {
		   return false;
		}
	}
	// 刪除某項商品
	public int deleteproductID(int productID) {
		if ( cart.get(productID) != null ) {
	       cart.remove(productID);  // Map介面的remove()方法
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return cart.size();
	}
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public double getSubtotal(){
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			int price    = cart.get(n).getPrice();
			int    qty      = cart.get(n).getQty();
			subTotal +=  price  * qty;
		}
		return subTotal;
	}
	public void listCart() {
		Set<Integer> set = cart.keySet();
		for(Integer k : set){
			System.out.printf("productID=%3d,  Qty=%3d,  price=%5.2f,  discount=%6.2f\n" , k , cart.get(k).getQty(), cart.get(k).getPrice());
		}
		System.out.println("------------------");
	}
}
