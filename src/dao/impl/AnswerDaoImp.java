package dao.impl;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import dao.AnswerDao;
import domain.AnswerInfo;
import util.DBCPUtil;
import web.controller.AnswerServlet;

public class AnswerDaoImp implements AnswerDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	private static Logger logger = Logger.getLogger(AnswerServlet.class);
	@Override
	public void addAnswer(AnswerInfo answer) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		List<AnswerInfo> aList = null;
		String sql0 = "select count(*) from answer where userid=? and workid=?";
		aList=qr.query(sql0, new BeanListHandler<AnswerInfo>(AnswerInfo.class), answer.getUserid(),answer.getWorkid());
		if (aList.size() == 0) {
			String sql = "insert into answer(answerID,userID,userName,date,content,workid,worktitle,workuser,state,workuserid) values(?,?,?,?,?,?,?,?,?,?)";
			Date nowTime = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			qr.update(sql, answer.getAnswerid(), answer.getUserid(), answer.getUsername(), time.format(nowTime),answer.getContent(), answer.getWorkid(), answer.getWorktitle(), answer.getWorkuser(), "“—Ã·Ωª",answer.getWorkuserid());
		} else {
			String sql = "update answer set date=?,content=? where userid=? and workid=?";
			Date nowTime = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			qr.update(sql, time.format(nowTime), answer.getContent(), answer.getUserid(), answer.getWorkid());
			logger.info(answer.getUserid()+" do update Answer for:"+answer.getWorkid());
		}
	}

	public void deleteAnswer(String id) throws SQLException {
		String sql = "delete from answer where answerId=?";
		qr.update(sql, id);

	}

	public List<AnswerInfo> checkAnswerS(String userid) throws SQLException {
		String sql = "select answerid,worktitle,workid,workuser,date,state from answer where userid =? order by date desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class), userid);
	}

	public List<AnswerInfo> checkAnswerT(String workuserid) throws SQLException {
		String sql = "select answerid,workid,worktitle,userid,username,date,state from answer where workuserid=? order by worktitle desc;";
		return qr.query(sql, new BeanListHandler<AnswerInfo>(AnswerInfo.class), workuserid);
	}

	public AnswerInfo getAnswer(String id) throws SQLException {
		String sql = "select * from answer where answerId=?";
		return qr.query(sql, new BeanHandler<AnswerInfo>(AnswerInfo.class), id);
	}

}
