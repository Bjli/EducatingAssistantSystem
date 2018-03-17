package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.NoticeDao;
import domain.Notice;
import util.DBCPUtil;

public class NoticeDaoImpl implements NoticeDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//发布通知
	public void releaseNotice(Notice notice) throws SQLException, ParseException {
		String sql="insert into Notice(id,author,authorId,identity,releaseDate,title,content) values(?,?,?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date date = sdf.parse(notice.getReleaseDate()); 
		Date sDate=new Date(date.getTime());
		qr.update(sql,notice.getId(),notice.getAuthor(),notice.getAuthorId(),notice.getIdentity(),sDate,notice.getTitle(),notice.getContent());
	}
	//删除通知
	public void deleteNotice(String id) throws SQLException {
		String sql="delete from Notice where id=?";
		qr.update(sql,id);
	}

	//查看通知
	public List<Notice> checkNotice() throws SQLException {
		String sql="select id,author,identity,releaseDate,title from Notice order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	//获取通知
	public Notice getNotice(String id) throws SQLException {
		String sql="select * from Notice where id=?";
		return qr.query(sql, new BeanHandler<Notice>(Notice.class),id);
	}
}
