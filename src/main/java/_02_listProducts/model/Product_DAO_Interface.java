package _02_listProducts.model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

public interface Product_DAO_Interface {
	public int getTotalPages() throws SQLException ;
	public Collection<Product_Bean> getPageBooks() throws SQLException ;
	public int getPageNo();
	public void setPageNo(int pageNo);
	public int getRecordsPerPage() ;
	public void setRecordsPerPage(int recordsPerPage);
	public int getRecordCounts() throws SQLException;
	public void setproductID(int productID);
	public Product_Bean getProduct() throws SQLException;
	public Collection<Product_Bean> getAllBooks() throws SQLException;
	public int updateProduct(Product_Bean bean, InputStream is, long sizeInBytes) throws SQLException;
	public int updateProduct(Product_Bean bean) throws SQLException;
	public Product_Bean queryProduct(int productID) throws SQLException;
	public int deleteProduct(int no) throws SQLException;
	public int insertProduct(Product_Bean bean, InputStream is, long size) throws SQLException;
	public void createProductTable() throws SQLException;
}
