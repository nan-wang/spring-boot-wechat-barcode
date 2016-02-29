package com.ojins.app.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

import com.ojins.app.model.Product;
import com.ojins.app.repository.ProductRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nwang on 25/02/16.
 */
@Service
public class ProductServiceBean implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void loadAllData() {
        // load all the data
        CSVParser csvParser = null;
        Path p = Paths.get("data/de_data_cleaned.csv");
        final String[] columName = {"id",
                "mandatoryInfo_0",
                "allergyInfo",
                "analyticalConsituents",
                "companyAddress",
                "notes_0",
                "notes_1",
                "statement",
                "placeOfOrigin",
                "ingredients_0",
                "nutritionalInfo",
                "mandatoryInfo_1",
                "warnings_0",
                "warnings_1",
                "warnings_2",
                "notes_2",
                "registrationNumber",
                "ingredients_1",
                "additives",
                "ingredients_2",
                "barCode",
                "brand",
                "description",
                "img",
                "imgAlt",
                "link",
                "name",
                "price",
                "size"};
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(columName);
        FileReader fileReader = null;
        List<Product> products = null;
        try {
            products = new ArrayList<>();
            fileReader = new FileReader("data/de_data_cleaned.csv");
            csvParser = new CSVParser(fileReader, csvFileFormat);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (int i=1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                Product product = new Product(
                        record.get(columName[1]),
                        record.get(columName[2]),
                        record.get(columName[3]),
                        record.get(columName[4]),
                        record.get(columName[5]),
                        record.get(columName[6]),
                        record.get(columName[7]),
                        record.get(columName[8]),
                        record.get(columName[9]),
                        record.get(columName[10]),
                        record.get(columName[11]),
                        record.get(columName[12]),
                        record.get(columName[13]),
                        record.get(columName[14]),
                        record.get(columName[15]),
                        record.get(columName[16]),
                        record.get(columName[17]),
                        record.get(columName[18]),
                        record.get(columName[19]),
                        record.get(columName[20]),
                        record.get(columName[21]),
                        record.get(columName[22]),
                        record.get(columName[23]),
                        record.get(columName[24]),
                        record.get(columName[25]),
                        record.get(columName[26]),
                        Float.parseFloat(record.get(columName[27])),
                        record.get(columName[28])
                        );
                products.add(product);
            }
        } catch (Exception e) {
                System.out.print("Error in reading the data.csv file");
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (Exception e){
                System.out.print("Error while closing the .csv file.");
                e.printStackTrace();
            }
        }
        if (products != null)  {
            productRepository.save(products);
        }
    }

    @Override
    public void loadAllDataWithCn() {
        // load all the data
        CSVParser csvParser = null;
        final String[] columName = {"id",
                "mandatoryInfo_0",
                "mandatoryInfo_0_cn",
                "allergyInfo",
                "allergyInfo_cn",
                "analyticalConsituents",
                "analyticalConsituents_cn",
                "companyAddress",
                "notes_0",
                "notes_0_cn",
                "notes_1",
                "notes_1_cn",
                "statement",
                "statement_cn",
                "placeOfOrigin",
                "ingredients_0",
                "ingredients_0_cn",
                "nutritionalInfo",
                "nutritionalInfo_cn",
                "mandatoryInfo_1",
                "mandatoryInfo_1_cn",
                "warnings_0",
                "warnings_0_cn",
                "warnings_1",
                "warnings_1_cn",
                "warnings_2",
                "warnings_2_cn",
                "notes_2",
                "notes_2_cn",
                "registrationNumber",
                "ingredients_1",
                "ingredients_1_cn",
                "additives",
                "additives_cn",
                "ingredients_2",
                "ingredients_2_cn",
                "barCode",
                "brand",
                "description",
                "description_cn",
                "img",
                "imgAlt",
                "link",
                "name",
                "price",
                "size"
        };
        CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader(columName);
        FileReader fileReader = null;
        List<Product> products = null;
        try {
            products = new ArrayList<>();
            fileReader = new FileReader("data/de_data_cleaned_with_cn.csv");
            csvParser = new CSVParser(fileReader, csvFileFormat);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (int i=1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                Product product = new Product(
                        record.get(columName[1]),
                        record.get(columName[2]),
                        record.get(columName[3]),
                        record.get(columName[4]),
                        record.get(columName[5]),
                        record.get(columName[6]),
                        record.get(columName[7]),
                        record.get(columName[8]),
                        record.get(columName[9]),
                        record.get(columName[10]),
                        record.get(columName[11]),
                        record.get(columName[12]),
                        record.get(columName[13]),
                        record.get(columName[14]),
                        record.get(columName[15]),
                        record.get(columName[16]),
                        record.get(columName[17]),
                        record.get(columName[18]),
                        record.get(columName[19]),
                        record.get(columName[20]),
                        record.get(columName[21]),
                        record.get(columName[22]),
                        record.get(columName[23]),
                        record.get(columName[24]),
                        record.get(columName[25]),
                        record.get(columName[26]),
                        record.get(columName[27]),
                        record.get(columName[28]),
                        record.get(columName[29]),
                        record.get(columName[30]),
                        record.get(columName[31]),
                        record.get(columName[32]),
                        record.get(columName[33]),
                        record.get(columName[34]),
                        record.get(columName[35]),
                        record.get(columName[36]),
                        record.get(columName[37]),
                        record.get(columName[38]),
                        record.get(columName[39]),
                        record.get(columName[40]),
                        record.get(columName[41]),
                        record.get(columName[42]),
                        record.get(columName[43]),
                        Float.parseFloat(record.get(columName[44])),
                        record.get(columName[45])
                );
                products.add(product);
            }
        } catch (Exception e) {
            System.out.print("Error in reading the data.csv file");
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (Exception e){
                System.out.print("Error while closing the .csv file.");
                e.printStackTrace();
            }
        }
        if (products != null)  {
            productRepository.save(products);
        }

    }

    @Override
    public Product findProductByBarcode(String barCode) {
        return productRepository.findByBarCode(barCode);
    }
}
