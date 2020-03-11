
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelMain {
    public static void main(String[] args) throws IOException {
        //https://www.cnblogs.com/grasslucky/p/10613308.html
        //获取桌面绝对路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();

        String fileName = desktop+ File.separator+"测试.xls";
        POIFactory poiFactory = new POIFactory(new File(fileName), 1);
        List<List<String>> sheetData = poiFactory.getSheetData();
        for (List<String> sheetDatum : sheetData) {
            System.out.println(sheetDatum);
        }

    }


}
