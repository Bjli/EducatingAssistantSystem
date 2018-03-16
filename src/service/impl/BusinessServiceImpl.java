package service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import dao.AnswerDao;
import dao.FileInfoDao;
import dao.GradeDao;
import dao.NoticeDao;
import dao.UserDao;
import dao.impl.AnswerDaoImp;
import dao.impl.FileInfoDaoImpl;
import dao.impl.GradeDaoImpl;
import dao.impl.NoticeDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AnswerInfo;
import domain.FileInfo;
import domain.Grade;
import domain.Notice;
import domain.User;
import service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	//系统管理模块
	private UserDao uDao=new UserDaoImpl();
	public String login(User user) throws SQLException {
		return uDao.login(user);
	}
	public void addUser(User user) throws SQLException {
		uDao.addUser(user);
	}
	public void deleteUser(User user) throws SQLException {
		uDao.deleteUser(user);
	}
	public List<User> checkUser() throws SQLException {
		return uDao.checkUser();
	}
	public String findPWD(User user) throws SQLException {
		return uDao.findPWD(user);
	}
	public void modifyPWD(User user) throws SQLException {
		uDao.modifyPWD(user);
	}
	
	//资源管理模块
	private FileInfoDao fDao=new FileInfoDaoImpl();
	public void uploadFile(FileInfo fileinfo, FileItem item) throws SQLException, ParseException{
		fDao.uploadFile(fileinfo, item);
	}
	public void deleteFile(String id) throws SQLException {
		fDao.deleteFile(id);
	}
	public void downloadFile(String id, OutputStream os) throws FileNotFoundException, SQLException, IOException{
		fDao.downloadFile(id, os);
	}
	public List<FileInfo> checkFile() throws SQLException {
		return fDao.checkFile();
	}

	//通知管理模块
	private NoticeDao nDao=new NoticeDaoImpl();
	public void releaseNotice(Notice notice) throws SQLException, ParseException {
		nDao.releaseNotice(notice);
	}
	public void deleteNotice(String id) throws SQLException {
		nDao.deleteNotice(id);
	}
	public List<Notice> checkNotice() throws SQLException {
		return nDao.checkNotice();
	}
	public Notice getNotice(String id) throws SQLException {
		return nDao.getNotice(id);
	}
	
	//成绩管理
	private GradeDao gDao=new GradeDaoImpl();
	public void inputGrade(Grade grade) throws SQLException {
		gDao.inputGrade(grade);
	}
	public void deleteGrade(String userid,String workId) throws SQLException {
		gDao.deleteGrade(userid,workId);
		
	}
	public void modifyGrade(Grade grade) throws SQLException {
		gDao.modifyGrade(grade);
	}
	public List<Grade> aCheckGrade() throws SQLException {
		return gDao.aCheckGrade();
	}
	
	public List<Grade> tCheckGrade(String teacherId) throws SQLException {
		return gDao.tCheckGrade(teacherId);
	}

	public List<Grade> tGetGradeByUid(String studentID,String teacherId) throws SQLException {
		return gDao.tGetGradeByUid(studentID, teacherId);
	}
	
	public List<Grade> getGrade(String studentID) throws SQLException {
		return gDao.getGrade(studentID);
	}
	
	public List<Grade> tGetGradeByTitle(String workTitle,String teacherId) throws SQLException {
		return gDao.tGetGradeByTitle(workTitle,teacherId);
	}

	
	//答题模块
	private AnswerDao aDao = new AnswerDaoImp();
	public void addAnswer(AnswerInfo answer) throws SQLException, ParseException {
		 aDao.addAnswer(answer);
	}
	public void deleteAnswer(String id) throws SQLException {
		aDao.deleteAnswer(id);
	}
	public List<AnswerInfo> checkAnswerS(String id) throws SQLException {
		return aDao.checkAnswerS(id);
	}
	public List<AnswerInfo> checkAnswerT(String id) throws SQLException {
		return aDao.checkAnswerT(id);
	}
	public AnswerInfo getAnswer(String id) throws SQLException {
		return aDao.getAnswer(id);
	}

}
