package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFinder {
    public static void main(String[] args) {

        String filePath = "E:\\My projects\\Java-Duplicate-Word-Finder\\Duplicate-Word-Finder\\file\\File.xlsx";

        try(Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(filePath)))){
            Sheet sheet = workbook.getSheetAt(0);

            //Create a Hashmap to store word frequencies
            Map<String,Integer> wordFreq = new HashMap<>();

            for (Row row: sheet){
                for (Cell cell: row){
                    if (cell.getCellType() == CellType.STRING){
                        String cellValue = cell.getStringCellValue().toLowerCase().replaceAll("[^\\p{IsSinhala}]","");
                        String[] words = cellValue.split("\\s+");

                        for (String word:words){
                            if (!word.isEmpty()){
                                wordFreq.put(word,wordFreq.getOrDefault(word,0)+1);

                            }
                        }
                    }
                }
            }

            System.out.println("Duplicate Sinhala words in the Dataset");
            for (Map.Entry<String,Integer>entry:wordFreq.entrySet()){
                if (entry.getValue()>1){
                    System.out.println(entry.getKey()+":"+entry.getValue() + "times");
                }
            }



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
