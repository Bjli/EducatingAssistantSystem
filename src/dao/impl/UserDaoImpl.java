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
	//��¼
	public String login(User user) throws SQLException {
		List<User> loginUser;
		Boolean findUser=false;
		loginUser=qr.query("select * from User where UserID=?",new BeanListHandler<User>(User.class),user.getUserID());
		if(loginUser.isEmpty()){
			return "�����ڸ��û�";
		}
		else{
			for(User dbUser:loginUser){
				if(dbUser.getUserType().equals(user.getUserType())){
					findUser=true;
					if(dbUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
						return "��¼�ɹ�";
					}
					else{
						return "��������";
					}
				}
			}
			if(findUser==false){
				return "�û����ʹ���";
			}
		}
		return null;
	}
	//����û�
	public void addUser(User user) throws SQLException {
		qr.update("insert into User(userID,userName,userType,className,password,phoneNumber,email) values(?,?,?,?,?,?,?)", user.getUserID(),user.getUserName(),user.getUserType(),user.getClassName(),MD5Util.getMD5(user.getPassword()),user.getPhoneNumber(),user.getEmail());
	}
	//ɾ���û�
	public void deleteUser(User user) throws SQLException {
		qr.update("delete from User where UserID=?",user.getUserID());
	}
	//��ȡ�û��б�
	public List<User> checkUser() throws SQLException {
		return qr.query("select * from User", new BeanListHandler<User>(User.class));
	}
	//��ȡ��¼�û�����Ϣ
	public User getUser(String id) throws SQLException {
		List<User> user;
		user = qr.query("select * from User where UserID=?",new BeanListHandler<User>(User.class),id);
		return user.get(0);
	}
	//�һ�����
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
			mail.setHost("smtp.163.com"); // �����ʼ�������,�������163��,�Լ����ҿ���ص�  
			mail.setSender("ljb_nwuer@163.com");  
			mail.setReceiver(mailAdd); // ������  
			mail.setUsername("ljb_nwuer@163.com"); // ��¼�˺�,һ�㶼�Ǻ�������һ����  
			mail.setPassword("123456ljb"); // ����������ĵ�¼����  
			mail.setSubject("�һ�����");  
			mail.setMessage("<br><h3>�����������ǣ�"+pwd+"<br>----------------<br><br> sender by @���<br>"+date+"</h3>");  
			mail.setName("ʵ�鱨���ѧ����ϵͳ");
			new MailUtils().send(mail);  
			return "�������ѷ����������䣡";
		}
		else{
			return "�����������������ϵ����Ա��";
		}
	}
	//�޸�����
	public void modifyPWD(User user) throws SQLException {
		String sql="update User set password=? where userID=? and userType=?";
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		qr.update(sql,user.getPassword(),user.getUserID(),user.getUserType());
	}
	//��ȡ��ǰ�༶�б�
	public List<ClassInfo> getClassList() throws SQLException {
		return  qr.query("select * from class",new BeanListHandler<ClassInfo>(ClassInfo.class));
		
	}
	//ע��༶
	public void addClass(ClassInfo classInfo) throws SQLException {
		qr.update("insert into Class(classid,className) values(?,?)", classInfo.getClassId(),classInfo.getClassName());
	}
	//ɾ���༶��Ϣ
	public void deleteClass(String classID) throws SQLException {
		qr.update("delete from User where className=(select className from class where classID=?)",classID);
		qr.update("delete from Class where classid=?",classID);
	}

	//ע��γ�
	public void addCourse(CourseInfo courseInfo) throws SQLException {
		qr.update("insert into Course(courseId,courseName,teacherId) values(?,?,?)", courseInfo.getCourseId(), courseInfo.getCourseName(),courseInfo.getTeacherId());
	}
	//ɾ���γ���Ϣ
	public void deleteCourse(String courseId) throws SQLException {
		qr.update("delete from Answer where CourseId=?",courseId);
		qr.update("delete from grade where CourseId=?",courseId);
		qr.update("delete from Notice where CourseId=?",courseId);
		qr.update("delete from Course where courseId=?",courseId);
	}
	//��ȡ��ע��γ��б�
	public List<CourseInfo> getCourseList(String teacherId) throws SQLException {
		return qr.query("select * from course where teacherId=?",new BeanListHandler<CourseInfo>(CourseInfo.class),teacherId);
	}
}
