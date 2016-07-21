package basic;

/**
 * zhengyuanhao  2015/6/30
 * 简单实现：quora  
 * 实现功能：Excel单元格格式设置
 *          1.设置行高和列宽；
 *          2.合并单元格；
 *          3.设置标题单元格和正文单元格格式。
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@SuppressWarnings("deprecation")
public class ExcelSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * 实现功能：关闭工作流
	 * @param filepath
	 */
	public static void close(WritableWorkbook workbook) {
		try {
			workbook.write();
			workbook.close();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 实现功能：设置列宽
	 * @param sheet
	 */
	public void setColumnView(WritableSheet sheet) throws Exception{
		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
	}
	
	/**
	 * 实现功能：设置行高
	 * @param sheet
	 */
	public void setRowView(WritableSheet sheet) throws Exception{
		sheet.setRowView(0, 800, false);
		sheet.setRowView(1, 1000, false);
	}
	
	/**
	 * 实现功能：合并单元格
	 * @param sheet
	 */
	public void mergeCells(WritableSheet sheet) throws Exception{
		sheet.mergeCells(0, 0, 0, 1); // 合并分类单元格		
		sheet.mergeCells(1, 0, 3, 0);
	}
	
	/**
	 * 实现功能：设置"标题"单元格的格式
	 * @param 
	 */
	public static WritableCellFormat setTitleText() throws Exception{
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);  // 字体
		// 用于标题
		WritableCellFormat wcf_title = new WritableCellFormat(BoldFont);
		wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcf_title.setAlignment(Alignment.CENTRE); // 水平对齐
		wcf_title.setBackground(Colour.GRAY_25);
		wcf_title.setWrap(true); // 是否换行
		return wcf_title;
	}
	
	/**
	 * 实现功能：设置"正文"单元格的格式
	 * @param 
	 */
	public static WritableCellFormat setCenterText() throws Exception{
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  // 字体
		// 用于正文
		WritableCellFormat wcf_center = new WritableCellFormat(NormalFont);
		wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);  // 线条
		wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);   // 垂直对齐
		wcf_center.setAlignment(Alignment.CENTRE);  
		wcf_center.setWrap(true);   // 是否换行
		return wcf_center;
	}
	
	/**
	 * 1、完成Excel的基本读写，建立的Sheet可读写。
	 * 待改进：目前无法实现一个sheet用于读，另一个sheet用于写。
	 * 可以先读入可写的sheet，用该sheet创建可写对象。然后再打开只读的sheet。
	 * 2、使用方法：
	 * 1）继承该类。2）编写processing()函数。3)创建对象，初始化文件，执行go函数。
	 *      SheetBase ss;
	 * 		ss = new DestPageInfo();
	 *      String RdWrFile="d:/dir/xxx.xls";
	 *		ss.init(RdWrFile);
	 *		ss.go();
	 *
	 */
	Workbook SrcBook;
	protected Sheet SrcSheet;
	WritableWorkbook SinkBook;
	WritableSheet SinkSheet;
	public int startLine = 1;
	
	private String SrcFile=null;
	private String SinkFile=null;
	
	//存放Title的结构
	Map<String,Integer> srcTitle=null;
	Map<String,Integer> sinkTitle=null;
	
	//兼容马健代码
	Sheet sheet;
	WritableSheet wsheet;
	
	public void init(){
		assert(this.SrcFile != null && this.SinkFile!=null);
		init(this.SrcFile, this.SinkFile);
	}
//	public void init(String file){
//		init(file,file);
//	}

	//  初始化读写表
	public void init(String SrcFile, String SinkFile) {

		this.SrcFile = SrcFile;
		this.SinkFile = SinkFile;
		try {
			SrcBook = Workbook.getWorkbook(new File(SrcFile));
			SrcSheet = SrcBook.getSheet(0);

			SinkBook = Workbook.createWorkbook(new File(SinkFile), SrcBook);			
			SinkSheet = SinkBook.getSheet(0);

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//兼容马健代码
		sheet =SrcSheet;
		wsheet = SinkSheet;
		
		initTitle();
	}
	
	/**
	 * 实现功能：初始化需要写的新表格，自己用  
	 * zhengyuanhao
	 * 2015/7/2
	 */
	public void init(String SinkFile) {

		this.SinkFile = SinkFile;
		try {
			SinkBook = Workbook.createWorkbook(new File(SinkFile), SrcBook);			
			SinkSheet = SinkBook.createSheet("信息抽取", 0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//初始化标题
		initTitle();
	}
	
	public void initTitle(){
		srcTitle = new HashMap<String, Integer>();
		sinkTitle = new HashMap<String, Integer>();
		for(int i=0;i<SrcSheet.getColumns();i++){
			String title = getStringValue(i,0);
			srcTitle.put(title, i);
		}
		for(int i=0;i<SinkSheet.getColumns();i++){
			String title = getStringValue(i,0);
			sinkTitle.put(title, i);
		}
	}
	

	
	public void close() {
		try {
			SinkBook.write();
			SinkBook.close();
			SrcBook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	public void go(){
//		printConfigInfo();
//		printSpecificInfo();
//		try {
//
//				processing();
//	
//		} catch (RowsExceededException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (WriteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		close();
//		System.out.println("go() end");
//		
//	}
	
	protected void printConfigInfo(){
		System.out.println("startLine=" + startLine);
		System.out.println("SrcFile=" + SrcFile);
		System.out.println("SinkFile=" + SinkFile);
		System.out.println(srcTitle);
		System.out.println(sinkTitle);		
	}

//	abstract public void processing() throws RowsExceededException, WriteException, FileNotFoundException, IOException;
	
	public void printSpecificInfo(){
		
	}
	
	public void printTitle(){
		//int col=sheet.getColumns();
		
	}
	
	//设置及获取cell的内容，分别为字符串和整数索引
	public void setNumberValue(int col,int row, double value){
		Number number = new Number(col, row, value);
		try {
			SinkSheet.addCell(number);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setNumberValue(String colStr,int row, double value){
		Integer col = sinkTitle.get(colStr);
		assert(col != null);
		setNumberValue(col, row,value);
	}
	
	public void setNumberValue(int col,int row, int value){
		Number number = new Number(col, row, value);
		try {
			SinkSheet.addCell(number);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setNumberValue(String colStr,int row, int value){
		Integer col = sinkTitle.get(colStr);
		assert(col != null);
		setNumberValue(col, row,value);
	}
	
	public void setStringValue(int col,int row, String str){
		Label label = new Label(col, row, str);
		try {
			SinkSheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 实现功能：增加有单元格格式要求的单元  
	 * zhengyuanhao
	 * 2015/7/2
	 */
	public void setStringValue(int col,int row, String str, WritableCellFormat wcf_center){
		Label label = new Label(col, row, str, wcf_center);
		try {
			SinkSheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setStringValue(String colStr,int row, String str){
		Integer col = sinkTitle.get(colStr);
		assert(col != null);
		setStringValue(col, row,str);
	}
	public void setStringValue(String colStr,int row, boolean bool){
		Boolean Bool=new Boolean(bool);
		setStringValue(colStr, row,Bool.toString());
	}
	
	public double getDoubleValue(int col,int row){
		Cell cell = SrcSheet.getCell(col, row);
		// System.out.println(c11.getContents());
		double value = Double.parseDouble(cell.getContents());
		return value;
	}
	
	public double getDoubleValue(String colStr,int row){
		Integer col = srcTitle.get(colStr);
		assert(col != null);
		return getDoubleValue(col, row);
	}
	
	public Integer getIntegerValue(int col,int row){
		Cell cell = SrcSheet.getCell(col, row);
		// System.out.println(c11.getContents());
		Integer value = Integer.parseInt(cell.getContents());
		return value;
	}
	
	public Integer getIntegerValue(String colStr,int row){
		Integer col = srcTitle.get(colStr);
		assert(col != null);
		return getIntegerValue(col, row);
	}
	
	public String getStringValue(int col,int row){
		Cell cell = SrcSheet.getCell(col, row);
		// System.out.println(c11.getContents());
		String str = cell.getContents();
		return str;
	}
	
	public String getStringValue(String colStr,int row){
		Integer col = srcTitle.get(colStr);
		assert(col != null);
		return getStringValue(col, row);
	}
	
	
	//小功能
	public String modifyCategory(int col,int row){
		Cell cell = SrcSheet.getCell(col, row);
		// System.out.println(c11.getContents());
		String str = cell.getContents();
		if(str.equalsIgnoreCase("Other1")||str.equalsIgnoreCase("Other2")||str.equalsIgnoreCase("Other3"))
			str="Other";
		if(str.equalsIgnoreCase("A is a B")||str.equalsIgnoreCase("B is a A"))
			str="Hypo";
		setStringValue(col, row,str);
		return str;
	}
	public String modifyCategory(String colStr,int row){
		Integer col = srcTitle.get(colStr);
		return modifyCategory(col,row);
	}
	
	
}
