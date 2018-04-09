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

	// 发布通知
	public void releaseNotice(Notice notice) throws SQLException, ParseException {
		String sql = "insert into Notice(id,author,authorId,identity,className,releaseDate,title,content) values(?,?,?,?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(notice.getReleaseDate());
		Date sDate = new Date(date.getTime());
		qr.update(sql, notice.getId(), notice.getAuthor(), notice.getAuthorId(), notice.getIdentity(),
				notice.getClassName(), sDate, notice.getTitle(), notice.getContent());
	}

	// 删除通知
	public void deleteNotice(String id) throws SQLException {
		String sql = "delete from Notice where id=?";
		qr.update(sql, id);
	}

	// 管理员查看作业/通知
	public List<Notice> checkNotice() throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	// 教师用户查看作业/通知
	public List<Notice> tCheckNotice(String userId) throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice where authorId=? or identity='通知' order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class), userId);
	}

	// 学生查看作业/通知
	public List<Notice> sCheckNotice(String userId) throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice where identity='通知' or className=(select className from user where userid=?) order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class), userId);
	}

	// 获取通知
	public Notice getNotice(String id) throws SQLException {
		String sql = "select * from Notice where id=?";
		return qr.query(sql, new BeanHandler<Notice>(Notice.class), id);
	}

}
