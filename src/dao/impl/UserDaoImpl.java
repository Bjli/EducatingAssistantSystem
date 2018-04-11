package dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.UserDao;
import domain.ClassInfo;
import domain.CourseInfo;
import domain.Mail;
import domain.User;
import util.DBCPUtil;
import util.MD5Util;
import util.MailUtils;
import util.PwdGenerator;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//登录
	public String login(User user) throws SQLException {
		List<User> loginUser;
		Boolean findUser=false;
		loginUser=qr.query("select * from User where UserID=?",new BeanListHandler<User>(User.class),user.getUserID());
		if(loginUser.isEmpty()){
			return "不存在该用户";
		}
		else{
			for(User dbUser:loginUser){
				if(dbUser.getUserType().equals(user.getUserType())){
					findUser=true;
					if(dbUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
						return "登录成功";
					}
					else{
						return "密码有误";
					}
				}
			}
			if(findUser==false){
				return "用户类型错误";
			}
		}
		return null;
	}
	//添加用户
	public void addUser(User user) throws SQLException {
		qr.update("insert into User(userID,userName,userType,className,password,phoneNumber,email) values(?,?,?,?,?,?,?)", user.getUserID(),user.getUserName(),user.getUserType(),user.getClassName(),MD5Util.getMD5(user.getPassword()),user.getPhoneNumber(),user.getEmail());
	}
	//删除用户
	public void deleteUser(User user) throws SQLException {
		qr.update("delete from User where UserID=?",user.getUserID());
	}
	//获取用户列表
	public List<User> checkUser() throws SQLException {
		return qr.query("select * from User", new BeanListHandler<User>(User.class));
	}
	//获取登录用户的信息
	public User getUser(String id) throws SQLException {
		List<User> user;
		user = qr.query("select * from User where UserID=?",new BeanListHandler<User>(User.class),id);
		return user.get(0);
	}
	//找回密码
	public String findPWD(User user) throws SQLException {
		User dbUser=null;
		String pwd=PwdGenerator.getPwd();
		user.setPassword(pwd);
		
		String mailAdd=user.getEmail();
		String sql="select * from User where userID=? and userType=?";
		dbUser=qr.query(sql, new BeanHandler<User>(User.class),user.getUserID(),user.getUserType());
		if(dbUser.getEmail().equals(user.getEmail())){
			modifyPWD(user);
			Date date=new Date();
			Mail mail = new Mail(); 
			mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
			mail.setSender("ljb_nwuer@163.com");  
			mail.setReceiver(mailAdd); // 接收人  
			mail.setUsername("ljb_nwuer@163.com"); // 登录账号,一般都是和邮箱名一样吧  
			mail.setPassword("123456ljb"); // 发件人邮箱的登录密码  
			mail.setSubject("找回密码");  
			mail.setMessage("<br><h3>您的新密码是："+pwd+"<br>----------------<br><br> sender by @李吉波<br>"+date+"</h3>");  
			mail.setName("实验报告教学管理系统");
			new MailUtils().send(mail);  
			return "新密码已发至您的邮箱！";
		}
		else{
			return "请输入您的邮箱或联系管理员！";
		}
	}
	//修改密码
	public void modifyPWD(User user) throws SQLException {
		String sql="update User set password=? where userID=? and userType=?";
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		qr.update(sql,user.getPassword(),user.getUserID(),user.getUserType());
	}
	//获取当前班级列表
	public List<ClassInfo> getClassList() throws SQLException {
		return  qr.query("select * from class",new BeanListHandler<ClassInfo>(ClassInfo.class));
		
	}
	//注册班级
	public void addClass(ClassInfo classInfo) throws SQLException {
		qr.update("insert into Class(classid,className) values(?,?)", classInfo.getClassId(),classInfo.getClassName());
	}
	//删除班级信息
	public void deleteClass(String classID) throws SQLException {
		qr.update("delete from User where className=(select className from class where classID=?)",classID);
		qr.update("delete from Class where classid=?",classID);
	}

	//注册课程
	public void addCourse(CourseInfo courseInfo) throws SQLException {
		qr.update("insert into Course(courseId,courseName,teacherId) values(?,?,?)", courseInfo.getCourseId(), courseInfo.getCourseName(),courseInfo.getTeacherId());
	}
	//删除课程信息
	public void deleteCourse(String courseId) throws SQLException {
		qr.update("delete from Answer where CourseId=?",courseId);
		qr.update("delete from grade where CourseId=?",courseId);
		qr.update("delete from Notice where CourseId=?",courseId);
		qr.update("delete from Course where courseId=?",courseId);
	}
	//获取已注册课程列表
	public List<CourseInfo> getCourseList(String teacherId) throws SQLException {
		return qr.query("select * from course where teacherId=?",new BeanListHandler<CourseInfo>(CourseInfo.class),teacherId);
	}
}
