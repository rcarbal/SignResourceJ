package utils;

import org.apache.poi.ss.usermodel.*;
import pojo.SRItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelFileReader {
    public static ArrayList<SRItem> getPurchasingItems(String fileLocation) throws IOException {

        ArrayList<SRItem> poArray = new ArrayList<>();
        File file = new File(fileLocation);


        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();


        return null;
    }
}
