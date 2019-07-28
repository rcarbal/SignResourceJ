package utils;

import org.apache.poi.ss.usermodel.*;
import pojo.SRItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelFileReader {
    public static ArrayList<SRItem> getPurchasingItems(String fileLocation) throws IOException {

        ArrayList<SRItem> purchaseMaterials = new ArrayList<>();
        File file = new File(fileLocation);


        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();

        for (Row row : sheet){

            SRItem item = new SRItem();

            for (Cell cell: row){

                if (cell.getRowIndex() == 0){
                    continue;
                }
                else {
                    int columnIndex = cell.getColumnIndex();
                    String cellValue = dataFormatter.formatCellValue(cell);

                    switch (columnIndex){

                        case 3:
                            if (cellValue == null)break;
                            item.setItemNumberString(cellValue);
                    }

                }

            }
            if (row.getRowNum() != 0){
                purchaseMaterials.add(item);
                System.out.println("Purchasable items parsed: " + purchaseMaterials.size());
            }


        }
        return purchaseMaterials;
    }
}
