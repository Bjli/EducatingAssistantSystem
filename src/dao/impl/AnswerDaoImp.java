package dao.impl;

import java.sql.Date;
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
		System.out.println("////////////sql");
		System.out.println(answer.getAnswerid());
		System.out.println(answer.getUserid());
		System.out.println(answer.getDate());
		System.out.println(answer.getContent());
		System.out.println("////////////sql");
		String sql = "insert into answer(answerID,userID,date,connect) values(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(answer.getDate());
		Date sDate = new Date(date.getTime());
		qr.update(sql,answer.getAnswerid(), answer.getUserid(), sDate, answer.getContent());
	}

	@Override
	public void deleteAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql="delete from answer where answerId=?";
		qr.update(sql,id);

	}

	@Override
	public List<AnswerInfo> checkAnswerS(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select answerid,userid,Date from answer where userid = ? order by Date desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class),id);
	}

	@Override
	public List<AnswerInfo> checkAnswerT() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select answerid,userid,Date from answer order by Date desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class));
	}

	@Override
	public AnswerInfo getAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from answer where answerId=?";
		return qr.query(sql, new BeanHandler<AnswerInfo>(AnswerInfo.class),id);
	}

}
