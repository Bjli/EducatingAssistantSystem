package domain;

import java.io.Serializable;

public class AnswerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String answerid;//��ҵ��ʶid
	private String userid;//�ش���id
	private String date;//����ʱ��
	private String content;//��ҵ����

	

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
