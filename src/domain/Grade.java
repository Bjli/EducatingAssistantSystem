package domain;

import java.io.Serializable;

public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;//学号
	private String userName;//姓名
	private String workId;//对应作业标识
	private String workTitle;//作业标题
	private String courseId;//科目标识
	private String courseName;//科目名称
	private String teacherName;//相应老师的姓名
	private String teacherId;//相应老师工号
	private int score;//成绩
	private String remark;//评语
	private String state;//成绩状态
 
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getUserName() {
		return userName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
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
