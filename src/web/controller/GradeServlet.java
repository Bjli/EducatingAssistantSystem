package web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import domain.AnswerInfo;
import domain.Grade;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business = new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(GradeServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if ("inputGrade".equals(operation)) {
			inputGrade(request, response);
		}
		if ("deleteGrade".equals(operation)) {
			deleteGrade(request, response);
		}
		if ("modifyGrade".equals(operation)) {
			modifyGrade(request, response);
		}
		// 管理员查看成绩
		if ("aCheckGrade".equals(operation)) {
			aCheckGrade(request, response);
		}
		// 教师查看成绩成绩表
		if ("tCheckGrade".equals(operation)) {
			tCheckGrade(request, response);
		}
		// 学生获得个人成绩
		if ("sCheckGrade".equals(operation)) {
			sCheckGrade(request, response);
		}
		// 教师获得个人成绩
		if ("getStuGrade".equals(operation)) {
			getStuGrade(request, response);
		}
	}

	//
	// 修改成绩
	private void modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentID = request.getParameter("userid");
		String workTitle = request.getParameter("worktitle");
		String score = request.getParameter("modifyscore");
		String remark = request.getParameter("remark");
		Grade grade = new Grade();
		grade.setUserId(studentID);
		grade.setWorkTitle(workTitle);
		grade.setScore(Integer.parseInt(score));
		if (remark.equals("") || remark == null) {
			grade.setRemark(null);
		} else {
			grade.setRemark(remark);
		}
		try {
			business.modifyGrade(grade);
			request.setAttribute("message", "<script type='text/javascript'>alert('修改成功！')</script>");
			request.getRequestDispatcher("../admin/inputGrade.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

	// 删除成绩记录
	private void deleteGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String workId = request.getParameter("workId");
		try {
			business.deleteGrade(userId, workId);
			HttpSession session = request.getSession();
			List<AnswerInfo> nList = null;
			nList = business.checkAnswerT((String) session.getAttribute("userID"));
			request.setAttribute("nList", nList);
			request.getRequestDispatcher("/client/teacher/checkAnswer.jsp").forward(request, response);

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


	// 管理员查看成绩
	private void aCheckGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Grade> gList;
		try {
			gList = business.aCheckGrade();
			request.setAttribute("gList", gList);
			request.getRequestDispatcher("/admin/checkGrade.jsp").forward(request, response);
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

	// 学生查看成绩
	private void sCheckGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentID = (String) session.getAttribute("userID");
		try {
			List<Grade> grade = business.getGrade(studentID);
			request.setAttribute("Grade", grade);
			request.getRequestDispatcher("/client/student/showGrade.jsp").forward(request, response);
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

	// 教师获取个人成绩信息
	private void getStuGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String condition = request.getParameter("condition");
		String ways = request.getParameter("ways");
		HttpSession session = request.getSession();
		String teacherId = (String) session.getAttribute("userID");
		try {
			List<Grade> grade = null;
			if (ways.equals("Title")) {
				grade = business.tGetGradeByTitle(condition, teacherId);
			} else {
				grade = business.tGetGradeByUid(condition, teacherId);
			}
			request.setAttribute("grade", grade);
			request.getRequestDispatcher("/client/teacher/showGrade.jsp").forward(request, response);
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

	// 教师查看成绩表
	private void tCheckGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String teacherId = (String) session.getAttribute("userID");
		try {
			List<Grade> tList = business.tCheckGrade(teacherId);
			request.setAttribute("tList", tList);
			request.getRequestDispatcher("/client/teacher/checkGrade.jsp").forward(request, response);
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

	// 录入成绩
	private void inputGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Grade grade = new Grade();
		try {
			BeanUtils.populate(grade, request.getParameterMap());
			business.inputGrade(grade);
			request.setAttribute("message", "<script type='text/javascript'>alert('添加成功！')</script>");
			request.getRequestDispatcher("/client/teacher/inputGrade.jsp").forward(request, response);
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
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}

}
