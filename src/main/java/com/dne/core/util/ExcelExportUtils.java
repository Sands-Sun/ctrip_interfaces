package com.dne.core.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * @Author: qqwx
 * @Date: 2017-12-24 22:46:46
 */
public class ExcelExportUtils {

	private static XSSFWorkbook wb;

	private static CellStyle titleStyle; // 标题行样式

	private static Font titleFont; // 标题行字体

	private static CellStyle dateStyle; // 日期行样式

	private static Font dateFont; // 日期行字体

	private static CellStyle headStyle; // 表头行样式

	private static Font headFont; // 表头行字体

	private static CellStyle contentStyle; // 内容行样式

	private static Font contentFont; // 内容行字体

	public static <T> void export2ExcelByPojo(Collection<T> dataList, String[] headNames, String[] fieldNames, String sheetName, String title, String destPath) throws IOException {
		File file = new File(destPath).getParentFile();
		if (!file.exists()) {
			file.mkdirs();
		}
		ExportSetInfo<T> setInfo = new ExportSetInfo<T>();
		setInfo.addTitle(title);
		setInfo.addHeadName(headNames);
		setInfo.addFieldName(fieldNames);
		setInfo.addSheetData(sheetName, dataList);
		OutputStream os = null;
		try {
			os = new FileOutputStream(destPath);
			setInfo.setOut(os);
			ExcelExportUtils.export2ExcelByPojo(setInfo);
		} finally {
			if (null != os) {
				os.close();
			}
		}
	}

	/**
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	public static <T> void export2ExcelByPojo(ExportSetInfo<T> setInfo) throws IOException {
		init();
		Set<Entry<String, Collection<T>>> set = setInfo.getSheetDataMap().entrySet();
		String[] sheetNames = new String[setInfo.getSheetDataMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, Collection<T>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		XSSFSheet[] sheets = getSheets(setInfo.getSheetDataMap().size(), sheetNames);

		int sheetNum = 0;
		for (Entry<String, Collection<T>> entry : set) {
			int rowNum = 2;
			int seq = 1;
			// Sheet
			Collection<T> objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			if (setInfo.isDateCols()) {
				rowNum = 3;
				seq = 2;
				createTableDateRow(setInfo, sheets, sheetNum);
			}
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			for (Object obj : objs) {
				XSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short)300);
				XSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length, seq);
				int cellNum = 1; // 去掉一列序号，因此从1开始
				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {
						Object value = ReflectionUtils.invokeGetterMethod(obj, fieldNames[num]);
						cells[cellNum].setCellValue(value == null ? "" : value.toString());
						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	/**
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	public static void export2ExcelByMap(ExportSetInfo<Map<String, Object>> setInfo) throws IOException, IllegalArgumentException, IllegalAccessException {
		init();
		Set<Entry<String, Collection<Map<String, Object>>>> set = setInfo.getSheetDataMap().entrySet();
		String[] sheetNames = new String[setInfo.getSheetDataMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, Collection<Map<String, Object>>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		XSSFSheet[] sheets = getSheets(setInfo.getSheetDataMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, Collection<Map<String, Object>>> entry : set) {
			int rowNum = 2;
			int seq = 1;
			// Sheet
			Collection<Map<String, Object>> objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			if (setInfo.isDateCols()) {
				rowNum = 3;
				seq = 2;
				createTableDateRow(setInfo, sheets, sheetNum);
			}
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			for (Map<String, Object> obj : objs) {
				XSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short)300);
				XSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length, seq);
				int cellNum = 1; // 去掉一列序号，因此从1开始
				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {
						Object value = obj.get(fieldNames[num]);
						cells[cellNum].setCellValue(value == null ? "" : value.toString());
						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	public static List<String[]> readExcel(String filePath) throws IOException {
		if (!filePath.toLowerCase().startsWith("http")) {
			filePath = "file:///" + filePath;
		}
		URL url = new URL(filePath);
		return readExcel(url.openStream(), filePath);
	}

	/**
	 * 用POI读取Excel文件内容
	 * @param stream
	 * @param fileName
	 * @return
	 */
	public static List<String[]> readExcel(InputStream stream, String fileName) {
		List<String[]> list = Lists.newArrayList();
		Workbook wb = null;
		Row row = null;
		try {
			if (fileName.endsWith("xls")) {
				wb = new HSSFWorkbook(stream);
			} else if (fileName.endsWith("xlsx")) {
				wb = new XSSFWorkbook(stream);
			} else {
				return list;
			}
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {// 第一行标题不读取
				row = sheet.getRow(i);
				if (null != row) {
					int cellSize = row.getPhysicalNumberOfCells();
					String[] str = new String[cellSize];// 创建一个数组,用来存储每一列的值
					for (int j = 0; j < cellSize; j++) {
						str[j] = parseExcel(row.getCell(j)).replaceAll(" ", "");
					}
					/* 判断数据是否全部为空 */
					if (!checkEmpty(str)) {
						list.add(str);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private static String parseExcel(Cell cell) {
		String result = "";
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case NUMERIC:// 数字类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
						sdf = new SimpleDateFormat("HH:mm:ss");
					} else {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date date = cell.getDateCellValue();
					result = sdf.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 58) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
					result = sdf.format(date);
				} else {
					double value = cell.getNumericCellValue();
					CellStyle style = cell.getCellStyle();
					DecimalFormat format = new DecimalFormat();
					String temp = style.getDataFormatString();
					if (temp.equals("General")) {
						format.applyPattern("#");
					}
					result = format.format(value);
				}
				break;
			case STRING:// String类型
				result = cell.getRichStringCellValue().toString();
				break;
			case BLANK:
				result = "";
			default:
				result = "";
				break;
			}
		}
		return result;
	}

	/**
	 * @Description: 初始化
	 */
	private static void init() {
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
	private static void adjustColumnSize(XSSFSheet[] sheets, int sheetNum, String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static <T> void createTableTitleRow(ExportSetInfo<T> setInfo, XSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		XSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short)600);
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(setInfo.getTitles().get(sheetNum));
	}

	/**
	 * @Description: 创建日期行(需合并单元格)
	 */
	private static <T> void createTableDateRow(ExportSetInfo<T> setInfo, XSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(dateRange);
		XSSFRow dateRow = sheets[sheetNum].createRow(1);
		dateRow.setHeight((short)350);
		XSSFCell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(dateStyle);
		dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static <T> void creatTableHeadRow(ExportSetInfo<T> setInfo, XSSFSheet[] sheets, int sheetNum) {
		// 表头
		XSSFRow headRow = sheets[sheetNum].createRow(setInfo.isDateCols() ? 2 : 1);
		headRow.setHeight((short)350);
		// 序号列
		XSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("No.");

		// 列头名称
		for (int num = 1, len = setInfo.getHeadNames().get(sheetNum).length; num <= len; num++) {
			XSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num - 1]);
		}
	}

	/**
	 * @Description: 创建所有的Sheet
	 */
	private static XSSFSheet[] getSheets(int num, String[] names) {
		XSSFSheet[] sheets = new XSSFSheet[num];
		for (int i = 0; i < num; i++) {
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}

	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static XSSFCell[] getCells(XSSFRow contentRow, int num, int seq) {
		XSSFCell[] cells = new XSSFCell[num + 1];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		cells[0].setCellValue(contentRow.getRowNum() - seq);

		return cells;
	}

	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化日期行样式
	 */
	private static void initDateCellStyle() {
		dateStyle.setAlignment(HorizontalAlignment.LEFT);
		dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		setCrllStyle(headStyle);
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setFont(contentFont);
		setCrllStyle(contentStyle);
		contentStyle.setWrapText(true); // 字段换行
	}

	private static void setCrllStyle(CellStyle cellSyle) {
		cellSyle.setBorderTop(BorderStyle.THIN);
		cellSyle.setBorderBottom(BorderStyle.THIN);
		cellSyle.setBorderLeft(BorderStyle.THIN);
		cellSyle.setBorderRight(BorderStyle.THIN);
		cellSyle.setTopBorderColor(IndexedColors.BLACK.index);
		cellSyle.setBottomBorderColor(IndexedColors.BLACK.index);
		cellSyle.setLeftBorderColor(IndexedColors.BLACK.index);
		cellSyle.setRightBorderColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short)16);
		titleFont.setBold(true);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化日期行字体
	 */
	private static void initDateFont() {
		dateFont.setFontName("隶书");
		dateFont.setFontHeightInPoints((short)10);
		dateFont.setBold(true);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short)10);
		headFont.setBold(true);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLACK.index);
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short)10);
		contentFont.setBold(false);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.index);
	}

	private static boolean checkEmpty(String[] datas) {
		boolean result = true;
		for (String data : datas) {
			result = result && StringUtils.isEmpty(data);
			if (!result) {
				break;
			}
		}
		return result;
	}

	/**
	 * @Description: 封装Excel导出的设置信息
	 * @author: 谭凯凯
	 */
	public static class ExportSetInfo<T> {

		private Map<String, Collection<T>> sheetDataMap = Maps.newLinkedHashMap();

		private List<String> titles = Lists.newArrayList();

		private List<String[]> headNames = Lists.newArrayList();

		private List<String[]> fieldNames = Lists.newArrayList();

		private boolean dateCols = false;

		private OutputStream out;

		/**
		 * @param titles 对应每个sheet里的标题，即顶部大字
		 */
		public void addTitle(String title) {
			this.titles.add(title);
		}

		/**
		 * 对应每个页签的表头的每一列的名称
		 * @param headName
		 */
		public void addHeadName(String[] headName) {
			this.headNames.add(headName);
		}

		/**
		 * @param clazz 对应每个sheet里的每行数据的对象的属性名称
		 */
		public void addFieldName(String[] fieldName) {
			this.fieldNames.add(fieldName);
		}

		/**
		 * @param sheetName sheet名称
		 * @param objs 导出数据泛型 String : 代表sheet名称 List : 代表单个sheet里的所有行数据
		 */
		public void addSheetData(String sheetName, Collection<T> objs) {
			this.sheetDataMap.put(sheetName, objs);
		}

		public List<String> getTitles() {
			return titles;
		}

		public List<String[]> getHeadNames() {
			return headNames;
		}

		public List<String[]> getFieldNames() {
			return fieldNames;
		}

		public Map<String, Collection<T>> getSheetDataMap() {
			return sheetDataMap;
		}

		public boolean isDateCols() {
			return dateCols;
		}

		public void setDateCols(boolean dateCols) {
			this.dateCols = dateCols;
		}

		public OutputStream getOut() {
			return out;
		}

		public void setSheetDataMap(Map<String, List<T>> sheetDataMap) {
			this.sheetDataMap.putAll(sheetDataMap);
		}

		public void setTitles(List<String> titles) {
			this.titles.addAll(titles);
		}

		public void setHeadNames(List<String[]> headNames) {
			this.headNames.addAll(headNames);
		}

		public void setFieldNames(List<String[]> fieldNames) {
			this.fieldNames.addAll(fieldNames);
		}

		/**
		 * @param out Excel数据将输出到该输出流
		 */
		public void setOut(OutputStream out) {
			this.out = out;
		}

	}
}
