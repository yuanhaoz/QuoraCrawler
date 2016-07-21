package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.Html;

/**
 * 优势：可以得到答案的时间信息，并且能够调用JS操作滚动条下拉页面刷新页面
 * @author zhengyuanhao
 */
@SuppressWarnings("deprecation")
public class QuoraWebPage {

	public static void main(String[] args) throws Exception {
//		String url = "http://www.quora.com";
//		String filePath = "file/testdata/1.html";
////		String filepath = "F:/photo.html";
////		QuoraWebPage.httpClientCrawler(filepath, url);
//		QuoraWebPage.seleniumCrawlerSubject(filePath, url);
	}
	
	/**
	 * 实现功能：爬取主题页面，输入是 "页面保存路径filepath" 和"主题页面链接url"
	 * 下拉四次主题页面，10秒内没打开页面会重开（问题数目为49-59），调整下拉次数可以得到问题数目会增加
	 * 使用技术：selenium
	 */
	public static void seleniumCrawlerTopic(String filePath, String url) throws InterruptedException {
		File file = new File(filePath);
		if(!file.exists()){
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);    //5秒内没打开，重新加载
			while (true){
				try{
					driver.get(url);
				}
				catch (Exception e)
				{
					driver.quit();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
					continue;
				}
				break;
			}
			System.out.println("Page title is: " + driver.getTitle());
			// roll the page
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			try {
				JS.executeScript("scrollTo(0, 5000)");
				System.out.println("1");
				Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
				JS.executeScript("scrollTo(5000, 10000)");
				System.out.println("2");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(10000, 30000)"); // document.body.scrollHeight
				System.out.println("3");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(10000, 80000)"); // document.body.scrollHeight
				System.out.println("4");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(80000, 90000)"); // document.body.scrollHeight
				System.out.println("5");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(90000, 100000)"); // document.body.scrollHeight
				System.out.println("6");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(100000, 130000)"); // document.body.scrollHeight
				System.out.println("7");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(130000, 160000)"); // document.body.scrollHeight
				System.out.println("8");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(160000, 170000)"); // document.body.scrollHeight
				System.out.println("9");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(170000, 180000)"); // document.body.scrollHeight
				System.out.println("10");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(180000, 190000)"); // document.body.scrollHeight
				System.out.println("11");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(190000, 200000)"); // document.body.scrollHeight
				System.out.println("12");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(200000, 210000)"); // document.body.scrollHeight
				System.out.println("13");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(220000, 230000)"); // document.body.scrollHeight
				System.out.println("14");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(230000, 240000)"); // document.body.scrollHeight
				System.out.println("15");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(240000, 250000)"); // document.body.scrollHeight
				System.out.println("16");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(250000, 260000)"); // document.body.scrollHeight
				System.out.println("17");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(270000, 280000)"); // document.body.scrollHeight
				System.out.println("18");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(280000, 290000)"); // document.body.scrollHeight
				System.out.println("19");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(290000, 300000)"); // document.body.scrollHeight
				System.out.println("20");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(300000, 310000)"); // document.body.scrollHeight
				System.out.println("21");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(310000, 320000)"); // document.body.scrollHeight
				System.out.println("22");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(320000, 330000)"); // document.body.scrollHeight
				System.out.println("23");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(330000, 340000)"); // document.body.scrollHeight
				System.out.println("24");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(340000, 360000)"); // document.body.scrollHeight
				System.out.println("25");
				Thread.sleep(5000);
//				for(int i = 0; i < 30; i++){
//					String he = "scrollTo(" + i*5000 + ", " + (i+1)*5000 + ")";
//					JS.executeScript(he);
//					System.out.println("第" + (i+1) + "次拖动页面...");
//					Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
//				}
			} catch (Exception e) {
				System.out.println("Error at loading the page ...");
				driver.quit();
			}
			// save page
			String html = driver.getPageSource();
			saveHtml(filePath, html);
			System.out.println("save finish");
			// Close the browser
			Thread.sleep(2000);
			driver.quit();
		}else{
			System.out.println(filePath + "已经存在，不必再次爬取...");
		}
	}
	
	/**
	 * 实现功能：爬取主题页面，输入是 "页面保存路径filepath" 和"主题页面链接url"
	 * 下拉四次主题页面，10秒内没打开页面会重开（问题数目为49-59），调整下拉次数可以得到问题数目会增加
	 * 使用技术：selenium
	 */
	public static void seleniumCrawlerSubject(String filePath, String url) throws InterruptedException {
		File file = new File(filePath);
		if(!file.exists()){
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);    //5秒内没打开，重新加载
			while (true){
				try{
					driver.get(url);
				}
				catch (Exception e)
				{
					driver.quit();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					continue;
				}
				break;
			}
			System.out.println("Page title is: " + driver.getTitle());
			// roll the page
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			try {
				JS.executeScript("scrollTo(0, 5000)");
				System.out.println("1");
				Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
				JS.executeScript("scrollTo(5000, 10000)");
				System.out.println("2");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(10000, 30000)"); // document.body.scrollHeight
				System.out.println("3");
				Thread.sleep(5000);
				JS.executeScript("scrollTo(10000, 80000)"); // document.body.scrollHeight
				System.out.println("4");
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Error at loading the page ...");
				driver.quit();
			}
			// save page
			String html = driver.getPageSource();
			saveHtml(filePath, html);
			System.out.println("save finish");
			// Close the browser
			Thread.sleep(2000);
			driver.quit();
		}else{
			System.out.println(filePath + "已经存在，不必再次爬取...");
		}
	}
	
	/**
	 * 实现功能：爬取问题页面，输入是 “页面保存路径filepath” 和 “问题页面链接url”
	 * 下拉四次主题页面，10秒内没打开页面会重开（答案数目一般都在10条以内），调整下拉次数可以得到较为完整的答案较多的问题页面
	 * 使用技术：selenium
	 */
	public static void seleniumCrawlerQuestion(String filePath, String url) throws InterruptedException {
		File file = new File(filePath);
		if(!file.exists()){
//			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
//			WebDriver driver = new FirefoxDriver();
//			System.out.println("hehe");
			WebDriver driver = new InternetExplorerDriver();
//			System.out.println("hehe");
			int m = 1;
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);    //5秒内没打开，重新加载
			while (true){
				try{
					driver.get(url);
					// max size the browser
			        driver.manage().window().maximize();
				}
				catch (Exception e)
				{
//					driver.navigate().refresh();
					System.out.println("第" + m + "次重载页面...");
					if(m < 4){
						m++;
					} else {
						m = 4;
					}
					driver.quit();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().pageLoadTimeout(10*m, TimeUnit.SECONDS);
//					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					continue;
				}
				break;
			}
			System.out.println("begin to save page...");
			System.out.println("Page title is: " + driver.getTitle());
			// roll the page
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			try {
				JS.executeScript("scrollTo(0, document.body.scrollHeight)");
				System.out.println("1");
				Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
			} catch (Exception e) {
				System.out.println("Error at loading the page ...");
				e.printStackTrace();
				driver.quit();
			}
			// save page
			String html = driver.getPageSource();
			saveHtml(filePath, html);
			System.out.println("save finish");
			// Close the browser
			Thread.sleep(2000);
			driver.quit();
		}else{
			System.out.println(filePath + "已经存在，不必再次爬取...");
		}
	}
	
	/**
	 * 实现功能：爬取问题页面，输入是 “页面保存路径filepath” 和 “问题页面链接url”
	 * 下拉四次主题页面，10秒内没打开页面会重开（答案数目一般都在10条以内），调整下拉次数可以得到较为完整的答案较多的问题页面
	 * 使用技术：selenium
	 */
	public static void seleniumCrawlerQuestion2(String filePath, String url) throws InterruptedException {
		File file = new File(filePath);
		if(!file.exists()){
//			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
//			WebDriver driver = new FirefoxDriver();
//			System.out.println("hehe");
			WebDriver driver = new InternetExplorerDriver();
//			System.out.println("hehe");
			int m = 1;
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);    //5秒内没打开，重新加载
			while (true){
				try{
					driver.get(url);
				}
				catch (Exception e)
				{
					System.out.println("第" + m + "次重载页面...");
					if(m < 6){
						m++;
					} else {
						m = 6;
					}
					
					driver.quit();
					driver = new InternetExplorerDriver();
					driver.manage().timeouts().pageLoadTimeout(10*m, TimeUnit.SECONDS);
					continue;
				}
				break;
			}
			System.out.println("begin to save page...");
			System.out.println("Page title is: " + driver.getTitle());
			// roll the page
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			try {
				JS.executeScript("scrollTo(0, document.body.scrollHeight)");
				System.out.println("1");
				Thread.sleep(5000);             //调整休眠时间可以获取更多的内容
//				JS.executeScript("scrollTo(5000, 10000)");
//				System.out.println("2");
//				Thread.sleep(5000);
//				JS.executeScript("scrollTo(10000, 30000)"); // document.body.scrollHeight
//				System.out.println("3");
//				Thread.sleep(5000);
//				JS.executeScript("scrollTo(10000, 80000)"); // document.body.scrollHeight
//				System.out.println("4");
//				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Error at loading the page ...");
				e.printStackTrace();
				driver.quit();
			}
			// save page
			String html = driver.getPageSource();
//			System.out.println(html);
			saveHtml(filePath, html);
			System.out.println("save finish");
			// Close the browser
			Thread.sleep(2000);
			driver.quit();
		}else{
			System.out.println(filePath + "已经存在，不必再次爬取...");
		}
	}
	
	/**
	 * 实现功能：爬取作者页面，输入是"页面保存路径filepath" 和"作者页面链接url"
	 * 10秒内没打开页面会重开，无需下来页面，所需信息都在页面顶部
	 * 使用技术：selenium
	 */
	public static void seleniumCrawlerAuthor(String filePath, String url) throws InterruptedException {
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);    //5秒内没打开，重新加载
		while (true){
			try{
				driver.get(url);
			}
			catch (Exception e)
			{
				driver.quit();
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				continue;
			}
			break;
		}
		System.out.println(String.format("\nFetching %s...", url));
		// save page
		String html = driver.getPageSource();
		saveHtml(filePath, html);
		System.out.println("save finish");
		// Close the browser
		Thread.sleep(2000);
		driver.quit();
	}
	
	/**
	 * 实现功能：httpClient方法爬取所有页面，输入是 "页面保存路径filepath"和"主题页面链接"
	 * 使用技术：httpClient
	 */
	public static void httpClientCrawler(String filePath, String url) throws Exception{
		@SuppressWarnings("resource")
		HttpClient hc = new DefaultHttpClient();
		try
		{
			String charset = "UTF-8";
			System.out.println("filepath is : " + filePath);
		    System.out.println(String.format("\nFetching %s...", url));   	        	    
		    HttpGet hg = new HttpGet(url);     
		    hg.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		    hg.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		    hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1)");
		    hg.setHeader("Accept-Charset", "utf-8;q=0.7,*;q=0.7");
//		    hg.setHeader("Host", "en.wikipedia.org");
		    hg.setHeader("Host", "www.quora.com");
	        hg.setHeader("Connection", "Keep-Alive");
		    HttpResponse response = hc.execute(hg);
		    HttpEntity entity = response.getEntity();   	       	        
		    InputStream htmInput = null;       
		    if(entity != null){
		        htmInput = entity.getContent();
		        String htmString = inputStream2String(htmInput,charset);
		        System.out.println(htmString);
		        saveHtml(filePath, htmString);      //保存文件
		        System.out.println("爬取成功:" + " 网页长度为  " + entity.getContentLength());
		    }  
		}
		catch(Exception err) {
			System.err.println("爬取失败...失败原因: " + err.getMessage()); 		
		}
		finally {
	        //关闭连接，释放资源
	        hc.getConnectionManager().shutdown();
	    }
	}
	
	/**
	 * 得到页面html源码（调用webmagic框架的爬取器）
	 * 
	 * @return
	 */
	public static void webmagicCrawler(String filePath, String url) {
		// Site site = Site.me()//.setHttpProxy(new HttpHost("127.0.0.1",8888))
		// .setRetryTimes(3).setSleepTime(1000).setUseGzip(true);
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//		Html html = httpClientDownloader.download(url);
		Html html = httpClientDownloader.download(url, 3, "UTF-8");
		String content = html.toString();
		// System.out.println("HTML:"+content);
		saveHtml(filePath, content);      //保存文件
	}


	/**
	 * 实现功能：保存html字符串流到本地html文件，输入是"页面保存路径filepath"和"html字符串源码"
	 */
	public static void saveHtml(String filePath, String str) {
		try {
			OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filePath, true), "utf-8");
			outs.write(str);
			outs.close();
		} catch (IOException e) {
			System.out.println("Error at save html...");
			e.printStackTrace();
		}
	}
	
	/**
	 * 实现功能：输入流转为字符串流，输入是 "输入流InputStream"和"编码方式charset"
	 */
    public static String inputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }
    
    
}


