package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import domain.FileInfo;
import domain.Grade;
import domain.GradeTable;
import domain.Notice;
import domain.User;

public interface BusinessService {
	//ϵͳ����ģ��
	String login(User user) throws SQLException;
	void addUser(User user) throws SQLException;
	void deleteUser(User user) throws SQLException;
	List<User> checkUser() throws SQLException;
	String findPWD(User user) throws SQLException;
	void modifyPWD(User user) throws SQLException;
	//��Դ����ģ��
	void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException;
	void deleteFile(String id) throws SQLException;
	void downloadFile(String id, OutputStream os) throws FileNotFoundException, SQLException, IOException;
	List<FileInfo> checkFile() throws SQLException;
	//֪ͨ����ģ��
	void releaseNotice(Notice notice) throws SQLException, ParseException;
	void deleteNotice(String id) throws SQLException;
	List<Notice> checkNotice() throws SQLException;
	Notice getNotice(String id) throws SQLException;
	//�ɼ�����ģ��
	void inputGrade(Grade grade) throws SQLException;
	void deleteGrade(Grade grade) throws SQLException;
	void modifyGrade(Grade grade) throws SQLException;
	List<Grade> aCheckGrade() throws SQLException;
	List<GradeTable> getGradeTable() throws SQLException;
	List<Grade> getGrade(String studentID) throws SQLException;
}
