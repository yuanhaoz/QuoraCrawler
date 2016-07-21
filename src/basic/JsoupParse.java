package basic;

/**
 * zhengyuanhao  2015/6/30
 * 简单实现：quora  
 * 实现功能：Jsoup获取HTML解析对象的几种方法：
 *          1.从一个URL加载一个Document对象；
 *          2.根据一个文件加载Document对象；
 *          3.解析一个html字符串。
 * 
 */

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParse {

	/**
	 * 实现功能：根据一个文件加载Document对象， 解析指定路径的html文件
	 * @param path
	 */
	public static Document parsePathText(String path) {
		Document doc = null;
		try {
			File input = new File(path);
			doc = Jsoup.parse(input, "UTF-8", "http://www.quora.com");  //Quora��վ
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 实现功能：从一个URL加载一个Document对象
	 * @param URL
	 */
	public static Document parseURLText(String URL) {
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
//			String title = doc.title();     
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 实现功能：解析一个html字符串
	 * @param 
	 */
	public static Document parseHtmlText() {
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		return doc;
	}
	
	/**
	 * 实现功能：主函数
	 * @param 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
}
