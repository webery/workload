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

import dlnu.workload.module.practice.model.ProjectAccount;

public class MajorProjectAccountExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String semester = (String) model.get("semester");
		String major = (String) model.get("major");
		List<ProjectAccount> projectAccounts = (List<ProjectAccount>) model
				.get("projectAccounts");

		HSSFSheet sheet = workbook.createSheet("大创申报表");
		sheet.setDefaultColumnWidth(12);
		sheet.setDefaultRowHeightInPoints(24);
		int index = 0;// 整个sheet的行数纪录
		// 1.主题

		// 2.标题
		HSSFRow titleRow = sheet.createRow(index++);
		CellStyle titleStyle = getTitleStyle(workbook);
		Cell c_t_1 = titleRow.createCell(1);
		c_t_1.setCellValue("学院(公章):");
		c_t_1.setCellStyle(titleStyle);
		// 3.内容区
		int act_index = 0;
		HSSFRow act_t_Row = sheet.createRow(index++);
		CellStyle pat_t_Style = getTitleStyle(workbook);
		Cell c_act_t_order = act_t_Row.createCell(act_index++);
		c_act_t_order.setCellValue("序号");
		c_act_t_order.setCellStyle(pat_t_Style);
		Cell c_act_t_teacher = act_t_Row.createCell(act_index++);
		c_act_t_teacher.setCellValue("指导教师");
		c_act_t_teacher.setCellStyle(pat_t_Style);
		Cell c_act_t_spcode = act_t_Row.createCell(act_index++);
		c_act_t_spcode.setCellValue("项目编号");
		c_act_t_spcode.setCellStyle(pat_t_Style);
		Cell c_act_t_sworkload = act_t_Row.createCell(act_index++);
		c_act_t_sworkload.setCellValue("工作量");
		c_act_t_sworkload.setCellStyle(pat_t_Style);
		Cell c_act_t_mpcode = act_t_Row.createCell(act_index++);
		c_act_t_mpcode.setCellValue("项目编号");
		c_act_t_mpcode.setCellStyle(pat_t_Style);
		Cell c_act_t_mworkload = act_t_Row.createCell(act_index++);
		c_act_t_mworkload.setCellValue("工作量");
		c_act_t_mworkload.setCellStyle(pat_t_Style);
		Cell c_act_t_workload = act_t_Row.createCell(act_index++);
		c_act_t_workload.setCellValue("总工作量");
		c_act_t_workload.setCellStyle(pat_t_Style);

		if (projectAccounts == null) {
			projectAccounts = new ArrayList<ProjectAccount>();
		}
		int pa_order = 1;

		Iterator<ProjectAccount> it_pa = projectAccounts.iterator();
		while (it_pa.hasNext()) {
			act_index = 0;
			ProjectAccount projectAccount = it_pa.next();
			HSSFRow pat_Row = sheet.createRow(index++);
			CellStyle pat_Style = getCommonStyle(workbook);

			Cell c_pat_order = pat_Row.createCell(act_index++);
			c_pat_order.setCellValue(pa_order++);
			c_pat_order.setCellStyle(pat_Style);
			Cell c_pateacher = pat_Row.createCell(act_index++);
			c_pateacher.setCellValue(projectAccount.getCreator().getName());
			c_pateacher.setCellStyle(pat_Style);
			Cell c_pat_spcode = pat_Row.createCell(act_index++);
			c_pat_spcode.setCellValue(projectAccount.getSpcode());
			c_pat_spcode.setCellStyle(pat_Style);
			Cell c_pat_sworkload = pat_Row.createCell(act_index++);
			c_pat_sworkload.setCellValue(projectAccount.getSworkload());
			c_pat_sworkload.setCellStyle(pat_Style);
			Cell c_pat_mpcode = pat_Row.createCell(act_index++);
			c_pat_mpcode.setCellValue(projectAccount.getMpcode());
			c_pat_mpcode.setCellStyle(pat_Style);
			Cell c_pat_mworkload = pat_Row.createCell(act_index++);
			c_pat_mworkload.setCellValue(projectAccount.getMworkload());
			c_pat_mworkload.setCellStyle(pat_Style);
			Cell c_pat_workload = pat_Row.createCell(act_index++);
			c_pat_workload.setCellValue(projectAccount.getSworkload()
					+ projectAccount.getMworkload());
			c_pat_workload.setCellStyle(pat_Style);

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

}
