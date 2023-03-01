/*
 * @(#)FileUtils.java	2016年12月2日下午10:21:23
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 
 * 类<strong>FileUtils.java</strong>{文件操作工具类}
 * @author: wangyf
 * @version: 1.0 Date: 2016年12月2日 下午10:21:23
 */
public class FileUtils {

	public static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * 根据文件名从不同的位置定位文件
	 * 
	 * @param filename
	 * @return
	 */
	public static URL getURL(String filename) {
		URL url = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		url = cl.getResource(filename);
		if (null == url) {
			cl = FileUtils.class.getClassLoader();
			url = cl.getResource(filename);
		}
		if (null == url) {
			url = ClassLoader.getSystemResource(filename);
		}
		if (null == url) {
			File file = new File(filename);
			if (file.exists()) {
				try {
					url = new File(filename).toURI().toURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		if (null == url) {
			throw new RuntimeException("The file not found: " + filename);
		}
		return url;
	}

	/**
	 * 计算文件大小
	 * @param fileLength
	 * @return
	 */
	public static String getFileSize(long fileLength) {
		fileLength = fileLength / 1024;
		String fileSize = "1 KB";
		if (fileLength >= 1024) {
			fileSize = String.valueOf((int)(fileLength / 1024f * 100) / 100f) + " MB";// 保留两位小数
		} else if (fileLength > 0) {
			fileSize = String.valueOf(fileLength) + " KB";
		}
		return fileSize;
	}

	/**
	 * 文件拷贝-缓冲方式
	 * @param source 源文件
	 * @param dest 目标文件
	 * @param isDelete 是否删除源文件
	 */
	public static void copyFile(File source, File dest, boolean isDelete) {
		if (!source.exists()) {
			return;
		}
		InputStream is = null;
		OutputStream os = null;
		int readed = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			is = new BufferedInputStream(new FileInputStream(source), BUFFER_SIZE);
			os = new BufferedOutputStream(new FileOutputStream(dest), BUFFER_SIZE);
			while ((readed = is.read(buffer)) != -1) {
				os.write(buffer, 0, readed);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			isDelete = false;
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isDelete) {
				source.delete();
			}
		}
	}

	/**
	 * 保存文件
	 * @param is 源文件流
	 * @param filePath 目标文件
	 */
	public static int saveFile(InputStream is, String filePath) {
		OutputStream os = null;
		int readed = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		int fileLenth = 0;
		try {
			fileLenth = is.available();
			os = new BufferedOutputStream(new FileOutputStream(filePath), BUFFER_SIZE);
			while ((readed = is.read(buffer)) != -1) {
				os.write(buffer, 0, readed);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileLenth;
	}

	/**
	 * 写入文件
	 * @param file
	 * @param content
	 */
	public static void writerFile(String file, String content) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 追加文件末尾
	 * @param fileName
	 * @param content
	 */
	public static void appendFile(String fileName, String content) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, true);// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 保存对象到文件
	 * @param obj
	 * @param fileName
	 */
	public static void saveObjToFile(Object obj, String fileName) {
		File file = new File(fileName);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			if (!file.exists()) {
				file.createNewFile();
			}
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件读取对象
	 * @param fileName
	 * @return
	 */
	public static Object getObjFromFile(String fileName) {
		Object obj = null;
		File file = new File(fileName);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				obj = ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public static void copyFile(InputStream in, String file) throws IOException {
		Path target = Paths.get(file);
		if (Files.notExists(target.getParent())) {
			Files.createDirectories(target.getParent());
		}
		Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
	}

}
