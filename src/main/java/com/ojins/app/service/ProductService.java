package com.ojins.app.service;

import com.ojins.app.model.Product;

/**
 * Created by nwang on 25/02/16.
 */
public interface ProductService {
    void loadAllData();
    void loadAllDataWithCn();
    Product findProductByBarcode(String barCode);
}
