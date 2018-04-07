package dao;

import java.sql.SQLException;
import java.util.List;

import domain.ClassInfo;
import domain.User;

public interface UserDao {
	String login(User user) throws SQLException;
	User getUser(String id) throws SQLException;
	void addUser(User user) throws SQLException;
	void deleteUser(User user) throws SQLException;
	List<User> checkUser() throws SQLException;
	void addClass(ClassInfo classInfo) throws SQLException;
	void deleteClass(String classID) throws SQLException;
	List<ClassInfo> getClassName() throws SQLException;
	String findPWD(User user) throws SQLException;
	void modifyPWD(User user) throws SQLException;
}
