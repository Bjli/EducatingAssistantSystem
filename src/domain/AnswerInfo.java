package domain;

import java.io.Serializable;

public class AnswerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String answerid;//答题标识id
	private String userid;//回答者id
	private String username;//回答者姓名
	private String date;//作答时间
	private String content;//作业内容
	private String workid;//作业标识id（发布）
	private String worktitle;//作业标题（发布）
	private String workuser;//作业发布者
	private String workuserid;//作业发布者id
	private String courseId;//科目标识
	private String courseName;//科目名称
	
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	private String state;// 已提交/已批改
	

	public String getWorkuserid() {
		return workuserid;
	}

	public void setWorkuserid(String workuserid) {
		this.workuserid = workuserid;
	}


	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
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
