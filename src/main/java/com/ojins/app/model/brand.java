package com.ojins.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by nwang on 21/02/16.
 */
@Entity
public class brand {
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

    public String getGetBrandDescriptionCN() {
        return getBrandDescriptionCN;
    }

    public void setGetBrandDescriptionCN(String getBrandDescriptionCN) {
        this.getBrandDescriptionCN = getBrandDescriptionCN;
    }

    @Id
    private Integer brandId;
    private String brandName;
    private String brandDescription;
    private String brandNameCN;
    private String getBrandDescriptionCN;

    public brand() {}

    public brand(Integer brandId, String brandName, String brandDescription,
                 String brandNameCN, String getBrandDescriptionCN) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandNameCN = brandNameCN;
        this.getBrandDescriptionCN = getBrandDescriptionCN;
    }

    public brand(Integer brandId, String brandName, String brandDescription) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
    }
}
