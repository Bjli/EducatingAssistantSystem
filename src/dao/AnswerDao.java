package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import domain.AnswerInfo;

public interface AnswerDao {
	void addAnswer(AnswerInfo answer) throws SQLException, ParseException;
	void deleteAnswer(String id) throws SQLException;
	List<AnswerInfo> checkAnswerS(String id) throws SQLException;
	List<AnswerInfo> checkAnswerT(String id) throws SQLException;
	AnswerInfo getAnswer(String id) throws SQLException;

}
