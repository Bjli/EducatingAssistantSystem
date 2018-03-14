package web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import domain.AnswerInfo;
import domain.Notice;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import util.IdGenerator;

public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business = new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(AnswerServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if("addAnswer".equals(operation)){
			addAnswer(request,response);
		}
		if("checkAnswer".equals(operation)){
			checkAnswer(request,response);
		}
		if("deleteAnswer".equals(operation)){
			deleteAnswer(request,response);
		}
	}

	// 删除特定id的作业记录
	private void deleteAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			business.deleteAnswer(id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		checkAnswer(request, response);
	}

	// 获取作业列表
	private void checkAnswer(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			String userType=(String)session.getAttribute("userType");
			List<AnswerInfo> nList=null;
			try {
	            if(userType.equals("教师")){
					nList = business.checkAnswerT();
					request.setAttribute("nList", nList);
	            	request.getRequestDispatcher("/client/teacher/checkAnswer.jsp").forward(request, response);
				}
				else {
					nList = business.checkAnswerS((String)session.getAttribute("userId"));
					request.setAttribute("nList", nList);
					request.getRequestDispatcher("/client/student/checkAnswer.jsp").forward(request, response);
				}
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

	// 回答作业
	private void addAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AnswerInfo answer = new AnswerInfo();
		answer.setAnswerid(IdGenerator.genPrimaryKey());
		try {
			BeanUtils.populate(answer, request.getParameterMap());
			business.addAnswer(answer);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			String errorMsg = "日期转换出错，请检查日期格式";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "未知异常，请重试或联系管理员";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		request.setAttribute("message", "<script type='text/javascript'>alert('提交成功！')</script>");
		try {
			request.getRequestDispatcher("/client/student/addAnswer.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
}
