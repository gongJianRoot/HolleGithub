package com.hw.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hw.entity.Drama;
import com.hw.entity.vo.ScoreInfoVo;
import com.hw.service.DramaService;
import com.hw.service.ScoreInfoService;

/**
 * Servlet implementation class ExportExcel_DramasScoreServlet 导出话剧评分的Excel表格
 * @author Smile
 */
@WebServlet("/ExportExcel_DramasScoreServlet.do")
public class ExportExcel_DramasScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 在servlet中实现依赖注入的方法
	public void init() throws ServletException {
		super.init();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
		factory.autowireBean(this);
	}

	// 依赖注入
	@Autowired
	DramaService dramaService;

	// 依赖注入
	@Autowired
	ScoreInfoService scoreInfoService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求编码
		request.setCharacterEncoding("utf-8");
		// 查询话剧信息
		List<Drama> dramaList = dramaService.getAllDramas();
		// 声明剧目ID的集合，作为参数
		List<Integer> dramaIdList = new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		// 得到话剧评分列表
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		String sheetName = "用车统计表单";
		String titleName = "话剧演出信息统计表";
		String fileName = "用车申请统计表单";
		int columnNumber = 4;
		// 定义列宽
		int[] columnWidth = { 15, 20, 60, 10};
		// 定义二维数组
		String[][] dataList = new String [dramaList.size()][4];
		// 存放话剧信息
		for (int i = 0; i < dramaList.size(); i++) {
				dataList[i][0] = dramaList.get(i).getDramaName();
				dataList[i][1] = dramaList.get(i).getTheaterName();
				dataList[i][2] = dramaList.get(i).getDramaIntro();
				dataList[i][3] = scoreInfoVoList.get(i).getScoreAvg()+"";
		}
		// 控制台输出表格信息
		for (int i = 0; i < dramaList.size(); i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(dataList[i][j] + "                         ");
			}
			// 换行
			System.out.println();
		}
		// 定义列标题
		String[] columnName = { "话剧名称", "剧场名称", "话剧简介" ,"评分"};
		// 调用导出Excel表格的方法
		try {
			new ExportExcel_DramasScoreServlet().ExportNoResponse(sheetName, titleName, fileName,
					columnNumber, columnWidth, columnName, dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出表的响应
	 * @param sheetName
	 * @param titleName
	 * @param fileName
	 * @param columnNumber
	 * @param columnWidth
	 * @param columnName
	 * @param dataList
	 * @param response
	 * @throws Exception
	 */
	public void ExportWithResponse(String sheetName, String titleName,
			String fileName, int columnNumber, int[] columnWidth,
			String[] columnName, String[][] dataList,
			HttpServletResponse response) throws Exception {
		if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 512); // 单独设置每列的宽
					}
				}
			}
			// 创建第0行 也就是标题
			HSSFRow row1 = sheet.createRow((int) 0);
			row1.setHeightInPoints(50);// 设备标题的高度
			// 第三步创建标题的单元格样式style2以及字体样式headerFont1
			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont1.setFontName("黑体"); // 设置字体类型
			headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
			style2.setFont(headerFont1); // 为标题样式设置字体样式
 
			HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
					columnNumber - 1)); // 合并列标题
			cell1.setCellValue(titleName); // 设置值标题
			cell1.setCellStyle(style2); // 设置标题样式
 
			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 1);
			row.setHeightInPoints(40);// 设置表头高度
 
			// 第四步，创建表头单元格样式 以及表头的字体样式
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
 
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
			HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont.setFontName("黑体"); // 设置字体类型
			headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
			style.setFont(headerFont); // 为标题样式设置字体样式
 
			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}
 
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 2);
				// 为数据内容设置特点新单元格样式1 自动换行 上下居中
				HSSFCellStyle zidonghuanhang = wb.createCellStyle();
				zidonghuanhang.setWrapText(true);// 设置自动换行
				zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
 
				// 设置边框
				zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
				// 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
				HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
				zidonghuanhang2.setWrapText(true);// 设置自动换行
				zidonghuanhang2
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
				zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
 
				// 设置边框
				zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) 
				{
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
					datacell.setCellStyle(zidonghuanhang2);
				}
			}
 
			// 第六步，将文件存到浏览器设置的下载位置
			String filename = fileName + ".xls";
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					.concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			OutputStream out = response.getOutputStream();
			try {
				wb.write(out);// 将数据写出去
				String str = "导出" + fileName + "成功！";
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "导出" + fileName + "失败！";
				System.out.println(str1);
			} finally {
				out.close();
			}
 
		} else {
			System.out.println("列数目长度名称三个数组长度要一致");
		}
 
	}
 
	/**
	 * 导出无响应
	 * @param sheetName
	 * @param titleName
	 * @param fileName
	 * @param columnNumber
	 * @param columnWidth
	 * @param columnName
	 * @param dataList
	 * @throws Exception
	 */
	public void ExportNoResponse(String sheetName, String titleName,
			String fileName, int columnNumber, int[] columnWidth,
			String[] columnName, String[][] dataList) throws Exception {
		if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
					}
				}
			}
			// 创建第0行 也就是标题
			HSSFRow row1 = sheet.createRow((int) 0);
			row1.setHeightInPoints(50);// 设备标题的高度
			// 第三步创建标题的单元格样式style2以及字体样式headerFont1
			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont1.setFontName("黑体"); // 设置字体类型
			headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
			style2.setFont(headerFont1); // 为标题样式设置字体样式
 
			HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
					columnNumber - 1)); // 合并第0到第17列
			cell1.setCellValue(titleName); // 设置值标题
			cell1.setCellStyle(style2); // 设置标题样式
 
			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 1);
			row.setHeightInPoints(37);// 设置表头高度
 
			// 第四步，创建表头单元格样式 以及表头的字体样式
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
 
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
			HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont.setFontName("黑体"); // 设置字体类型
			headerFont.setFontHeightInPoints((short) 12); // 设置字体大小
			style.setFont(headerFont); // 为标题样式设置字体样式
 
			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}
 
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 2);
				// 为数据内容设置特点新单元格样式1 自动换行 上下居中
				HSSFCellStyle zidonghuanhang = wb.createCellStyle();
				zidonghuanhang.setWrapText(true);// 设置自动换行
				zidonghuanhang
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
				// 设置边框
				zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
				// 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
				HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
				zidonghuanhang2.setWrapText(true);// 设置自动换行
				zidonghuanhang2
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
				zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
 
				// 设置边框
				zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) 
				{
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
					datacell.setCellStyle(zidonghuanhang2);
				}
			}
 
			// 第六步，将文件存到指定位置
			try {
				FileOutputStream fout = new FileOutputStream("H:dramasscore.xls");
				wb.write(fout);
				String str = "导出" + fileName + "成功！";
				System.out.println(str);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "导出" + fileName + "失败！";
				System.out.println(str1);
			}
		} else {
			System.out.println("列数目长度名称三个数组长度要一致");
		}
 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
