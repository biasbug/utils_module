import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class POIFactory {
    private File file;
    private int sheetNum;
    private BufferedInputStream bis;
    private Workbook workbook;
    private Sheet sheet;

    public POIFactory(File file, int sheetNum) {
        this.file = file;
        this.sheetNum = sheetNum;
        init();
    }

    //初始化参数
    private void init(){
        try {
            this.bis = new BufferedInputStream(new FileInputStream(this.file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fileName = this.file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        try {
            if(".xls".equals(suffix)){
                workbook = new HSSFWorkbook(bis);
            }else if(".xlsx".equals(suffix)){
                workbook = new XSSFWorkbook(bis);
            }
            sheet = workbook.getSheetAt(sheetNum);
        }catch (IOException e){
            e.printStackTrace();;
        }
    }

    //获取表格数据
    public  List<List<String>> getSheetData(){
        List<List<String>> list = new ArrayList<List<String>>();
        int lastRowNum = this.sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            List<String> rowData = getRowData(this.sheet.getRow(i));
            list.add(rowData);
        }
        return list;
    }

    //传入一行对象，读取每个单元格的数据存入数组，并返回该数组
    private  List<String> getRowData(Row row){
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
