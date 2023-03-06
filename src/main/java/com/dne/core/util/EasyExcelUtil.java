package com.dne.core.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.dne.ctrip.excel.model.SeparationEmpInfoRowModel;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class EasyExcelUtil {


	public  <T extends BaseRowModel> Pair<List<T>, Integer> readExcel2007(
			InputStream inputStream, Integer sheetNo, Integer headLineMun,ExcelTypeEnum typeEnum, Class<T> clazz) {
		System.out.println("--------start-------------");
		ExcelListener<T> listener = new ExcelListener<>();
		ExcelReader excelReader = new ExcelReader(inputStream, typeEnum,null, listener);
		excelReader.read(new Sheet(sheetNo, headLineMun, clazz));
		return Pair.of(listener.getDatas(),listener.getLastRowNum());
	}

	public <T extends BaseRowModel> void writeExcel2007(
			List<List<? extends BaseRowModel>> dataList,OutputStream outputStream, List<String> sheetNameList,
			Integer headLineMun,ExcelTypeEnum typeEnum,List<Class<T>> clazzList) {
		ExcelWriter excelWriter = new ExcelWriter(outputStream,typeEnum);
		for(int i = 0; i < dataList.size(); i++ ){
			int sheetNo = i + 1;
			List<? extends BaseRowModel> data = dataList.get(i);
			Class<T> clazz = clazzList.get(i);
			String sheetName = sheetNameList.get(i);
			Sheet sheet = new Sheet(sheetNo, headLineMun, clazz);
			sheet.setSheetName(sheetName);
			excelWriter.write(data,sheet);
		}
	}



	public static void main(String[] args) {

	}

	private void testWrite() {
		String billFilePath = "C:\\AppS\\Didi\\employee-infomation\\abc-Employee_info.xlsx";
		File file = new File(billFilePath);
		file.deleteOnExit();
		try {
			OutputStream outputStream = Files.newOutputStream(file.toPath(),
					StandardOpenOption.CREATE_NEW);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void testRead() {
		String billFilePath = "C:\\AppS\\Didi\\employee-infomation\\Employee_info.xlsx";
		InputStream inputStream;
		try {
			inputStream = Files.newInputStream(Paths.get(billFilePath));
			EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
			Pair<List<SeparationEmpInfoRowModel>, Integer> pair =
					easyExcelUtil.readExcel2007(inputStream,2,1,ExcelTypeEnum.XLSX, SeparationEmpInfoRowModel.class);
			List<SeparationEmpInfoRowModel> models = pair.getKey();
			Integer lastRowNum = pair.getRight();
			System.out.println(models.toString());
			System.out.println(lastRowNum);

		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
