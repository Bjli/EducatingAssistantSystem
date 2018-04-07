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

import domain.Notice;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import util.IdGenerator;

public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business=new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(NoticeServlet.class); 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if("releaseNotice".equals(operation)){
			releaseNotice(request,response);
		}
		if("checkNotice".equals(operation)){
			checkNotice(request,response);
		}
		if("deleteNotice".equals(operation)){
			deleteNotice(request,response);
		}
		if("getNotice".equals(operation)){
			getNotice(request,response);
		}
	}
	//获取特定id的通知内容
	private void getNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String op=request.getParameter("op");
		Notice notice=null;
		try {
			notice = business.getNotice(id);
			request.setAttribute("notice", notice);
			if(op.equals("addAnswer")){
				request.getRequestDispatcher("/client/student/addAnswer.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/common/getNotice.jsp").forward(request, response);
			}
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
	//删除特点id的通知
	private void deleteNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		HttpSession session=request.getSession();
		try {
			business.deleteNotice(id);
			logger.info((String)session.getAttribute("userID")+"do deleteNotice,notice_id:"+id);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		checkNotice(request, response);
	}
	//获取通知/作业列表
	private void checkNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userType=(String)session.getAttribute("userType");
		List<Notice> nList=null;
		try {

			if(userType.equals("管理员")){
				nList = business.checkNotice();
				request.setAttribute("nList", nList);
				request.getRequestDispatcher("/admin/checkNotice.jsp").forward(request, response);
			}
			else if(userType.equals("教师")){
				String userId = (String) session.getAttribute("userID");
				nList = business.tCheckNotice(userId);
				request.setAttribute("nList", nList);
				request.getRequestDispatcher("/client/teacher/checkNotice.jsp").forward(request, response);
			}
			else {
				String userId = (String) session.getAttribute("userID");
				nList = business.sCheckNotice(userId);
				request.setAttribute("nList", nList);
				request.getRequestDispatcher("/client/student/checkNotice.jsp").forward(request, response);
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
	//发布通知
	private void releaseNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userID");
		Notice notice=new Notice();
		notice.setId(IdGenerator.genPrimaryKey());
		notice.setAuthorId(userId);
		try {
			BeanUtils.populate(notice, request.getParameterMap());
			business.releaseNotice(notice);
			logger.info(userId+":do releaseNotice,notice_ID:"+notice.getId());
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
		request.setAttribute("message", "<script type='text/javascript'>alert('发布成功！')</script>");
		try {
				request.getRequestDispatcher("/common/releaseNotice.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO异常,请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
}
