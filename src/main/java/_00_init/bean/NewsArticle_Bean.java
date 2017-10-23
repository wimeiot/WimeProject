package _00_init.bean;

import java.sql.Blob;
import java.sql.Timestamp;

public class NewsArticle_Bean {
	private int artID;
	private String title;
	private String article;
	private Timestamp arDate;
	private String creater;
	private Blob artPic;
	private String picloc;
	private String articleLOC;
	
	
	
	public NewsArticle_Bean() {
		super();
	}
	
	

	public String getArticleLOC() {
		return articleLOC;
	}



	public void setArticleLOC(String articleLOC) {
		this.articleLOC = articleLOC;
	}



	public String getPicloc() {
		return picloc;
	}



	public void setPicloc(String picloc) {
		this.picloc = picloc;
	}



	public int getArtID() {
		return artID;
	}

	public void setArtID(int artID) {
		this.artID = artID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Timestamp getArDate() {
		return arDate;
	}

	public void setArDate(Timestamp arDate) {
		this.arDate = arDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Blob getArtPic() {
		return artPic;
	}

	public void setArtPic(Blob artPic) {
		this.artPic = artPic;
	}

	@Override
	public String toString() {
		return "NewsArticle_Bean [artID=" + artID + ", title=" + title + ", article=" + article + ", arDate=" + arDate
				+ ", creater=" + creater + ", artPic=" + artPic + "]";
	}
	
	
	

}
