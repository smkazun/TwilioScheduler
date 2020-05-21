/**
 * @author Sebastian Kazun
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Parses a given file dependent on the file type
 */
public class DataFileParser {

    private File inputFile;

    public DataFileParser(String fileName)
    {
        this.inputFile = new File(fileName);
    }


    public Set<PersonInfo> parseExcelFile()
    {
        Set<PersonInfo> persons = new HashSet<PersonInfo>();
        PersonInfo person;
        FileInputStream file = null;

        try
        {
            file = new FileInputStream(inputFile);

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            int j = 0;
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                person = new PersonInfo();
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int i = 0;
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                person.setDOB(cell.getDateCellValue());
                            } else {

                                if(i == 2)
                                {
                                    person.setHomePhone(new BigDecimal(cell.getNumericCellValue()).toPlainString());
                                }
                                else if(i == 3)
                                {
                                    person.setMobilePhone(new BigDecimal(cell.getNumericCellValue()).toPlainString());
                                }
                                else if(i == 7)
                                {
                                    person.setZip((int)cell.getNumericCellValue());
                                }

                            }
                            break;
                        case STRING:

                            if(i == 0)
                            {
                                person.setFirstName(cell.getStringCellValue());
                            }
                            else if(i == 1)
                            {
                                person.setLastName(cell.getStringCellValue());
                            }
                            else if(i == 4)
                            {
                                person.setStreetAddress(cell.getStringCellValue());
                            }
                            else if(i == 5)
                            {
                                person.setCity(cell.getStringCellValue());
                            }
                            else if(i == 6)
                            {
                                person.setState(cell.getStringCellValue());
                            }
                            break;
                    }
                    ++i;
                }
                if(j != 0 ) //skips 1st row
                {
                    persons.add(person);
                }
                j++;
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return persons;
    }
}