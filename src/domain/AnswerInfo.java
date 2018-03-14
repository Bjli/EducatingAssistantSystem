package domain;

import java.io.Serializable;

public class AnswerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String answerid;//作业标识id
	private String userid;//回答者id
	private String date;//作答时间
	private String content;//作业内容

	

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setDate(String releaseDate) {
		this.date = releaseDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswerid() {
		return answerid;
	}

	public String getUserid() {
		return userid;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

}
