package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import domain.Notice;

public interface NoticeDao {
	void releaseNotice(Notice notice) throws SQLException, ParseException;
	void deleteNotice(String id) throws SQLException;
	List<Notice> checkNotice() throws SQLException;
	List<Notice> tCheckNotice(String userId) throws SQLException;
	Notice getNotice(String id) throws SQLException;
}
