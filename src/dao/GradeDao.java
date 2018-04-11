package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Grade;

public interface GradeDao {
	void inputGrade(Grade grade) throws SQLException;
	void deleteGrade(String userid,String workId) throws SQLException;
	void applyModifyGrade(String userid,String workId) throws SQLException;
	List<Grade> aCheckGrade() throws SQLException;
	List<Grade> tCheckGrade(String teacherId, String courseId) throws SQLException;
	List<Grade> tGetGradeByUid(String studentID,String teacherId,String courseId) throws SQLException;
	List<Grade> getGrade(String StudentId) throws SQLException;
	List<Grade> tGetGradeByTitle(String workTitle,String teacherId,String courseId) throws SQLException;
}
