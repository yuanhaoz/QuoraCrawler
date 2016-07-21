package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/*
 * 目的：提供基本的目录及文件操作
 */
public class DirFile {

	// 读取某个目录下的所有文件的名字。（乱序）
	// 只有文件名，没有扩展名，没有路径。
	// 只能读取文件名（不能读取文件夹的名字）
	static public Set<String> getFileNamesFromDirectory(String dir){
		Set<String> nameWithoutExt=new HashSet<String>();
		File f = new File(dir);
		File[] childs = f.listFiles();
		System.out.println("读取目录……  "+ dir);
		for(File file:childs){
			String fileName = file.getName();
			int pos=fileName.lastIndexOf(".");
			if(pos>1){
				String realName = fileName.substring(0, pos); //
				nameWithoutExt.add(realName);
			}
		}
		System.out.println("acqure name，总数="+ nameWithoutExt.size());	
		return nameWithoutExt;
	}
	
	// 读取某个目录下的所有文件的名字。（顺序）
	// 只有文件名，没有扩展名，没有路径。
	// 只能读取文件名（不能读取文件夹的名字）
	static public ArrayList<String> getFileNamesFromDirectorybyArraylist(String dir) {
		ArrayList<String> nameWithoutExt = new ArrayList<String>();
		File f = new File(dir);
		File[] childs = f.listFiles();
		System.out.println("读取目录……  " + dir);
		for (File file : childs) {
			String fileName = file.getName();
			int pos = fileName.lastIndexOf(".");
			if (pos > 1) {
				String realName = fileName.substring(0, pos); //
				nameWithoutExt.add(realName);
			}
		}
		System.out.println("acqure name，总数=" + nameWithoutExt.size());
		return nameWithoutExt;
	}
	
	// 读取某个目录下的所有文件夹的名字。（乱序）
	// 只能读取文件夹（不能读取文件名）
	static public Set<String> getFolderFileNamesFromDirectory(String dir) {
		Set<String> nameFolder = new HashSet<String>();
		File f = new File(dir);
		File[] childs = f.listFiles();
		System.out.println("读取目录……  " + dir);
		for (File file : childs) {
			String fileName = file.getName();
			if (!fileName.contains(".")) {
				String realName = fileName;
				nameFolder.add(realName);
			}
		}
		System.out.println("acqure name，总数=" + nameFolder.size());
		return nameFolder;
	}
	
	// 读取某个目录下的所有文件夹的名字。（顺序）
	// 只能读取文件夹（不能读取文件名）
	static public ArrayList<String> getFolderFileNamesFromDirectorybyArraylist(String dir) {
		ArrayList<String> nameFolder = new ArrayList<String>();
		File f = new File(dir);
		File[] childs = f.listFiles();
		System.out.println("读取目录……  " + dir);
		for (File file : childs) {
			String fileName = file.getName();
			if (!fileName.contains(".")) {
				String realName = fileName; 
				nameFolder.add(realName);
			}
		}
		System.out.println("acqure name，总数=" + nameFolder.size());
		return nameFolder;
	}
	
	//1)保存源文件的回车。
	//2)没有最后一个回车。
	static public String getStringFromPathFile(String fileName){
		FileReader fr;
		StringBuffer sb=new StringBuffer();	
		try {
			fr = new FileReader(fileName);
			System.out.println("读取文件……  "+ fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s+"\r\n");				
			}
			if(sb.length()>2) sb.delete(sb.lastIndexOf("\r\n"), sb.length());			
			br.close();
			fr.close();			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	       if(sb.length()>65560){
//	    	   System.err.println("error: from getStringFromPathFile "+ sb.length() +"->"+ sb.toString().length());
//	       }
		return sb.toString();
	}
	
	static public void storeString2File(String data, String outFile){
			try {
				System.out.println("writing file: "+outFile);
				FileWriter fw=new FileWriter(outFile);
				fw.write(data.toString());
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	static public void storeString2File(StringBuffer data, String outFile){
//       if(data.length()>65560){
//    	   System.err.println("error: from storeString2File --data.length()>65560");
//       }		
       storeString2File(data.toString(),outFile);
	}
	
	static public void createFileCatalog(String file){
		File   a=new   File( file); 
		if(!a.exists()){
			a.mkdir();
		} else {
//			System.out.println("*****************");
		}
	}
	static public void delFile(String file){
	    File   a=new   File( file); 
	    System.err.println("delete file: "+file);
	    a.delete(); 
	}
	
	public static boolean copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}else{
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean moveFile(String oldPath, String newPath){
		 //文件原地址 
		  File oldFile = new   File(oldPath);   
		  //文件新（目标）地址 
//		  String newPath = "c:/test/";   
		  //new一个新文件夹 
//		  File fnewpath = new File(newPath); 
		  //判断文件夹是否存在  
//		  if(!fnewpath.exists())   
//		          fnewpath.mkdirs();  
		  //将文件移到新文件里 
//		  File fnew = new File(newPath +oldFile.getName());   
		  File fnew= new File(newPath);   
		  oldFile.renameTo(fnew);
		  return true;
	}
	
	//文件末尾添加一行
	public static void append(String line,String outfile){
		try {
			File file=new File(outfile);
			if(!file.exists()){
				System.err.println("文件不存在，准备创建："+outfile);
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(outfile,true);
			writer.write(line + "\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
