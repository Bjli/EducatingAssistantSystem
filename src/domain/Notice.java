package domain;

import java.io.Serializable;

public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//通知标识ID
	private String authorId;//发布者id
	private String author;//发布者姓名
    private String identity;//发布者身份
    private String releaseDate;//通知发布时间
    private String title;//通知标题
    private String content;//通知内容
    
    public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
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
