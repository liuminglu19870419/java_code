import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;

public class CreateXL {

	public static String outputFile = "gongye.xls";

	public static void main(String argv[]) {
		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			// 如要新建一名为"效益指标"的工作表，其语句为：
			// HSSFSheet sheet = workbook.createSheet("效益指标");
			HSSFSheet sheet = workbook.createSheet();
			// 在索引0的位置创建行（最顶端的行）
			HSSFRow row = sheet.createRow((short) 0);
			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell((short) 0);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue("test");		
			cell = row.createCell((short)1);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(100.001);
			row = sheet.createRow((short) 1);
			// 在索引0的位置创建单元格（左上端）
			cell = row.createCell((short) 0);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue("test1");		
			cell = row.createCell((short)1);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(1002.001); 
			// 新建一输出文件流
			FileOutputStream fOut = new FileOutputStream(outputFile);
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			System.out.println("文件生成...");

		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}
	}
}
