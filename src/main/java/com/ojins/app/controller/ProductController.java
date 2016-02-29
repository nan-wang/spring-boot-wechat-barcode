package com.ojins.app.controller;

import com.ojins.app.model.Product;
import com.ojins.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nwang on 25/02/16.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

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
}
