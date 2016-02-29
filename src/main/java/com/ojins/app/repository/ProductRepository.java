package com.ojins.app.repository;

import com.ojins.app.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nwang on 25/02/16.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String>{
    Product findByBarCode(String barCode);
}
