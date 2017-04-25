package ustc.sse.sa16225300.web.controller;

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

import ustc.sse.sa16225300.domain.Grade;
import ustc.sse.sa16225300.domain.GradeTable;
import ustc.sse.sa16225300.domain.PersonalGrade;
import ustc.sse.sa16225300.service.BusinessService;
import ustc.sse.sa16225300.service.impl.BusinessServiceImpl;

public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService business=new BusinessServiceImpl();
	private static Logger logger = Logger.getLogger(UserServlet.class); 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if("inputGrade".equals(operation)){
			inputGrade(request,response);
		}
		if("deleteGrade".equals(operation)){
			deleteGrade(request,response);
		}
		if("modifyGrade".equals(operation)){
			modifyGrade(request,response);
		}
		//����Ա�鿴�ɼ�
		if("aCheckGrade".equals(operation)){
			aCheckGrade(request,response);
		}
		//��ʦ�鿴�ɼ��ɼ���
		if("tCheckGrade".equals(operation)){
			tCheckGrade(request,response);
		}
		//ѧ����ø��˳ɼ�
		if("sCheckGrade".equals(operation)){
			sCheckGrade(request,response);
		}
		//��ʦ��ø��˳ɼ�
		if("getGrade".equals(operation)){
			getGrade(request,response);
		}
	}

	//�޸ĳɼ�
	private void modifyGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String studentID=request.getParameter("studentID");
		String gradeType=request.getParameter("gradeType");
		String score=request.getParameter("modifyScore");
		Grade grade=new Grade();
		grade.setStudentID(studentID);
		grade.setGradeType(gradeType);
		grade.setScore(score);
		try {
			business.modifyGrade(grade);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		aCheckGrade(request, response);
	}

	private void deleteGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String studentID=request.getParameter("studentID");
		String gradeType=request.getParameter("gradeType");
		Grade grade=new Grade();
		grade.setStudentID(studentID);
		grade.setGradeType(gradeType);
		try {
			business.deleteGrade(grade);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
		aCheckGrade(request, response);
	}

	//����Ա�鿴�ɼ�
	private void aCheckGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Grade> gList;
		try {
			gList = business.aCheckGrade();
			request.setAttribute("gList", gList);
			request.getRequestDispatcher("/admin/checkGrade.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO�쳣,������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//ѧ���鿴�ɼ�
	private void sCheckGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String studentID=(String)session.getAttribute("userID");
		try {
		PersonalGrade pGrade=getPersonalGrade(studentID);
		request.setAttribute("pGrade", pGrade);
		request.getRequestDispatcher("/client/student/showGrade.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO�쳣,������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//��ʦ��ȡ���˳ɼ���Ϣ
	private void getGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String studentID=request.getParameter("studentID");
		try {
		PersonalGrade pGrade=getPersonalGrade(studentID);
		request.setAttribute("pGrade", pGrade);
		request.getRequestDispatcher("/client/teacher/showGrade.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO�쳣,������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//��ʦ�鿴�ɼ���
	private void tCheckGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<GradeTable> tList=business.getGradeTable();
			request.setAttribute("tList", tList);
			request.getRequestDispatcher("/client/teacher/checkGrade.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO�쳣,������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}
	}
	//��ø��˳ɼ�
	private PersonalGrade getPersonalGrade(String studentID) throws SQLException{
		PersonalGrade pGrade=new PersonalGrade();
		pGrade.setStudentID(studentID);
		List<Grade> grades=business.getGrade(studentID);
		for(Grade grade:grades){
			if(grade.getGradeType().equals("����ҵ")){
				pGrade.setHomework(grade.getScore());
			}
			else if(grade.getGradeType().equals("����")){
				pGrade.setReport(grade.getScore());
			}
			else if(grade.getGradeType().equals("���û���")){
				pGrade.setInteraction(grade.getScore());
			}
			else if(grade.getGradeType().equals("���п���")){
				pGrade.setMiddleTest(grade.getScore());
			}
			else if(grade.getGradeType().equals("��ĩ����")){
				pGrade.setFinalTest(grade.getScore());
			}
		}
		double finalScore=Double.parseDouble(pGrade.getHomework())+
				Double.parseDouble(pGrade.getReport())+
				Double.parseDouble(pGrade.getInteraction())+
				Double.parseDouble(pGrade.getMiddleTest())+
				Double.parseDouble(pGrade.getFinalTest());
		pGrade.setFinalScore(String.valueOf(finalScore));
		return pGrade;
	}

	private void inputGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		Grade grade=new Grade();
		try {
			BeanUtils.populate(grade, request.getParameterMap());
			business.inputGrade(grade);
			request.setAttribute("message", "<script type='text/javascript'>alert('��ӳɹ���')</script>");
			request.getRequestDispatcher("/admin/inputGrade.jsp").forward(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String errorMsg = "IO�쳣,������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "���ݿ�����쳣��������";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage());
			String errorMsg = "δ֪�쳣�������Ի���ϵ����Ա";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		} 
	}

}
