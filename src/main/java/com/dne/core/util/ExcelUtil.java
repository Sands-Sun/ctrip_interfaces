package com.dne.core.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author: 作者：谭凯凯
 * @explain: 释义：数据导出到Excel
 * @version: 日期：2017-06-16 下午05:50:06
 */
@SuppressWarnings("deprecation")
public class ExcelUtil
{
	private static XSSFWorkbook wb;
	private static CellStyle titleStyle;		// 标题行样式
	private static Font titleFont;				// 标题行字体		
	private static CellStyle dateStyle;			// 日期行样式
	private static Font dateFont;				// 日期行字体
	private static CellStyle headStyle;			// 表头行样式
	private static Font headFont;				// 表头行字体
	private static CellStyle contentStyle ;		// 内容行样式
	private static Font contentFont;			// 内容行字体
	
	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	@SuppressWarnings({ "unchecked" })
	public static void export2ExcelByPojo(ExportSetInfo setInfo) throws 
		IOException, IllegalArgumentException, IllegalAccessException
	{
		init();
		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List> entry : set)
		{
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		XSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames);
		
		int sheetNum = 0;
		for (Entry<String, List> entry : set)
		{
			// Sheet
			List objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 3;
			for (Object obj : objs)
			{
				XSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				XSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
				int cellNum = 1;					// 去掉一列序号，因此从1开始
				if(fieldNames != null)
				{
					for (int num = 0; num < fieldNames.length; num++)
					{
						Object value = ReflectionUtils.invokeGetterMethod(obj, fieldNames[num]);
						cells[cellNum].setCellValue(value == null ? "" : value.toString());
						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames);	// 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}
	
	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	@SuppressWarnings({ "unchecked" })
	public static void export2ExcelByMap(ExportSetInfo setInfo) throws 
		IOException, IllegalArgumentException, IllegalAccessException
	{
		init();
		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List> entry : set)
		{
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		XSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames);
		
		int sheetNum = 0;
		for (Entry<String, List> entry : set)
		{
			// Sheet
			List<Map<String, Object>> objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 3;
			for (Map<String, Object> obj : objs)
			{
				XSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				XSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
				int cellNum = 1;					// 去掉一列序号，因此从1开始
				if(fieldNames != null)
				{
					for (int num = 0; num < fieldNames.length; num++)
					{
						Object value = obj.get(fieldNames[num]);
						cells[cellNum].setCellValue(value == null ? "" : value.toString());
						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames);	// 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	/**
	 * @Description: 初始化
	 */
	private static void init()
	{
		wb = new XSSFWorkbook();
		
		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();
		
		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	/**
	 * @Description: 自动调整列宽
	 */
	@SuppressWarnings("unused")
	private static void adjustColumnSize(XSSFSheet[] sheets, int sheetNum,
			String[] fieldNames)
	{
		for(int i = 0; i < fieldNames.length + 1; i++)
		{
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(ExportSetInfo setInfo,
			XSSFSheet[] sheets, int sheetNum)
	{
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		XSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short) 800);
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
	}

	/**
	 * @Description: 创建日期行(需合并单元格)
	 */
	private static void createTableDateRow(ExportSetInfo setInfo,
			XSSFSheet[] sheets, int sheetNum)
	{
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(dateRange);
		XSSFRow dateRow = sheets[sheetNum].createRow(1);
		dateRow.setHeight((short) 350);
		XSSFCell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(dateStyle);
		dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(ExportSetInfo setInfo,
			XSSFSheet[] sheets, int sheetNum)
	{
		// 表头
		XSSFRow headRow = sheets[sheetNum].createRow(2);
		headRow.setHeight((short) 350);
		// 序号列
		XSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for(int num = 1, len = setInfo.getHeadNames().get(sheetNum).length; num <= len; num++)
		{
			String columnName = setInfo.getHeadNames().get(sheetNum)[num - 1];
			XSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(columnName);
			if("pk".equals(columnName)){
				sheets[sheetNum].setColumnHidden(num, true);
			}
		}
	}

	/**
	 * @Description: 创建所有的Sheet
	 */
	private static XSSFSheet[] getSheets(int num, String[] names)
	{
		XSSFSheet[] sheets = new XSSFSheet[num];
		for (int i = 0; i < num; i++)
		{
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}

	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static XSSFCell[] getCells(XSSFRow contentRow, int num)
	{
		XSSFCell[] cells = new XSSFCell[num + 1];
		
		for (int i = 0,len = cells.length; i < len; i++)
		{
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		cells[0].setCellValue(contentRow.getRowNum() - 2);

		return cells;
	}

	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle()
	{
		titleStyle.setAlignment(HorizontalAlignment.LEFT);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化日期行样式
	 */
	private static void initDateCellStyle()
	{
		dateStyle.setAlignment(HorizontalAlignment.LEFT);
		dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle()
	{
		/*headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
		headStyle.setBorderTop(BorderStyle.MEDIUM);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.index);
		headStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		headStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		headStyle.setRightBorderColor(IndexedColors.BLACK.index);*/
		
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.index);
		headStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		headStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		headStyle.setRightBorderColor(IndexedColors.BLACK.index);
		
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle()
	{
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(BorderStyle.THIN);
		contentStyle.setBorderBottom(BorderStyle.THIN);
		contentStyle.setBorderLeft(BorderStyle.THIN);
		contentStyle.setBorderRight(BorderStyle.THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLACK.index);
		contentStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		contentStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		contentStyle.setRightBorderColor(IndexedColors.BLACK.index);
		contentStyle.setWrapText(true);	// 字段换行
	}
	
	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont()
	{
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBold(true);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化日期行字体
	 */
	private static void initDateFont()
	{
		dateFont.setFontName("宋体");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBold(true);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont()
	{
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBold(true);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont()
	{
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBold(false);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.index);
	}
	
	
	/**
	 * @Description: 封装Excel导出的设置信息
	 * @author: 谭凯凯
	 */
	public static class ExportSetInfo
	{
		@SuppressWarnings("unchecked")
		private LinkedHashMap<String, List> objsMap;
		
		private String[] titles;
		
		private List<String[]> headNames;
		
		private List<String[]> fieldNames;
		
		private OutputStream out;

		
		@SuppressWarnings("unchecked")
		public LinkedHashMap<String, List> getObjsMap()
		{
			return objsMap;
		}

		/**
		 * @param objMap 导出数据
		 * 
		 * 泛型
		 * String : 代表sheet名称
		 * List : 代表单个sheet里的所有行数据
		 */
		@SuppressWarnings("unchecked")
		public void setObjsMap(LinkedHashMap<String, List> objsMap)
		{
			this.objsMap = objsMap;
		}

		public List<String[]> getFieldNames()
		{
			return fieldNames;
		}

		/**
		 * @param clazz 对应每个sheet里的每行数据的对象的属性名称
		 */
		public void setFieldNames(List<String[]> fieldNames)
		{
			this.fieldNames = fieldNames;
		}

		public String[] getTitles()
		{
			return titles;
		}

		/**
		 * @param titles 对应每个sheet里的标题，即顶部大字
		 */
		public void setTitles(String[] titles)
		{
			this.titles = titles;
		}

		public List<String[]> getHeadNames()
		{
			return headNames;
		}

		/**
		 * @param headNames 对应每个页签的表头的每一列的名称
		 */
		public void setHeadNames(List<String[]> headNames)
		{
			this.headNames = headNames;
		}

		public OutputStream getOut()
		{
			return out;
		}

		/**
		 * @param out Excel数据将输出到该输出流
		 */
		public void setOut(OutputStream out)
		{
			this.out = out;
		}
	}
}