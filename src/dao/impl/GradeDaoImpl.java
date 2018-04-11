package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.GradeDao;
import domain.Grade;
import util.DBCPUtil;

public class GradeDaoImpl implements GradeDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//�ϴ��ɼ�
	public void inputGrade(Grade grade) throws SQLException {
		System.out.println(grade.getCourseId());
		String sql="insert into Grade(userId,username,workId,workTitle,teacherId,teachername,score,remark,courseId,state) values(?,?,?,?,?,?,?,?,?,?)";
		String sql2="update answer set state=? where workId=? and userId=?";
		qr.update(sql,grade.getUserId(),grade.getUserName(),grade.getWorkId(),grade.getWorkTitle(),grade.getTeacherId(),grade.getTeacherName(),grade.getScore(),grade.getRemark(),grade.getCourseId(),"ok");
		qr.update(sql2,"������",grade.getWorkId(),grade.getUserId());
	}

	//����Ա�鿴�ɼ�
	public List<Grade> aCheckGrade() throws SQLException {
		String sql="select * from Grade order by teacherid desc";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class));
	}
	
	//��ʦ��ȡ�ɼ���
	public List<Grade> tCheckGrade(String teacherId, String courseId) throws SQLException {
		String sql="select grade.userid,grade.username,course.courseName,grade.workTitle,grade.score,grade.remark from course,Grade where grade.courseId=course.courseId and grade.teacherId=? and grade.courseId=? order by worktitle desc;";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class),teacherId,courseId);
	}
	
	//ѧ����ȡ���˳ɼ���ϸ��Ϣ
	public List<Grade> getGrade(String studentID) throws SQLException {
		return qr.query("select course.courseName,grade.workTitle,grade.score,grade.teacherName,grade.remark from course,Grade where grade.courseId=course.courseId and grade.userID=? and grade.state = ?", new BeanListHandler<Grade>(Grade.class),studentID, "ok");
	}
	
	//��ʦͨ��ѧ�Ż�ȡ���˳ɼ�
	public List<Grade> tGetGradeByUid(String studentID,String teacherId,String courseId) throws SQLException {
		return qr.query("select grade.userId,grade.userName,course.courseName,grade.workTitle,grade.score,grade.teacherName from course,Grade where grade.courseId=course.courseId and Grade.userID=? and Grade.teacherId=? and grade.courseId=?", new BeanListHandler<Grade>(Grade.class),studentID,teacherId,courseId);
	}
	//��ʦͨ����ҵ��ȡ��������
	public 	List<Grade> tGetGradeByTitle(String workTitle,String teacherId,String courseId) throws SQLException {
		return qr.query("select grade.userId,grade.userName,course.courseName,grade.workTitle,grade.score,grade.teacherName from course,Grade where grade.courseId=course.courseId and grade.workTitle=? and grade.teacherId=? and grade.courseId=?",new BeanListHandler<Grade>(Grade.class), workTitle,teacherId,courseId);
	}

	//ɾ���ɼ�
	public void deleteGrade(String userid,String workId) throws SQLException {
		String sql="delete from Grade where userId=? and workId=?";
		qr.update(sql,userid,workId);
		String sql2="update answer set state=? where workId=? and userId=?";
		qr.update(sql2,"���ύ",workId,userid);
	}

	//�����޸ĳɼ�
	public void applyModifyGrade(String userid,String workId) throws SQLException {
		String sql="update Grade set state=? where userID=? and workId=?";
		qr.update(sql,"applyed",userid, workId);
		String sql2="update answer set state=? where workId=? and userId=?";
		qr.update(sql2,"�����볷��",workId,userid);
	}
}
