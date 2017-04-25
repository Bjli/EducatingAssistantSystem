package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.GradeDao;
import domain.Grade;
import domain.GradeTable;
import util.DBCPUtil;

public class GradeDaoImpl implements GradeDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//�ϴ��ɼ�
	public void inputGrade(Grade grade) throws SQLException {
		String sql="insert into Grade(studentID,gradeType,score) values(?,?,?)";
		qr.update(sql,grade.getStudentID(),grade.getGradeType(),grade.getScore());
	}

	//����Ա�鿴�ɼ�
	public List<Grade> aCheckGrade() throws SQLException {
		String sql="select * from Grade";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class));
	}
	
	//��ʦ��óɼ���
	public List<GradeTable> getGradeTable() throws SQLException {
		String sql="select * from TeacherView";
		return qr.query(sql, new BeanListHandler<GradeTable>(GradeTable.class));
	}
	
	//��ȡ���˳ɼ���ϸ��Ϣ
	public List<Grade> getGrade(String studentID) throws SQLException {
		return qr.query("select * from Grade where studentID=?", new BeanListHandler<Grade>(Grade.class),studentID);
	}

	//ɾ���ɼ�
	public void deleteGrade(Grade grade) throws SQLException {
		String sql="delete from Grade where studentID=? and gradeType=?";
		qr.update(sql,grade.getStudentID(),grade.getGradeType());
	}

	//�޸ĳɼ�
	public void modifyGrade(Grade grade) throws SQLException {
		String sql="update Grade set score=? where studentID=? and gradeType=?";
		qr.update(sql,grade.getScore(),grade.getStudentID(),grade.getGradeType());
	}
}
