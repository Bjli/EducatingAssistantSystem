package domain;

import java.io.Serializable;

public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
    private String author;
    private String identity;
    private String releaseDate;
    private String title;
    private String content;
    public String getIdentity(){
    	return identity;
    }
    
    public void setIdentity(String identity){
    	this.identity = identity;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
