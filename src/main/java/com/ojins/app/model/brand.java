package com.ojins.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by nwang on 21/02/16.
 */
@Entity
public class Brand {
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandNameCN() {
        return brandNameCN;
    }

    public void setBrandNameCN(String brandNameCN) {
        this.brandNameCN = brandNameCN;
    }

    public String getBrandDescriptionCN() {
        return brandDescriptionCN;
    }

    public void setBrandDescriptionCN(String brandDescriptionCN) {
        this.brandDescriptionCN = brandDescriptionCN;
    }

    @Id
    private Integer brandId;
    private String brandName;
    @Column(columnDefinition="TEXT")
    private String brandDescription;
    private String brandNameCN;
    @Column(columnDefinition="TEXT")
    private String brandDescriptionCN;

    public Brand() {}

    public Brand(Integer brandId, String brandName, String brandDescription,
                 String brandNameCN, String brandDescriptionCN) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandNameCN = brandNameCN;
        this.brandDescriptionCN = brandDescriptionCN;
    }

    public Brand(Integer brandId, String brandName, String brandDescription) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
    }
}
