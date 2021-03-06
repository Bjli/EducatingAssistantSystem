package web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import domain.ClassInfo;
import domain.CourseInfo;
import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import util.IdGenerator;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business = new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(UserServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if ("login".equals(operation)) {
			login(request, response);
		}
		if ("getClassName".equals(operation)) {
			getClassList(request, response);
		}
		if ("deleteClass".equals(operation)) {
			deleteClass(request, response);
		}
		if ("addClass".equals(operation)) {
			addClass(request, response);
		}
		if ("addCourse".equals(operation)) {
			addCourse(request, response);
		}
		if ("deleteCourse".equals(operation)) {
			deleteCourse(request, response);
		}
		if ("getCourseList".equals(operation)) {
			getCourseList(request, response);
		}
		if ("logout".equals(operation)) {
			logout(request, response);
		}
		if ("addUser".equals(operation)) {
			addUser(request, response);
		}
		if ("checkUser".equals(operation)) {
			checkUser(request, response);
		}
		if ("deleteUser".equals(operation)) {
			deleteUser(request, response);
		}
		if ("findPWD".equals(operation)) {
			findPWD(request, response);
		}
		if ("modifyPWD".equals(operation)) {
			modifyPWD(request, response);
		}
	}

	// 注册课程信息
	private void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CourseInfo courseInfo = new CourseInfo();
		String courseName = request.getParameter("courseName");
		courseInfo.setCourseName(courseName);
		courseInfo.setTeacherId((String) session.getAttribute("userID"));
		courseInfo.setCourseId(IdGenerator.genPrimaryKey());
		try {
			business.addCourse(courseInfo);
			request.setAttribute("message", "<script type='text/javascript'>alert('课程注册成功！')</script>");
			request.getRequestDispatcher("../client/teacher/addCourse.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 删除课程信息
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String courseId = request.getParameter("courseId");
		try {
			business.deleteCourse(courseId);
			logger.info(session.getAttribute("userID") + " do delete course: " + courseId);
			List<CourseInfo> cList = null;
			cList = business.getCourseList((String) session.getAttribute("userID"));
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("../client/teacher/checkCourse.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 获取课程列表
	private void getCourseList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CourseInfo> cList = null;
		try {
			cList = business.getCourseList((String) session.getAttribute("userID"));
			request.setAttribute("cList", cList);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		String op = request.getParameter("op");
		if (op.equals("mCourse")) {
			request.getRequestDispatcher("../client/teacher/checkCourse.jsp").forward(request, response);
		} else if (op.equals("sGrade")) {
			request.getRequestDispatcher("../client/teacher/seachGrade.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("../client/teacher/TempcheckGrade.jsp").forward(request, response);
		}
	}

	// 删除班级信息
	private void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String classID = request.getParameter("classId");
		try {
			business.deleteClass(classID);
			logger.info(session.getAttribute("userID") + " do delete class: " + classID);
			List<ClassInfo> cList = null;
			cList = business.getClassName();
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("../admin/checkClass.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}

	}

	// 注册班级信息
	private void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassInfo classInfo = new ClassInfo();
		String className = request.getParameter("className");
		classInfo.setClassName(className);
		classInfo.setClassId(IdGenerator.genPrimaryKey());
		try {
			business.addClass(classInfo);
			request.setAttribute("message", "<script type='text/javascript'>alert('注册成功！')</script>");
			request.getRequestDispatcher("../admin/addClass.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 获取班级列表
	private void getClassList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ClassInfo> cList = null;
		try {
			cList = business.getClassName();
			request.setAttribute("cList", cList);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		String op = request.getParameter("op");
		if (op.equals("rNotice")) {
			HttpSession session = request.getSession();
			List<CourseInfo> courseList = null;
			try {
				courseList = business.getCourseList((String) session.getAttribute("userID"));
			} catch (SQLException e) {
				logger.error(e.getMessage());
				String errorMsg = "数据库操作异常，请重试";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("../common/error.jsp").forward(request, response);
			}
			request.setAttribute("courseList", courseList);
			request.getRequestDispatcher("../common/releaseNotice.jsp").forward(request, response);
		} else if (op.equals("adduser")) {
			request.getRequestDispatcher("../admin/addUser.jsp").forward(request, response);
		} else if (op.equals("mCName")) {
			request.getRequestDispatcher("../admin/checkClass.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("../common/register.jsp").forward(request, response);
		}
	}

	// 注销登录
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);// 防止创建Session
		try {
			if (session == null) {
				response.sendRedirect("/TeachingAssistant/common/login.jsp");
			} else {
				session.removeAttribute("userID");
				session.removeAttribute("usetType");
				session.invalidate();
				response.sendRedirect("/TeachingAssistant/common/login.jsp");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 修改密码
	private void modifyPWD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String userType = (String) session.getAttribute("userType");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserID(userID);
		user.setUserType(userType);
		user.setPassword(password);
		try {
			business.modifyPWD(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('修改成功')</script>");
			logger.info(userID + ": do modifyPWD success.");
			request.getRequestDispatcher("/common/modifyPWD.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 找回密码
	private void findPWD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			business.findPWD(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('密码已发送至你的邮箱！')</script>");
			logger.info(user.getUserID() + ": do findPWD success.");
			request.getRequestDispatcher("/common/login.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 用户登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		String loginRes = null;
		try {
			BeanUtils.populate(user, request.getParameterMap());
			loginRes = business.login(user);
			if (loginRes.equals("登录成功")) {
				User login = business.getUser(user.getUserID());
				logger.info(login.getUserID() + "/" + login.getUserName() + ":login success");
				HttpSession session = request.getSession(true);
				// session.setMaxInactiveInterval(60);
				session.setAttribute("userID", login.getUserID());
				session.setAttribute("userName", login.getUserName());
				session.setAttribute("userType", login.getUserType());
				if (user.getUserType().equals("管理员")) {
					response.sendRedirect("/TeachingAssistant/admin/mainFrm.jsp");
				} else if (user.getUserType().equals("教师")) {
					response.sendRedirect("/TeachingAssistant/client/teacher/mainFrm.jsp");
				} else {
					response.sendRedirect("/TeachingAssistant/client/student/mainFrm.jsp");
				}
			} else if (loginRes.equals("密码有误")) {
				request.setAttribute("message", "<script type='text/javascript'>alert('密码有误')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			} else if (loginRes.equals("不存在该用户")) {
				request.setAttribute("message",
						"<script type='text/javascript'>alert('用户不存在，请检查用户编号或联系管理员！')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			} else if (loginRes.equals("用户类型错误")) {
				request.setAttribute("message", "<script type='text/javascript'>alert('请选择正确的用户类型！')</script>");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 删除用户
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		String userType = request.getParameter("userType");
		User user = new User();
		user.setUserID(userID);
		user.setUserType(userType);
		try {
			business.deleteUser(user);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		logger.info((String) session.getAttribute("userID") + ": do deleteUser:" + userID + " success.");
		checkUser(request, response);
	}

	// 查看所有用户
	private void checkUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> uList;
		try {
			uList = business.checkUser();
			request.setAttribute("uList", uList);
			request.getRequestDispatcher("/admin/checkUser.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 添加用户
	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userID");
		try {
			BeanUtils.populate(user, request.getParameterMap());
			business.addUser(user);
			request.setAttribute("message", "<script type='text/javascript'>alert('注册成功！')</script>");
			logger.info("User:" + user.getUserID() + "register.");
			if (userId == "" || userId == null) {
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/addUser.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
