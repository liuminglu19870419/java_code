import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Mainfun {

	/**
	 * ȥ���ַ����ұߵĿո�
	 * 
	 * @param str
	 *            Ҫ������ַ���
	 * @return �������ַ���
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

	public List<String[]> getData(String file, int ignoreRows)
			throws IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// ��HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// ��һ��Ϊ���⣬��ȡ
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// ע�⣺һ��Ҫ��������������ܻ��������
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
								value = String.valueOf(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// ����ʱ���Ϊ��ʽ���ɵ���������ֵ
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		return result;
	}

	void writeLine(String[] dataLine, int index, HSSFSheet sheet) {

		try {

			// ������i��λ�ô�����
			HSSFRow row = sheet.createRow((short) index);
			for (int i = 0; i < dataLine.length; i++) {
				// ������0��λ�ô�����Ԫ��
				HSSFCell cell = row.createCell((short) i);
				// ���嵥Ԫ��Ϊ�ַ�������
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// �ڵ�Ԫ��������һЩ����
				// cell.setCellValue(new String(dataLine[i].getBytes(),
				// Charset.forName("GBK")));
				cell.setCellValue(dataLine[i]);
				// System.out.println(dataLine[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeData(String file, List<String[]> data) throws IOException {
		int index = 0;

		// �����µ�Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ��Excel�������н�һ����������Ϊȱʡֵ
		HSSFSheet sheet = workbook.createSheet();
		for (String[] strings : data) {
			writeLine(strings, index, sheet);
			index++;
		}

		FileOutputStream fOut = new FileOutputStream(file);
		// ����Ӧ��Excel ����������
		workbook.write(fOut);
		fOut.flush();
		// �����������ر��ļ�
		fOut.close();
	}

	String[] contact(String[] a, String[] b) {
		String[] c = new String[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	public List<String[]> match(List<String[]> in1, List<String[]> in2) {
		ArrayList<String[]> resultArrayList = new ArrayList<String[]>();
		int length1 = 0, length2 = 0;
		Iterator<String[]> iterator1 = in1.iterator();
		while (iterator1.hasNext()) {

			String[] result1 = iterator1.next();
			if (result1.length > length1)
				length1 = result1.length;
			Iterator<String[]> iterator2 = in2.iterator();
			while (iterator2.hasNext()) {
				String[] result2 = iterator2.next();
				if (result2.length > length2) {
					length2 = result2.length;
				}
				if (result1[6].equals(result2[8])) {
					length1 = result1.length;
					length2 = result2.length;
					iterator1.remove();
					iterator2.remove();
					resultArrayList.add(contact(result1, result2));
					break;
				}
			}
		}

		String[] strings1 = new String[length1];
		String[] strings2 = new String[length2];
		for (int i = 0; i < strings1.length; i++) {
			strings1[i] = "";
		}
		for (int i = 0; i < strings2.length; i++) {
			strings2[i] = "";
		}
		for (String[] strings : in1) {
			resultArrayList.add(contact(strings, strings2));
		}
		for (String[] strings : in2) {
			resultArrayList.add(contact(strings1, strings));
		}

		return resultArrayList;
	}

	public static void main(String[] args) throws IOException {
		Mainfun mainfun = new Mainfun();
		List<String[]> result1 = mainfun.getData("one.xls", 1);
		List<String[]> result2 = mainfun.getData("two.xls", 1);
		System.out.println("read finish");
		List<String[]> result = mainfun.match(result1, result2);
		mainfun.writeData("temp.xls", result);
		System.out.println("write finish");
	}
}
