package domain;

public class CourseInfo {
	private static final long serialVersionUID = 1L;
	private String courseId;//��Ŀid
	private String courseName;//��Ŀ����
	private String teacherId;//��ʦ��
	
	
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
