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
 * Servlet implementation class ExportExcel_DramasScoreServlet �����������ֵ�Excel���
 * @author Smile
 */
@WebServlet("/ExportExcel_DramasScoreServlet.do")
public class ExportExcel_DramasScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ��servlet��ʵ������ע��ķ���
	public void init() throws ServletException {
		super.init();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
		factory.autowireBean(this);
	}

	// ����ע��
	@Autowired
	DramaService dramaService;

	// ����ע��
	@Autowired
	ScoreInfoService scoreInfoService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������
		request.setCharacterEncoding("utf-8");
		// ��ѯ������Ϣ
		List<Drama> dramaList = dramaService.getAllDramas();
		// ������ĿID�ļ��ϣ���Ϊ����
		List<Integer> dramaIdList = new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		// �õ����������б�
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		String sheetName = "�ó�ͳ�Ʊ�";
		String titleName = "�����ݳ���Ϣͳ�Ʊ�";
		String fileName = "�ó�����ͳ�Ʊ�";
		int columnNumber = 4;
		// �����п�
		int[] columnWidth = { 15, 20, 60, 10};
		// �����ά����
		String[][] dataList = new String [dramaList.size()][4];
		// ��Ż�����Ϣ
		for (int i = 0; i < dramaList.size(); i++) {
				dataList[i][0] = dramaList.get(i).getDramaName();
				dataList[i][1] = dramaList.get(i).getTheaterName();
				dataList[i][2] = dramaList.get(i).getDramaIntro();
				dataList[i][3] = scoreInfoVoList.get(i).getScoreAvg()+"";
		}
		// ����̨��������Ϣ
		for (int i = 0; i < dramaList.size(); i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(dataList[i][j] + "                         ");
			}
			// ����
			System.out.println();
		}
		// �����б���
		String[] columnName = { "��������", "�糡����", "������" ,"����"};
		// ���õ���Excel���ķ���
		try {
			new ExportExcel_DramasScoreServlet().ExportNoResponse(sheetName, titleName, fileName,
					columnNumber, columnWidth, columnName, dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������Ӧ
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
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //ͳһ�����п�
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 512); // ��������ÿ�еĿ�
					}
				}
			}
			// ������0�� Ҳ���Ǳ���
			HSSFRow row1 = sheet.createRow((int) 0);
			row1.setHeightInPoints(50);// �豸����ĸ߶�
			// ��������������ĵ�Ԫ����ʽstyle2�Լ�������ʽheaderFont1
			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // ����������ʽ
			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // ����Ӵ�
			headerFont1.setFontName("����"); // ������������
			headerFont1.setFontHeightInPoints((short) 15); // ���������С
			style2.setFont(headerFont1); // Ϊ������ʽ����������ʽ
 
			HSSFCell cell1 = row1.createCell(0);// ���������һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
					columnNumber - 1)); // �ϲ��б���
			cell1.setCellValue(titleName); // ����ֵ����
			cell1.setCellStyle(style2); // ���ñ�����ʽ
 
			// ������1�� Ҳ���Ǳ�ͷ
			HSSFRow row = sheet.createRow((int) 1);
			row.setHeightInPoints(40);// ���ñ�ͷ�߶�
 
			// ���Ĳ���������ͷ��Ԫ����ʽ �Լ���ͷ��������ʽ
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);// �����Զ�����
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����и�ʽ
 
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
			HSSFFont headerFont = (HSSFFont) wb.createFont(); // ����������ʽ
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // ����Ӵ�
			headerFont.setFontName("����"); // ������������
			headerFont.setFontHeightInPoints((short) 10); // ���������С
			style.setFont(headerFont); // Ϊ������ʽ����������ʽ
 
			// ����.һ����������ͷ����
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}
 
			// ���岽��������Ԫ�񣬲�����ֵ
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 2);
				// Ϊ�������������ص��µ�Ԫ����ʽ1 �Զ����� ���¾���
				HSSFCellStyle zidonghuanhang = wb.createCellStyle();
				zidonghuanhang.setWrapText(true);// �����Զ�����
				zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����и�ʽ
 
				// ���ñ߿�
				zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
				// Ϊ�������������ص��µ�Ԫ����ʽ2 �Զ����� ���¾�������Ҳ����
				HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
				zidonghuanhang2.setWrapText(true);// �����Զ�����
				zidonghuanhang2
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����¾��и�ʽ
				zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ���Ҿ���
 
				// ���ñ߿�
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
 
			// �����������ļ��浽��������õ�����λ��
			String filename = fileName + ".xls";
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					.concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			OutputStream out = response.getOutputStream();
			try {
				wb.write(out);// ������д��ȥ
				String str = "����" + fileName + "�ɹ���";
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "����" + fileName + "ʧ�ܣ�";
				System.out.println(str1);
			} finally {
				out.close();
			}
 
		} else {
			System.out.println("����Ŀ���������������鳤��Ҫһ��");
		}
 
	}
 
	/**
	 * ��������Ӧ
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
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //ͳһ�����п�
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 256); // ��������ÿ�еĿ�
					}
				}
			}
			// ������0�� Ҳ���Ǳ���
			HSSFRow row1 = sheet.createRow((int) 0);
			row1.setHeightInPoints(50);// �豸����ĸ߶�
			// ��������������ĵ�Ԫ����ʽstyle2�Լ�������ʽheaderFont1
			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // ����������ʽ
			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // ����Ӵ�
			headerFont1.setFontName("����"); // ������������
			headerFont1.setFontHeightInPoints((short) 15); // ���������С
			style2.setFont(headerFont1); // Ϊ������ʽ����������ʽ
 
			HSSFCell cell1 = row1.createCell(0);// ���������һ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
					columnNumber - 1)); // �ϲ���0����17��
			cell1.setCellValue(titleName); // ����ֵ����
			cell1.setCellStyle(style2); // ���ñ�����ʽ
 
			// ������1�� Ҳ���Ǳ�ͷ
			HSSFRow row = sheet.createRow((int) 1);
			row.setHeightInPoints(37);// ���ñ�ͷ�߶�
 
			// ���Ĳ���������ͷ��Ԫ����ʽ �Լ���ͷ��������ʽ
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);// �����Զ�����
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����и�ʽ
 
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
			HSSFFont headerFont = (HSSFFont) wb.createFont(); // ����������ʽ
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // ����Ӵ�
			headerFont.setFontName("����"); // ������������
			headerFont.setFontHeightInPoints((short) 12); // ���������С
			style.setFont(headerFont); // Ϊ������ʽ����������ʽ
 
			// ����.һ����������ͷ����
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}
 
			// ���岽��������Ԫ�񣬲�����ֵ
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 2);
				// Ϊ�������������ص��µ�Ԫ����ʽ1 �Զ����� ���¾���
				HSSFCellStyle zidonghuanhang = wb.createCellStyle();
				zidonghuanhang.setWrapText(true);// �����Զ�����
				zidonghuanhang
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����и�ʽ
				// ���ñ߿�
				zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
				zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
				zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);
 
				// Ϊ�������������ص��µ�Ԫ����ʽ2 �Զ����� ���¾�������Ҳ����
				HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
				zidonghuanhang2.setWrapText(true);// �����Զ�����
				zidonghuanhang2
						.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ����һ�����¾��и�ʽ
				zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ���Ҿ���
 
				// ���ñ߿�
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
 
			// �����������ļ��浽ָ��λ��
			try {
				FileOutputStream fout = new FileOutputStream("H:dramasscore.xls");
				wb.write(fout);
				String str = "����" + fileName + "�ɹ���";
				System.out.println(str);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "����" + fileName + "ʧ�ܣ�";
				System.out.println(str1);
			}
		} else {
			System.out.println("����Ŀ���������������鳤��Ҫһ��");
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
