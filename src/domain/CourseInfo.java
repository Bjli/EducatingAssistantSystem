package domain;

public class CourseInfo {
	private static final long serialVersionUID = 1L;
	private String courseId;//科目id
	private String courseName;//科目名称
	private String teacherId;//教师号
	
	
	public String getCourseId() {
		return courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	

}
