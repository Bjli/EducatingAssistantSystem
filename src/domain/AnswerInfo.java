package domain;

import java.io.Serializable;

public class AnswerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String answerid;//答题标识id
	private String userid;//回答者id
	private String date;//作答时间
	private String content;//作业内容
	private String workid;//作业标识id（发布）
	private String worktitle;//作业标题（发布）
	private String workuser;//作业发布者
	private String state;//1为已提交，2为已批改
	

	public String getWorkuser() {
		return workuser;
	}

	public String getState() {
		return state;
	}

	public void setWorkuser(String workuser) {
		this.workuser = workuser;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWorkid() {
		return workid;
	}

	public String getWorktitle() {
		return worktitle;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public void setWorktitle(String worktitle) {
		this.worktitle = worktitle;
	}

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
