package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import domain.AnswerInfo;
import domain.ClassInfo;
import domain.CourseInfo;
import domain.FileInfo;
import domain.Grade;
import domain.Notice;
import domain.User;

public interface BusinessService {
	//ϵͳ����ģ��
	String login(User user) throws SQLException;
	void addUser(User user) throws SQLException;
	User getUser(String userid) throws SQLException;
	void deleteUser(User user) throws SQLException;
	List<User> checkUser() throws SQLException;
	void addClass(ClassInfo classInfo) throws SQLException;
	void deleteClass(String classID) throws SQLException;
	List<ClassInfo> getClassName() throws SQLException;
	void addCourse(CourseInfo courseInfo) throws SQLException;
	void deleteCourse(String courseId) throws SQLException;
	List<CourseInfo> getCourseList(String teacherId) throws SQLException;
	String findPWD(User user) throws SQLException;
	void modifyPWD(User user) throws SQLException;
	//��Դ����ģ��
	void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException;
	void deleteFile(String id) throws SQLException;
	void downloadFile(String id, OutputStream os) throws FileNotFoundException, SQLException, IOException;
	List<FileInfo> checkFile() throws SQLException;
	//֪ͨ����ģ��
	void releaseNotice(Notice notice) throws SQLException, ParseException;
	void deleteNotice(String id) throws SQLException;
	List<Notice> checkNotice() throws SQLException;
	List<Notice> tCheckNotice(String userId) throws SQLException;
	List<Notice> sCheckNotice(String userId) throws SQLException;
	Notice getNotice(String id) throws SQLException;
	//�ɼ�����ģ��
	void inputGrade(Grade grade) throws SQLException;
	void deleteGrade(String userid,String workId) throws SQLException;
	void applyModifyGrade(String userid,String workId) throws SQLException;
	List<Grade> aCheckGrade() throws SQLException;
	List<Grade> tCheckGrade(String teacherId,String courseId) throws SQLException;
	List<Grade> tGetGradeByUid(String studentID,String teacherId,String courseId) throws SQLException;
	List<Grade> getGrade(String studentID) throws SQLException;
	List<Grade> tGetGradeByTitle(String workTitle,String teacherId,String courseId) throws SQLException;
	
	
	//����ģ��
	void addAnswer(AnswerInfo answer) throws SQLException, ParseException;
	void deleteAnswer(String id) throws SQLException;
	List<AnswerInfo> checkAnswerS(String id) throws SQLException;
	List<AnswerInfo> checkAnswerT(String id) throws SQLException;
	AnswerInfo getAnswer(String id) throws SQLException;
}
