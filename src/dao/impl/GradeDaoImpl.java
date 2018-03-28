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
	//上传成绩
	public void inputGrade(Grade grade) throws SQLException {
		String sql="insert into Grade(userId,username,workId,workTitle,teacherId,teachername,score,remark) values(?,?,?,?,?,?,?,?)";
		String sql2="update answer set state=? where workId=? and userId=?";
		qr.update(sql,grade.getUserId(),grade.getUserName(),grade.getWorkId(),grade.getWorkTitle(),grade.getTeacherId(),grade.getTeacherName(),grade.getScore(),grade.getRemark());
		qr.update(sql2,"已批改",grade.getWorkId(),grade.getUserId());
	}

	//管理员查看成绩
	public List<Grade> aCheckGrade() throws SQLException {
		String sql="select * from Grade order by teacherid desc";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class));
	}
	
	//教师获取成绩表
	public List<Grade> tCheckGrade(String teacherId) throws SQLException {
		String sql="select * from Grade where teacherId=? order by worktitle desc;";
		return qr.query(sql, new BeanListHandler<Grade>(Grade.class),teacherId);
	}
	
	//学生获取个人成绩详细信息
	public List<Grade> getGrade(String studentID) throws SQLException {
		return qr.query("select * from Grade where userID=?", new BeanListHandler<Grade>(Grade.class),studentID);
	}
	
	//教师通过学号获取个人成绩
	public List<Grade> tGetGradeByUid(String studentID,String teacherId) throws SQLException {
		return qr.query("select * from Grade where userID=? and teacherId=?", new BeanListHandler<Grade>(Grade.class),studentID,teacherId);
	}
	//教师通过作业获取作答详情
	public 	List<Grade> tGetGradeByTitle(String workTitle,String teacherId) throws SQLException {
		return qr.query("select * from Grade where workTitle=? and teacherId=?",new BeanListHandler<Grade>(Grade.class), workTitle,teacherId);
	}


	//删除成绩
	public void deleteGrade(String userid,String workId) throws SQLException {
		String sql="delete from Grade where userId=? and workId=?";
		qr.update(sql,userid,workId);
		String sql2="update answer set state=? where workId=? and userId=?";
		qr.update(sql2,"已提交",workId,userid);
	}

	//修改成绩
	public void modifyGrade(Grade grade) throws SQLException {
		String sql="update Grade set score=?,remark=? where userID=? and workTitle=?";
		qr.update(sql,grade.getScore(),grade.getRemark(), grade.getUserId(), grade.getWorkTitle());
	}
}
