package com.ojins.app.controller;

import com.ojins.app.model.Product;
import com.ojins.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by nwang on 25/02/16.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @Valid @NotNull
    @Value("${app.controller.loaddata}")
    private Boolean loadAllData;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> loadData(@RequestParam String cn) throws Exception{
        if (cn.equals("True")) {
            productService.loadAllDataWithCn();
        } else {
            productService.loadAllData();
        }
        logger.info("load all the data into database.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{barCode}")
    public ResponseEntity<Product> findProductByBarcode(@PathVariable String barCode) {
        Product product = productService.findProductByBarcode(barCode);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostConstruct
    public void loadDataFromCSV() {
        if (loadAllData) {
            productService.loadAllDataWithCn();
            logger.info("load all the data into database.");
        } else {
            logger.warn("application.properties.app.controller.loadAllData is set to False");
        }
    }
}
