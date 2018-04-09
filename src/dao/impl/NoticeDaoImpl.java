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

	// ����֪ͨ
	public void releaseNotice(Notice notice) throws SQLException, ParseException {
		String sql = "insert into Notice(id,author,authorId,identity,className,releaseDate,title,content) values(?,?,?,?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(notice.getReleaseDate());
		Date sDate = new Date(date.getTime());
		qr.update(sql, notice.getId(), notice.getAuthor(), notice.getAuthorId(), notice.getIdentity(),
				notice.getClassName(), sDate, notice.getTitle(), notice.getContent());
	}

	// ɾ��֪ͨ
	public void deleteNotice(String id) throws SQLException {
		String sql = "delete from Notice where id=?";
		qr.update(sql, id);
	}

	// ����Ա�鿴��ҵ/֪ͨ
	public List<Notice> checkNotice() throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	// ��ʦ�û��鿴��ҵ/֪ͨ
	public List<Notice> tCheckNotice(String userId) throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice where authorId=? or identity='֪ͨ' order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class), userId);
	}

	// ѧ���鿴��ҵ/֪ͨ
	public List<Notice> sCheckNotice(String userId) throws SQLException {
		String sql = "select id,author,identity,releaseDate,title from Notice where identity='֪ͨ' or className=(select className from user where userid=?) order by releaseDate desc;";
		return qr.query(sql, new BeanListHandler<Notice>(Notice.class), userId);
	}

	// ��ȡ֪ͨ
	public Notice getNotice(String id) throws SQLException {
		String sql = "select * from Notice where id=?";
		return qr.query(sql, new BeanHandler<Notice>(Notice.class), id);
	}

}
