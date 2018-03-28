package web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
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
		if ("ExportExcel".equals(operation)) {
			ExportExcel(request, response);
		}
	}

	// 导出成绩单
	private void ExportExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userID");
		List<Grade> gList;
		try {
			if (op.equals("admin")) {
				gList = business.aCheckGrade();
			} else {
				String condition = request.getParameter("condition");
				String ways = request.getParameter("ways");
				if (ways.equals("Title")) {
					gList = business.tGetGradeByTitle(condition, userId);
				} else {
					gList = business.tGetGradeByUid(condition, userId);
				}
			}
			logger.info(userId + ":ExportExcel success.");

			// 创建工作表
			WritableWorkbook book = null;
			response.reset();

			response.setCharacterEncoding("UTF-8");// 设置字符集

			// 创建工作流
			OutputStream os = null;
			try {

				// 设置弹出对话框
				response.setContentType("application/DOWLOAD");
				response.setCharacterEncoding("UTF-8");

				// 设置工作表的标题
				response.setHeader("Content-Disposition", "attachment; filename=Grdae_admin.xls");// 设置生成的文件名字
				os = response.getOutputStream();

				// 初始化工作表
				book = Workbook.createWorkbook(os);

			} catch (IOException e1) {

				logger.error("导出excel出现IO异常", e1);
			}
			try {
				WritableFont font = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD);
				WritableCellFormat format = new WritableCellFormat(font);
				format.setAlignment(jxl.format.Alignment.CENTRE);
				WritableFont font2 = new WritableFont(WritableFont.ARIAL, 15);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				WritableFont font3 = new WritableFont(WritableFont.ARIAL, 13);
				WritableCellFormat format3 = new WritableCellFormat(font3);

				WritableSheet sheet = book.createSheet("学生成绩单", 0);
				sheet.setColumnView(0, sheet.getCell(1, 1).getContents().length() * 2 + 15);
				sheet.setColumnView(1, sheet.getCell(1, 1).getContents().length() * 3 + 12);
				sheet.setColumnView(2, sheet.getCell(1, 1).getContents().length() * 3 + 20);
				sheet.mergeCells(0, 0, 4, 0);
				Label title = new Label(0, 0, "学生成绩统计表", format);
				sheet.addCell(title);
				// 表字段名
				sheet.addCell(new jxl.write.Label(0, 1, "学号", format2));
				sheet.addCell(new jxl.write.Label(1, 1, "姓名", format2));
				sheet.addCell(new jxl.write.Label(2, 1, "作业名称", format2));
				sheet.addCell(new jxl.write.Label(3, 1, "成绩", format2));
				sheet.addCell(new jxl.write.Label(4, 1, "老师", format2));

				// 将数据追加
				for (int i = 1; i < gList.size() + 1; i++) {
					sheet.addCell(new jxl.write.Label(0, i + 1, gList.get(i - 1).getUserId(), format3));
					sheet.addCell(new jxl.write.Label(1, i + 1, gList.get(i - 1).getUserName(), format3));
					sheet.addCell(new jxl.write.Label(2, i + 1, gList.get(i - 1).getWorkTitle(), format3));
					sheet.addCell(new jxl.write.Label(3, i + 1, gList.get(i - 1).getScore() + "", format3));
					sheet.addCell(new jxl.write.Label(4, i + 1, gList.get(i - 1).getTeacherName(), format3));
				}
				book.write();
				book.close();
			} catch (Exception e) {
				logger.error("导出excel出现异常", e);
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
						logger.error("关流出现异常", e);
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			String errorMsg = "数据库操作异常，请重试";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("../common/error.jsp").forward(request, response);
		}

	}

	// 修改成绩
	private void modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userID");
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
			logger.info(userId + "do modifyGrade,for stu:" + studentID + "_" + workTitle + "----" + score);
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
			logger.info((String) session.getAttribute("userID")+"do deleteGrade,for stu:"+userId+"_"+workId);
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
			request.setAttribute("ways", ways);
			request.setAttribute("condition", condition);
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
		HttpSession session = request.getSession();
		Grade grade = new Grade();
		grade.setTeacherId((String) session.getAttribute("userID"));
		grade.setTeacherName((String) session.getAttribute("userName"));
		try {
			BeanUtils.populate(grade, request.getParameterMap());
			business.inputGrade(grade);
			logger.info(grade.getTeacherId()+"do inputGrade,for stu:"+grade.getUserId()+"_"+grade.getWorkTitle());
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
