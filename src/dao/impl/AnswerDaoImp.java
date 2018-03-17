package dao.impl;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.AnswerDao;
import domain.AnswerInfo;
import domain.Notice;
import util.DBCPUtil;

public class AnswerDaoImp implements AnswerDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

	@Override
	public void addAnswer(AnswerInfo answer) throws SQLException, ParseException {
		// TODO Auto-generated method stub
//		System.out.println("////////////sql");
//		System.out.println(answer.getAnswerid());
//		System.out.println(answer.getUserid());
//		System.out.println(answer.getDate());
//		System.out.println(answer.getWorkid());
//		System.out.println(answer.getWorktitle());
//		System.out.println(answer.getContent());
//		System.out.println("////////////sql");
		String sql = "insert into answer(answerID,userID,userName,date,content,workid,worktitle,workuser,state,workuserid) values(?,?,?,?,?,?,?,?,?,?)";
		Date nowTime = new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		qr.update(sql,answer.getAnswerid(), answer.getUserid(),answer.getUsername(), time.format(nowTime), answer.getContent(),answer.getWorkid(),answer.getWorktitle(),answer.getWorkuser(),"“—Ã·Ωª",answer.getWorkuserid());
	}

	@Override
	public void deleteAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql="delete from answer where answerId=?";
		qr.update(sql,id);

	}

	@Override
	public List<AnswerInfo> checkAnswerS(String userid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select answerid,worktitle,workid,workuser,date,state from answer where userid =? order by date desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class),userid);
	}

	@Override
	public List<AnswerInfo> checkAnswerT(String workuserid) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(workuserid);
		String sql="select answerid,workid,worktitle,userid,username,date,state from answer where workuserid=? order by Date desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class),workuserid);
	}

	@Override
	public AnswerInfo getAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(id);
		String sql="select * from answer where answerId=?";
		return qr.query(sql, new BeanHandler<AnswerInfo>(AnswerInfo.class),id);
	}

}
