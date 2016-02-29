package com.ojins.app.repository;

import com.ojins.app.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nwang on 25/02/16.
 */
@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer>{
    Brand findByBrandId(Integer brandId);
}
