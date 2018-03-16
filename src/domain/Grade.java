package domain;

import java.io.Serializable;

public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;//学号
	private String workId;//对应作业标识
	private String workTitle;//作业标题
	private String teacherId;//相应老师的id
	private int score;//成绩
	private String remark;//评语
 

	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getUserId() {
		return userId;
	}
	public String getWorkId() {
		return workId;
	}
	public int getScore() {
		return score;
	}
	public String getRemark() {
		return remark;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
