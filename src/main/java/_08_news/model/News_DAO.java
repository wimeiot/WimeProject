package _08_news.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _00_init.bean.NewsArticle_Bean;

public class News_DAO {
	
	public News_DAO() {
	}
	// ==================查詢===================
	public List<NewsArticle_Bean> getSelect_All () throws SQLException, NamingException{
		List<NewsArticle_Bean> nblist = null;
		NewsArticle_Bean nb;
		String sql = "SELECT * FROM NEWS";
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rset = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			nblist = new ArrayList<NewsArticle_Bean>();
			while(rset.next()) {
				nb = new NewsArticle_Bean();
				nb.setTitle(rset.getString("TITLE"));
				nb.setArticle(rset.getString("ARTICLE"));
				nb.setArDate(rset.getTimestamp("CREATE_TIME"));
				nb.setCreater(rset.getString("CREATOR"));
				nb.setArtPic(rset.getBlob("PIC"));
				nb.setPicloc(rset.getString("picloc"));
				nb.setArticleLOC(rset.getString("ARTICLE_LOC"));
				nblist.add(nb);
			}
			
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
		return nblist;
	}
	

	// ==================修改===================

}
