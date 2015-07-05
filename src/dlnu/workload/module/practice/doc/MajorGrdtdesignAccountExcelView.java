package dlnu.workload.module.practice.doc;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;

public class MajorGrdtdesignAccountExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String semester = (String) model.get("semester");
		String major = (String) model.get("major");
		List<GrdtdesignAccount> grdtdesignAccounts = (List<GrdtdesignAccount>) model
				.get("grdtdesignAccounts");

		// 1.生成工作簿
		HSSFSheet sheet = workbook.createSheet("理论课程申报表");
		sheet.setDefaultColumnWidth(12);
		sheet.setDefaultRowHeightInPoints(24);
		int index = 0;// 整个sheet的行数纪录

		// 2。主题
		HSSFRow subjectRow = sheet.createRow(index++);
		CellStyle subjectStyle = getSubjectStyle(workbook);
		Cell c_sj_1 = subjectRow.createCell(0);
		c_sj_1.setCellValue("大连民族大学 " + semester);
		c_sj_1.setCellStyle(subjectStyle);
		// 3。标题
		HSSFRow titleRow = sheet.createRow(index++);
		CellStyle titleStyle = getTitleStyle(workbook);
		Cell c_t_1 = titleRow.createCell(1);
		c_t_1.setCellValue(" 毕业论文（设计）工作量核算表");
		c_t_1.setCellStyle(titleStyle);
		// 3.内容
		int act_index = 0;
		if (grdtdesignAccounts == null) {
			grdtdesignAccounts = new ArrayList<GrdtdesignAccount>();
		}
		Iterator<GrdtdesignAccount> it_act = grdtdesignAccounts.iterator();
		while (it_act.hasNext()) {
			GrdtdesignAccount grdtdesignAccount = it_act.next();
			HSSFRow actHeaderRow = sheet.createRow(index++);
			CellStyle actHeaderStyle = getActHeaderStyle(workbook);

			Cell c_act_h_name = actHeaderRow.createCell(act_index++);
			c_act_h_name.setCellValue(" 毕业论文（设计）工作量核算表");
			c_act_h_name.setCellStyle(actHeaderStyle);

			act_index = 0;
			HSSFRow act_t_Row = sheet.createRow(index++);
			CellStyle act_t_Style = getTitleStyle(workbook);

			Cell c_act_t_major = act_t_Row.createCell(act_index++);
			c_act_t_major.setCellValue("专业年级");
			c_act_t_major.setCellStyle(act_t_Style);
			Cell c_act_t_classNum = act_t_Row.createCell(act_index++);
			c_act_t_classNum.setCellValue("班级数");
			c_act_t_classNum.setCellStyle(act_t_Style);
			Cell c_act_t_stuNum = act_t_Row.createCell(act_index++);
			c_act_t_stuNum.setCellValue("学生人数");
			c_act_t_stuNum.setCellStyle(act_t_Style);
			Cell c_act_t_weekNum = act_t_Row.createCell(act_index++);
			c_act_t_weekNum.setCellValue("计划周数");
			c_act_t_weekNum.setCellStyle(act_t_Style);
			Cell c_act_t_factor = act_t_Row.createCell(act_index++);
			c_act_t_factor.setCellValue("系数");
			c_act_t_factor.setCellStyle(act_t_Style);
			Cell c_act_t_workload = act_t_Row.createCell(act_index++);
			c_act_t_workload.setCellValue("标准工作量");
			c_act_t_workload.setCellStyle(act_t_Style);
			Cell c_act_t_period = act_t_Row.createCell(act_index++);
			c_act_t_period.setCellValue(grdtdesignAccount.getCampus() + "计划学时");
			c_act_t_period.setCellStyle(act_t_Style);
			Cell c_act_t_remark = act_t_Row.createCell(act_index++);
			c_act_t_remark.setCellValue("备注");
			c_act_t_remark.setCellStyle(act_t_Style);

			// 内容
			act_index = 0;
			HSSFRow act_Row = sheet.createRow(index++);
			CellStyle act_Style = getCommonStyle(workbook);

			Cell c_act_major = act_Row.createCell(act_index++);
			c_act_major.setCellValue(grdtdesignAccount.getMajor().getName()
					+ grdtdesignAccount.getGrade());
			c_act_major.setCellStyle(act_Style);
			Cell c_act_classNum = act_Row.createCell(act_index++);
			c_act_classNum.setCellValue(grdtdesignAccount.getClassNum());
			c_act_classNum.setCellStyle(act_Style);
			Cell c_act_stuNum = act_Row.createCell(act_index++);
			c_act_stuNum.setCellValue(grdtdesignAccount.getStuNum());
			c_act_stuNum.setCellStyle(act_Style);
			Cell c_act_weekNum = act_Row.createCell(act_index++);
			c_act_weekNum.setCellValue(grdtdesignAccount.getWeekNum());
			c_act_weekNum.setCellStyle(act_Style);
			Cell c_act_factor = act_Row.createCell(act_index++);
			c_act_factor.setCellValue(grdtdesignAccount.getFactor());
			c_act_factor.setCellStyle(act_Style);
			Cell c_act_workload = act_Row.createCell(act_index++);
			c_act_workload.setCellValue(grdtdesignAccount.getWorkload());
			c_act_workload.setCellStyle(act_Style);
			Cell c_act_period = act_Row.createCell(act_index++);
			c_act_period.setCellValue(grdtdesignAccount.getPeriod());
			c_act_period.setCellStyle(act_Style);
			Cell c_act_remark = act_Row.createCell(act_index++);
			c_act_remark.setCellValue(grdtdesignAccount.getRemark());
			c_act_remark.setCellStyle(act_Style);

			// 工作量分配
			// 头
			HSSFRow gatHeaderRow = sheet.createRow(index++);
			CellStyle gatHeaderStyle = getTitleStyle(workbook);
			Cell c_gac_h_1 = gatHeaderRow.createCell(0);
			c_gac_h_1.setCellValue("工作量分配如下:");
			c_gac_h_1.setCellStyle(gatHeaderStyle);
			int gat_index = 0;
			int gat_order = 1;
			HSSFRow gat_t_Row = sheet.createRow(index++);
			CellStyle gat_t_Style = getTitleStyle(workbook);
			Cell c_gat_t_order = gat_t_Row.createCell(gat_index++);
			c_gat_t_order.setCellValue("序号");
			c_gat_t_order.setCellStyle(gat_t_Style);
			Cell c_gat_t_teacher = gat_t_Row.createCell(gat_index++);
			c_gat_t_teacher.setCellValue("教师姓名");
			c_gat_t_teacher.setCellStyle(gat_t_Style);
			Cell c_gat_t_workload = gat_t_Row.createCell(gat_index++);
			c_gat_t_workload.setCellValue("工作量");
			c_gat_t_workload.setCellStyle(gat_t_Style);
			Cell c_gat_t_period = gat_t_Row.createCell(gat_index++);
			c_gat_t_period.setCellValue("计划学时");
			c_gat_t_period.setCellStyle(gat_t_Style);
			Cell c_gat_t_remark = gat_t_Row.createCell(gat_index++);
			c_gat_t_remark.setCellValue("备注");
			c_gat_t_remark.setCellStyle(gat_t_Style);

			List<GrdtdesignActTeacher> grdtdesignActTeachers = grdtdesignAccount
					.getGrdtdesignActTeachers();
			if (grdtdesignActTeachers == null) {
				grdtdesignActTeachers = new ArrayList<GrdtdesignActTeacher>();
			}
			Iterator<GrdtdesignActTeacher> it_gat = grdtdesignActTeachers
					.iterator();
			while (it_gat.hasNext()) {
				GrdtdesignActTeacher grdtdesignActTeacher = it_gat.next();

				gat_index = 0;
				HSSFRow gat_Row = sheet.createRow(index++);
				CellStyle gat_Style = getTitleStyle(workbook);
				Cell c_gat_order = gat_Row.createCell(gat_index++);
				c_gat_order.setCellValue(gat_order++);
				c_gat_order.setCellStyle(gat_Style);
				Cell c_gat_teacher = gat_Row.createCell(gat_index++);
				c_gat_teacher.setCellValue(grdtdesignActTeacher.getTeacher()
						.getName());
				c_gat_teacher.setCellStyle(gat_Style);
				Cell c_gat_workload = gat_Row.createCell(gat_index++);
				c_gat_workload.setCellValue(grdtdesignActTeacher.getWorkload());
				c_gat_workload.setCellStyle(gat_Style);
				Cell c_gat_period = gat_Row.createCell(gat_index++);
				c_gat_period.setCellValue(grdtdesignActTeacher.getPeriod());
				c_gat_period.setCellStyle(gat_Style);
				Cell c_gat_remark = gat_Row.createCell(gat_index++);
				c_gat_remark.setCellValue(grdtdesignActTeacher.getRemark());
				c_gat_remark.setCellStyle(gat_Style);
			}

		}
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
		font.setColor(Font.COLOR_NORMAL);
		style.setFont(font);

		return style;
	}

	private CellStyle getActHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setColor(Font.COLOR_NORMAL);
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
		font.setColor(Font.COLOR_NORMAL);
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
		font.setColor(Font.COLOR_NORMAL);
		style.setFont(font);

		return style;
	}

}
