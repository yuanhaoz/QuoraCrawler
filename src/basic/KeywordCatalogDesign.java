package basic;

/**
 * zhengyuanhao  2015/6/30
 * 简单实现：quora  
 * 实现功能：1.为每个主题词生成用于存储网页数据的文件夹（setKeywordCatalog）
 * 			2.定位到主题词文件夹（GetKeywordCatalog）
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import basic.DirFile;

public class KeywordCatalogDesign {

	/**
	 * 实现功能：根据课程名，为该课程目录下的所有关键词页面生成对应文件夹 
	 * 			输入是课程名，输出是生成相应文件夹
	 * 
	 * @param course
	 */
	public static void setKeywordCatalog(String course){
		String catalog = "file/datacollection/" + course + "/";
		ArrayList<String> a = DirFile
				.getFileNamesFromDirectorybyArraylist(catalog); // 读取所有文件名
		Iterator<String> it = a.iterator();
		while (it.hasNext()) { // 判断是否有下一个
			String keyword = it.next();
//			System.out.println(course + "课程目录下有如下关键词：" + keyword);
			String keywordcatalog = catalog + keyword + "/";
			File file = new File(keywordcatalog);
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 实现功能：根据关键词，定位到相应关键词目录 
	 * 			用于存放“主题”、“问题”和“作者”页面 
	 * 			输入是关键词，输出是文件路径
	 * 
	 * @param keyword
	 */
	public static String GetKeywordCatalog(String keyword) {
		String keywordcatalog = "";
		String[] course = { "Data_structure" };   //只有一门课程
		for (int i = 0; i < course.length; i++) {
			String catalog = "file/datacollection/" + course[i] + "/";
			ArrayList<String> a = DirFile.getFolderFileNamesFromDirectorybyArraylist(catalog); // 读取所有文件名
			for (int j = 0; j < a.size(); j++) {
				String testkeyword = a.get(j);
//				System.out.println("关键词：" + keyword);
				if (keyword.equals(testkeyword)) {
					keywordcatalog = catalog + keyword + "/";
				}
			}
		}
		return keywordcatalog;
	}

	/**
	 * 实现功能：根据关键词，定位到相应关键词目录 
	 * 			用于存放“主题”、“问题”和“作者”页面 
	 * 			输入是关键词，输出是文件路径
	 * （输入包含课程名）
	 * @param keyword
	 */
	public static String GetKeywordCatalog(String course, String keyword) {
		String keywordcatalog = "";
		String catalog = "file/datacollection/" + course + "/";
		ArrayList<String> a = DirFile.getFileNamesFromDirectorybyArraylist(catalog); // 读取所有文件名
		for (int j = 0; j < a.size(); j++) {
			String testkeyword = a.get(j);
			System.out.println("关键词：" + keyword);
			if (keyword.equals(testkeyword)) {
				keywordcatalog = catalog + keyword + "/";
			}
		}
		return keywordcatalog;
	}
	
	/**
	 * 实现功能：根据关键词，定位到相应关键词目录 
	 * 			用于存放标注过的数据
	 * 			输入是关键词，输出是对应路径
	 * 
	 * @param keyword
	 */
	public static String getTagKeywordCatalog(String keyword) {
		String keywordcatalog = "";
		String catalog = "file/标注术语/";
		
		// 读取所有文件名
		ArrayList<String> a = DirFile.getFolderFileNamesFromDirectorybyArraylist(catalog); 
		for (int j = 0; j < a.size(); j++) {
			String testkeyword = a.get(j);
			// System.out.println("关键词：" + keyword);
			if (keyword.equals(testkeyword)) {
				keywordcatalog = catalog + keyword + "/";
			}
		}
		return keywordcatalog;
	}

}
