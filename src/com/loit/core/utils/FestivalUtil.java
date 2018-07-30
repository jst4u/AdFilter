package com.loit.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * JAVA 节假日 判断和配置 实现类Festival.java， 配置文件WEB-INF/节假日.xls
 * 需POI包支持poi-2.5.1-final-20040804.jar 节假日.xls
 * 
 * @author yu
 * 
 */
public class FestivalUtil {
	private final String FILE_NAME = "节假日.xls";
	private List<Date> festival = new ArrayList<Date>();// 节假日
	private List<Date> workDay = new ArrayList<Date>();// 工作日

	public FestivalUtil() {
		File excel = this.getExcel();

		try {

			FileInputStream fin = new FileInputStream(excel);
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(fin);
			HSSFSheet sheet = hssfworkbook.getSheetAt(0);
			int last = sheet.getLastRowNum();
			int index = 1;
			Date dt = null;
			while (index <= last) {
				HSSFRow row = sheet.getRow(index);

				/* 读取法定节假日 */
				HSSFCell cell = row.getCell((short) 0);
				if (cell != null) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						if (dt != null && dt.getTime() > 0) {
							this.festival.add(dt);
						}
					}

				}

				/* 读取特殊工作日 */
				cell = row.getCell((short) 1);

				if (cell != null) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());

						if (dt != null && dt.getTime() > 0) {
							// System.out.println(this.getDate(dt));
							this.workDay.add(dt);
						}
					}

				}

				index++;
			}
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public File getExcel() {
		File excel = null;
		try {
			URL url = FestivalUtil.class.getResource("/");
			url = new URL(url, "../" + FILE_NAME);
			excel = new File(url.getPath());
			return excel;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
	}

	/**
	 * 从EXCEL文件中读取节假日
	 * 
	 * @return
	 */
	public List getFestival() {
		return this.festival;
	}

	public List getSpecialWorkDay() {
		return this.workDay;
	}

	/**
	 * 判断一个日期是否日节假日 法定节假日只判断月份和天，不判断年
	 * 
	 * @param date
	 * @return
	 */
	public boolean isFestival(Date date) {
		boolean festival = false;
		Calendar fcal = Calendar.getInstance();
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		List<Date> list = this.getFestival();
		for (Date dt : list) {
			fcal.setTime(dt);

			// 法定节假日判断
			if (fcal.get(Calendar.MONTH) == dcal.get(Calendar.MONTH) && fcal.get(Calendar.DATE) == dcal.get(Calendar.DATE)) {
				festival = true;
			}
		}
		return festival;
	}

	/**
	 * 周六周日判断
	 * 
	 * @param date
	 * @return
	 */
	public boolean isWeekend(Date date) {
		boolean weekend = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			weekend = true;
		}
		return weekend;
	}

	/**
	 * 是否是工作日 法定节假日和周末为非工作日
	 * 
	 * @param date
	 * @return
	 */
	public boolean isWorkDay(Date date) {
		boolean workday = true;
		if (this.isFestival(date) || this.isWeekend(date)) {
			workday = false;
		}

		/* 特殊工作日判断 */
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		for (Date dt : this.workDay) {
			cal2.setTime(dt);
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
				// 年月日相等为特殊工作日
				workday = true;
			}

		}
		return workday;
	}

	public Date getDate(String str) {
		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;

	}

	public String getDate(Date date) {
		String dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dt = df.format(date);

		return dt;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FestivalUtil f = new FestivalUtil();
		Date dt = f.getDate("2014-04-12");
		System.out.println(f.isWorkDay(dt));
	}
}
