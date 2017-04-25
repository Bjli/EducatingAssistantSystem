package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Grade;
import domain.GradeTable;

public interface GradeDao {
	void inputGrade(Grade grade) throws SQLException;
	void deleteGrade(Grade grade) throws SQLException;
	void modifyGrade(Grade grade) throws SQLException;
	List<Grade> aCheckGrade() throws SQLException;
	List<GradeTable> getGradeTable() throws SQLException;
	List<Grade> getGrade(String studentID) throws SQLException;
}
