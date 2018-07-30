package com.loit.core.utils.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.loit.core.utils.excel.vo.ColumnView;
import com.loit.core.utils.excel.vo.MyLabel;
import com.loit.core.utils.excel.vo.MySheet;
import com.loit.core.utils.excel.vo.MyWorkBook;
import com.loit.core.utils.excel.vo.RowView;
import com.loit.core.exception.SysException;


public class WriteExcelUtil {
	private WriteExcelUtil(){
	}
	
	public static void write(OutputStream os,MyWorkBook workBook) throws IOException, SysException{
		if(os == null){
			return;
		}
		if(workBook == null){
			throw new NullPointerException("workBook can't be null!" );
		}
		Iterator itor = workBook.iteratorOfSheet();
		if(!itor.hasNext()){
			throw new NullPointerException("The mySheet of workBook can't be null or zero!" );
		}
		WritableWorkbook book = Workbook.createWorkbook(os);
		processSheet(book,workBook);
		book.write();
		try {
			book.close();
		} catch (WriteException e) {
			throw new SysException(e.getMessage());
		}
	}
	
	private static void processSheet(WritableWorkbook book,MyWorkBook myWorkBook) throws SysException{
		for(Iterator<MySheet> itor = myWorkBook.iteratorOfSheet(); itor.hasNext();){
			MySheet mySheet = itor.next();
			WritableSheet sheet = book.createSheet(mySheet.getSheetName(), mySheet.getNum());
			for(Iterator<ColumnView> itor2 = mySheet.iteratorOfColumnViews(); itor2.hasNext() ; ){
				ColumnView cv = itor2.next();
				sheet.setColumnView(cv.getColNum(), cv.getWidth());
			}
			/**
			 * 设置打印方向
			 */
			if(MySheet.PRINT_FORWARD_ALIGN == mySheet.getPrintForward()){
				SheetSettings sheetSettings = sheet.getSettings();
				sheetSettings.setOrientation(PageOrientation.LANDSCAPE);
			}else if(MySheet.PRINT_FORWARD_VALIAN == mySheet.getPrintForward()){
				SheetSettings sheetSettings = sheet.getSettings();
				sheetSettings.setOrientation(PageOrientation.PORTRAIT);
			}else{
				//什么都不做，默认的。
			}
			
			/**
			 * 设置打印边距
			 */
			SheetSettings sheetSettings = sheet.getSettings();
			sheetSettings.setTopMargin(mySheet.getTopMargin());
			sheetSettings.setBottomMargin(mySheet.getBottomMargin());
			sheetSettings.setLeftMargin(mySheet.getLeftMargin());
			sheetSettings.setRightMargin(mySheet.getRightMargin());
			/**
			 * 设置缩放
			 */
			sheetSettings.setScaleFactor(mySheet.getScaleFactor());
			for(Iterator<RowView> itor2 = mySheet.iteratorOfRowViews(); itor2.hasNext() ; ){
				RowView rv = itor2.next();
				try {
					sheet.setRowView(rv.getRowNum(), rv.getHeight());
				} catch (RowsExceededException e) {
					e.printStackTrace();
				}
			}
			/**
			 * 打印居中
			 */
			sheetSettings.setHorizontalCentre(true);
			writeInSheet(sheet,mySheet);
		}
	}
	
	private static void writeInSheet(WritableSheet sheet,MySheet mySheet) throws SysException{
		try {
		for(Iterator<MyLabel> itor = mySheet.iteratorOfMySheet(); itor.hasNext();){
			MyLabel myLabel = itor.next();
			
			WritableFont font3 = null;
			/**
			 * 设置字体
			 */
			if(myLabel.getIsBold()){
				font3 = new WritableFont(WritableFont.createFont(myLabel.getFontName()),myLabel.getFontNum(),WritableFont.BOLD ); 
			}else{
				font3 = new WritableFont(WritableFont.createFont(myLabel.getFontName()),myLabel.getFontNum(),WritableFont.NO_BOLD );
			}
			
			/**
			 *  WritableFont wfc = new WritableFont

      (WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);

			 */
			WritableCellFormat format= new WritableCellFormat(font3); 
			/**设置边框
			 * 
			 */
			if(myLabel.isBorder()){
				format.setBorder(Border.ALL, BorderLineStyle.THIN); 
			}
			
			/**
			 * 设置水平对齐
			 */
			if(MyLabel.LEFT == myLabel.getAlign()){
				format.setAlignment(Alignment.LEFT); 
			}else if(MyLabel.RIGHT == myLabel.getAlign()){
				format.setAlignment(Alignment.RIGHT); 
			}else{
				format.setAlignment(Alignment.CENTRE); 
			}
			
			format.setWrap(myLabel.isAutoNewLine());//设置自动换行
			format.setVerticalAlignment(VerticalAlignment.CENTRE);//设置垂直对齐
//			format.setShrinkToFit(true);
			
			Label label = new Label(myLabel.getY()-1, myLabel.getX()-1, myLabel.getText(), format);
			sheet.addCell(label);
			if(myLabel.getColspan() > 1 || myLabel.getRowspan() > 1 ){
				sheet.mergeCells(myLabel.getY()-1, myLabel.getX()-1, myLabel.getY() + myLabel.getColspan() -2, myLabel.getX() + myLabel.getRowspan() -2);
			}
		}
		} catch (RowsExceededException e) {
			throw new SysException(e.getMessage());
		} catch (WriteException e) {
			throw new SysException(e.getMessage());
		}
		
	}
}
