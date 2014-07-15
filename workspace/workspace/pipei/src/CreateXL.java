import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;

public class CreateXL {

	public static String outputFile = "gongye.xls";

	public static void main(String argv[]) {
		try {
			// �����µ�Excel ������
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			// ��Ҫ�½�һ��Ϊ"Ч��ָ��"�Ĺ����������Ϊ��
			// HSSFSheet sheet = workbook.createSheet("Ч��ָ��");
			HSSFSheet sheet = workbook.createSheet();
			// ������0��λ�ô����У���˵��У�
			HSSFRow row = sheet.createRow((short) 0);
			// ������0��λ�ô�����Ԫ�����϶ˣ�
			HSSFCell cell = row.createCell((short) 0);
			// ���嵥Ԫ��Ϊ�ַ�������
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// �ڵ�Ԫ��������һЩ����
			cell.setCellValue("test");		
			cell = row.createCell((short)1);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(100.001);
			row = sheet.createRow((short) 1);
			// ������0��λ�ô�����Ԫ�����϶ˣ�
			cell = row.createCell((short) 0);
			// ���嵥Ԫ��Ϊ�ַ�������
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// �ڵ�Ԫ��������һЩ����
			cell.setCellValue("test1");		
			cell = row.createCell((short)1);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(1002.001); 
			// �½�һ����ļ���
			FileOutputStream fOut = new FileOutputStream(outputFile);
			// ����Ӧ��Excel ����������
			workbook.write(fOut);
			fOut.flush();
			// �����������ر��ļ�
			fOut.close();
			System.out.println("�ļ�����...");

		} catch (Exception e) {
			System.out.println("������ xlCreate() : " + e);
		}
	}
}
