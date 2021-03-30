package Excel;

import Data.Data;
import Data.MainInformation;
import WorkingFiles.TextMethods;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelProcessor {

    private LinkedHashSet<String> firstSetRows = new LinkedHashSet<>(); //хранятся уникальными подписи к столбцам в порядке добавления
    private XSSFWorkbook workbook = new XSSFWorkbook(); // создание самого excel файла в памяти
    private XSSFSheet sheet = workbook.createSheet("Просто лист"); // создание листа с названием "Просто лист"
    private ArrayList<Data> dataCluster = new ArrayList<Data>(); //Cluster объектов Data
    private Data data;
    private MainInformation mainInformation;
    /**
     * Запуск работы парсера
     */

    public void Start() {
        // заполняем список какими-то данными
        List<Data> dataList;
        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        //Data data = new CreateObjects().getData(new TextMethods().getIdOutOfTxtFile().get(0).toString());

        MainInformation mainInformation;


        /**
         * Получаем название характеристики у всех товаров и записываем их в FirstSetRows
         */
        firstSetRows.add("ID");
        firstSetRows.add("Name");
        ArrayList listId = new TextMethods().getIdOutOfTxtFile(); //Лист всех ID товаров

        for (int i = 0; i < listId.size(); i++) {

            data = new CreateObjects().getData(listId.get(i).toString());

            if (data.getId() == null) {
                dataCluster.add(i, new Data());
                continue;
            }
            dataCluster.add(i, data); //добавление объекта data в ArrayList
            System.out.println(data.getId());
            for (int spec = 0; spec < data.getSpecifications().size(); spec++) {
                for (int sub = 0; sub < data.getSpecifications().get(spec).getSubspecs().size(); sub++) {
                    firstSetRows.add(data.getSpecifications().get(spec).getSubspecs().get(sub).getName());
                }
            }
        }


        /**создаем подписи к столбцам (это будет первая строчка в листе Excel файла)*/

        int count = 0;
        Iterator<String> iterator = firstSetRows.iterator();

        while (iterator.hasNext()) {
            row.createCell(count++).setCellValue(iterator.next());
        }


        /**
         * Загружаем характеристики в таблицу для каждого ID (Товара)
         */

        ArrayList id = new TextMethods().getIdOutOfTxtFile();
        for (int i = 0; i < dataCluster.size(); i++) {

            data = dataCluster.get(i);

            mainInformation = new CreateObjects().getMainInformation(id.get(i).toString());
            rowNum++;
            createSheetHeader(sheet, rowNum, data, mainInformation, firstSetRows);

            /**
             * Вывод загрузки на консоль
             */

            String out = String.format("%.1f", (double) (1 + i) / dataCluster.size() * 100);
            System.out.println("Загрузка: " + out + "%");

        }


        /**
         * Записываем созданный в памяти Excel документ в файл
         */
        try (FileOutputStream out = new FileOutputStream(new File(".\\datafiles\\example.xlsx"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
    }


    //заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private void createSheetHeader(XSSFSheet sheet, int rowNum, Data data, MainInformation infoItem, LinkedHashSet firstSetRows) {
        Row row = sheet.createRow(rowNum);
        int count = 0;


        Iterator<String> iterator = firstSetRows.iterator();

        while (iterator.hasNext()) {


            if (count == 0) row.createCell(count).setCellValue(data.getId());
            if (count == 1) row.createCell(count).setCellValue(infoItem.getName());
            String text = iterator.next();

            if (data.getId() == null) {
                continue;
            }

            for (int i = 0; i < data.getSpecifications().size(); i++) {
                for (int j = 0; j < data.getSpecifications().get(i).getSubspecs().size(); j++) {
                    if (data.getSpecifications().get(i).getSubspecs().get(j).getName().equals(text)) {
                        row.createCell(count).setCellValue(data.getSpecifications().get(i).getSubspecs().get(j).getValue());

                    }

                }

            }
            count++;

        }

    }
}


/**
 * *** Метод для создания подписей к столбцам (первая строчка в Excel файле ***
 */



