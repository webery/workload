package dlnu.workload.module.theoryteaching.doc;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import dlnu.workload.module.common.model.User;
import dlnu.workload.module.theoryteaching.model.CourseAccount;

/**
 * 用户个人的理论工作量导出
 * 
 * @author weber
 *
 */
public class UserCourseAccountExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String semester = (String) model.get("semester");
		User user = (User) model.get("user");

		// 1.生成工作簿
		HSSFSheet sheet = workbook.createSheet("理论课程申报表");
		sheet.setDefaultColumnWidth(12);
		sheet.setDefaultRowHeightInPoints(24);
		int index = 1;// 整个sheet的行数纪录
		// CellStyle dateStyle = getDataFormatStyle(workbook);
		// 1.主题
		HSSFRow subjectRow = sheet.createRow(index++);
		CellStyle subjectStyle = getSubjectStyle(workbook);
		Cell c_sj_1 = subjectRow.createCell(0);
		c_sj_1.setCellValue("大连民族大学 " + semester + " 理论教学工作量核算表");
		c_sj_1.setCellStyle(subjectStyle);
		// 2.题目getTitleStyle
		HSSFRow titleRow = sheet.createRow(index++);
		CellStyle titleStyle = getSubjectStyle(workbook);
		Cell c_t_1 = titleRow.createCell(1);
		c_t_1.setCellValue("申报教师名称");
		c_t_1.setCellStyle(titleStyle);
		Cell c_t_2 = titleRow.createCell(2);
		c_t_2.setCellValue(user.getName());
		c_t_2.setCellStyle(titleStyle);
		Cell c_t_3 = titleRow.createCell(3);
		c_t_3.setCellValue("职称");
		c_t_3.setCellStyle(titleStyle);
		Cell c_t_4 = titleRow.createCell(4);
		c_t_4.setCellValue(user.getTeacherTitle().getName());
		c_t_4.setCellStyle(titleStyle);
		Cell c_t_5 = titleRow.createCell(5);
		c_t_5.setCellValue("开课学院");
		c_t_5.setCellStyle(titleStyle);
		Cell c_t_6 = titleRow.createCell(6);
		c_t_6.setCellValue(user.getCollege().getName());
		c_t_6.setCellStyle(titleStyle);
		// 2.内容头部
		// 2.sheet头部
		int c_index = 0;
		HSSFRow headerRow = sheet.createRow(index++);
		CellStyle headerStyle = getHeaderStyle(workbook);
		Cell c_h_1 = headerRow.createCell(c_index++);
		c_h_1.setCellValue("课程名称");
		c_h_1.setCellStyle(headerStyle);
		Cell c_h_2 = headerRow.createCell(c_index++);
		c_h_2.setCellValue("授课对象");
		c_h_2.setCellStyle(headerStyle);
		Cell c_h_3 = headerRow.createCell(c_index++);
		c_h_3.setCellValue("正常选课人数");
		c_h_3.setCellStyle(headerStyle);
		Cell c_h_4 = headerRow.createCell(c_index++);
		c_h_4.setCellValue("重修人数");
		c_h_4.setCellStyle(headerStyle);
		Cell c_h_5 = headerRow.createCell(c_index++);
		c_h_5.setCellValue("总人数");
		c_h_5.setCellStyle(headerStyle);
		Cell c_h_6 = headerRow.createCell(c_index++);
		c_h_6.setCellValue("计划学时");
		c_h_6.setCellStyle(headerStyle);
		Cell c_h_7 = headerRow.createCell(c_index++);
		c_h_7.setCellValue("课程类型系数");
		c_h_7.setCellStyle(headerStyle);
		Cell c_h_8 = headerRow.createCell(c_index++);
		c_h_8.setCellValue("重复课系数");
		c_h_8.setCellStyle(headerStyle);
		Cell c_h_9 = headerRow.createCell(c_index++);
		c_h_9.setCellValue("工作量");
		c_h_9.setCellStyle(headerStyle);
		Cell c_h_10 = headerRow.createCell(c_index++);
		c_h_10.setCellValue("授课校区");
		c_h_10.setCellStyle(headerStyle);

		// 2.1写入内容头部
		List<CourseAccount> courseAccounts = user.getCourseAccounts();
		Iterator<CourseAccount> it_a = courseAccounts.iterator();
		while (it_a.hasNext()) {
			c_index = 0;
			CourseAccount courseAccount = it_a.next();
			HSSFRow row_a = sheet.createRow(index++);
			CellStyle row_a_Style = getCommonStyle(workbook);

			Cell c_a_course = row_a.createCell(c_index++);
			c_a_course.setCellValue(courseAccount.getCourse().getName());
			c_a_course.setCellStyle(row_a_Style);
			Cell c_a_student = row_a.createCell(c_index++);
			c_a_student.setCellValue(courseAccount.getStudent());
			c_a_student.setCellStyle(row_a_Style);
			Cell c_a_courseNum = row_a.createCell(c_index++);
			c_a_courseNum.setCellValue(courseAccount.getCourseNum());
			c_a_courseNum.setCellStyle(row_a_Style);
			Cell c_a_courseRepnum = row_a.createCell(c_index++);
			c_a_courseRepnum.setCellValue(courseAccount.getCourseRepnum());
			c_a_courseRepnum.setCellStyle(row_a_Style);
			Cell c_a_classHour = row_a.createCell(c_index++);
			c_a_classHour.setCellValue(courseAccount.getClassHour());
			c_a_classHour.setCellStyle(row_a_Style);
			Cell c_a_typeFactor = row_a.createCell(c_index++);
			c_a_typeFactor.setCellValue(courseAccount.getTypeFactor());
			c_a_typeFactor.setCellStyle(row_a_Style);
			Cell c_a_repFactor = row_a.createCell(c_index++);
			c_a_repFactor.setCellValue(courseAccount.getRepFactor());
			c_a_repFactor.setCellStyle(row_a_Style);
			Cell c_a_workload = row_a.createCell(c_index++);
			c_a_workload.setCellValue(courseAccount.getWorkload());
			c_a_workload.setCellStyle(row_a_Style);
			Cell c_a_campus = row_a.createCell(c_index++);
			c_a_campus.setCellValue(courseAccount.getCampus());
			c_a_campus.setCellStyle(row_a_Style);

		}
		// 3.写入页脚
		// 5.返回客户端
		// 设置下载时客户端Excel的名称
		String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ ".xls";
		// 处理中文文件名
		// filename = MyUtils.encodeFilename(filename, request);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	// 头部样式
	private CellStyle getCommonStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		style.setFont(font);

		return style;
	}

	// 头部样式
	private CellStyle getHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setColor(Font.COLOR_RED);
		style.setFont(font);

		return style;
	}

	// 主题样式
	private CellStyle getSubjectStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		font.setColor(Font.COLOR_RED);
		style.setFont(font);

		return style;
	}

	// 主题样式
	private CellStyle getTitleStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setColor(Font.COLOR_RED);
		style.setFont(font);

		return style;
	}

}
