
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelMain {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        //https://www.cnblogs.com/grasslucky/p/10613308.html
        //获取桌面绝对路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();

        String fileName = desktop+ File.separator+"测试.xlsx";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));

        XSSFWorkbook workbook = new XSSFWorkbook(bis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<List<String>> sheetData = getSheetData(sheet);
        for (List<String> sheetDatum : sheetData) {
            System.out.println(sheetDatum);
        }
    }

    public static List<List<String>> getSheetData(Sheet sheet){
        List<List<String>> list = new ArrayList<List<String>>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            List<String> rowData = getRowData(sheet.getRow(i));
            list.add(rowData);
        }
        return list;
    }
    //传入一行对象，读取每个单元格的数据存入数组，并返回该数组
    public static List<String> getRowData(Row row){
        int lastCellNum = row.getLastCellNum();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            CellType cellTypeEnum = cell.getCellTypeEnum();
            if (cellTypeEnum == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    list.add(new SimpleDateFormat("yyy-MM-dd").format(cell.getDateCellValue()));
                }else{
                    list.add(String.valueOf(cell.getNumericCellValue()));
                }
            }else if(cellTypeEnum == CellType.STRING){
                list.add(cell.getStringCellValue());
            }else if(cellTypeEnum == CellType.BOOLEAN){
                list.add(String.valueOf(cell.getBooleanCellValue()));
            }else if(cellTypeEnum == CellType.ERROR){
                list.add("错误类型");
            }else if(cellTypeEnum == CellType._NONE){
                list.add("");
            }else {
                list.add("");
            }
        }
        return list;
    }
}
