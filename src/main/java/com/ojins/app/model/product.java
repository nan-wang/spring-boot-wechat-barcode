package com.ojins.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by nwang on 21/02/16.
 */
@Entity
public class Product {
    @Column(columnDefinition="TEXT")
    private String mandatoryInfo_0;
    @Column(columnDefinition="TEXT")
    private String allergyInfo;
    @Column(columnDefinition="TEXT")
    private String analyticalConsituents;
    private String companyAddress;
    @Column(columnDefinition="TEXT")
    private String notes_0;
    @Column(columnDefinition="TEXT")
    private String notes_1;
    @Column(columnDefinition="TEXT")
    private String statement;
    private String placeOfOrigin;
    @Column(columnDefinition="TEXT")
    private String ingredients_0;
    @Column(columnDefinition="TEXT")
    private String nutritionalInfo;
    @Column(columnDefinition="TEXT")
    private String mandatoryInfo_1;
    @Column(columnDefinition="TEXT")
    private String warnings_0;
    @Column(columnDefinition="TEXT")
    private String warnings_1;
    @Column(columnDefinition="TEXT")
    private String warnings_2;
    @Column(columnDefinition="TEXT")
    private String notes_2;
    private String registrationNumber;
    @Column(columnDefinition="TEXT")
    private String ingredients_1;
    @Column(columnDefinition="TEXT")
    private String additives;
    @Column(columnDefinition="TEXT")
    private String ingredients_2;
    @Id
    private String barCode;
    private String brand;
    @Column(columnDefinition="TEXT")
    private String description;
    private String img;
    private String imgAlt;
    private String link;
    private String name;
    private Float price;
    private String size;


    @Column(columnDefinition="TEXT")
    private String mandatoryInfo_0_cn;
    @Column(columnDefinition="TEXT")
    private String allergyInfo_cn;
    @Column(columnDefinition="TEXT")
    private String analyticalConsituents_cn;

    public String getNotes_0_cn() {
        return notes_0_cn;
    }

    public void setNotes_0_cn(String notes_0_cn) {
        this.notes_0_cn = notes_0_cn;
    }

    public String getMandatoryInfo_0_cn() {
        return mandatoryInfo_0_cn;
    }

    public void setMandatoryInfo_0_cn(String mandatoryInfo_0_cn) {
        this.mandatoryInfo_0_cn = mandatoryInfo_0_cn;
    }

    public String getAllergyInfo_cn() {
        return allergyInfo_cn;
    }

    public void setAllergyInfo_cn(String allergyInfo_cn) {
        this.allergyInfo_cn = allergyInfo_cn;
    }

    public String getAnalyticalConsituents_cn() {
        return analyticalConsituents_cn;
    }

    public void setAnalyticalConsituents_cn(String analyticalConsituents_cn) {
        this.analyticalConsituents_cn = analyticalConsituents_cn;
    }

    public String getNotes_1_cn() {
        return notes_1_cn;
    }

    public void setNotes_1_cn(String notes_1_cn) {
        this.notes_1_cn = notes_1_cn;
    }

    public String getStatement_cn() {
        return statement_cn;
    }

    public void setStatement_cn(String statement_cn) {
        this.statement_cn = statement_cn;
    }

    public String getIngredients_0_cn() {
        return ingredients_0_cn;
    }

    public void setIngredients_0_cn(String ingredients_0_cn) {
        this.ingredients_0_cn = ingredients_0_cn;
    }

    public String getNutritionalInfo_cn() {
        return nutritionalInfo_cn;
    }

    public void setNutritionalInfo_cn(String nutritionalInfo_cn) {
        this.nutritionalInfo_cn = nutritionalInfo_cn;
    }

    public String getMandatoryInfo_1_cn() {
        return mandatoryInfo_1_cn;
    }

    public void setMandatoryInfo_1_cn(String mandatoryInfo_1_cn) {
        this.mandatoryInfo_1_cn = mandatoryInfo_1_cn;
    }

    public String getWarnings_0_cn() {
        return warnings_0_cn;
    }

    public void setWarnings_0_cn(String warnings_0_cn) {
        this.warnings_0_cn = warnings_0_cn;
    }

    public String getWarnings_1_cn() {
        return warnings_1_cn;
    }

    public void setWarnings_1_cn(String warnings_1_cn) {
        this.warnings_1_cn = warnings_1_cn;
    }

    public String getWarnings_2_cn() {
        return warnings_2_cn;
    }

    public void setWarnings_2_cn(String warnings_2_cn) {
        this.warnings_2_cn = warnings_2_cn;
    }

    public String getNotes_2_cn() {
        return notes_2_cn;
    }

    public void setNotes_2_cn(String notes_2_cn) {
        this.notes_2_cn = notes_2_cn;
    }

    public String getIngredients_1_cn() {
        return ingredients_1_cn;
    }

    public void setIngredients_1_cn(String ingredients_1_cn) {
        this.ingredients_1_cn = ingredients_1_cn;
    }

    public String getAdditives_cn() {
        return additives_cn;
    }

    public void setAdditives_cn(String additives_cn) {
        this.additives_cn = additives_cn;
    }

    public String getIngredients_2_cn() {
        return ingredients_2_cn;
    }

    public void setIngredients_2_cn(String ingredients_2_cn) {
        this.ingredients_2_cn = ingredients_2_cn;
    }

    public String getDescription_cn() {
        return description_cn;
    }

    public void setDescription_cn(String description_cn) {
        this.description_cn = description_cn;
    }

    @Column(columnDefinition="TEXT")
    private String notes_0_cn;
    @Column(columnDefinition="TEXT")
    private String notes_1_cn;
    @Column(columnDefinition="TEXT")
    private String statement_cn;
    @Column(columnDefinition="TEXT")
    private String ingredients_0_cn;
    @Column(columnDefinition="TEXT")
    private String nutritionalInfo_cn;
    @Column(columnDefinition="TEXT")
    private String mandatoryInfo_1_cn;
    @Column(columnDefinition="TEXT")
    private String warnings_0_cn;
    @Column(columnDefinition="TEXT")
    private String warnings_1_cn;
    @Column(columnDefinition="TEXT")
    private String warnings_2_cn;
    @Column(columnDefinition="TEXT")
    private String notes_2_cn;
    @Column(columnDefinition="TEXT")
    private String ingredients_1_cn;
    @Column(columnDefinition="TEXT")
    private String additives_cn;
    @Column(columnDefinition="TEXT")
    private String ingredients_2_cn;
    @Column(columnDefinition="TEXT")
    private String description_cn;

    public String getMandatoryInfo_0() {
        return mandatoryInfo_0;
    }

    public void setMandatoryInfo_0(String mandatoryInfo_0) {
        this.mandatoryInfo_0 = mandatoryInfo_0;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(String allergyInfo) {
        this.allergyInfo = allergyInfo;
    }

    public String getAnalyticalConsituents() {
        return analyticalConsituents;
    }

    public void setAnalyticalConsituents(String analyticalConsituents) {
        this.analyticalConsituents = analyticalConsituents;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getNotes_0() {
        return notes_0;
    }

    public void setNotes_0(String notes_0) {
        this.notes_0 = notes_0;
    }

    public String getNotes_1() {
        return notes_1;
    }

    public void setNotes_1(String notes_1) {
        this.notes_1 = notes_1;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getIngredients_0() {
        return ingredients_0;
    }

    public void setIngredients_0(String ingredients_0) {
        this.ingredients_0 = ingredients_0;
    }

    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public String getMandatoryInfo_1() {
        return mandatoryInfo_1;
    }

    public void setMandatoryInfo_1(String mandatoryInfo_1) {
        this.mandatoryInfo_1 = mandatoryInfo_1;
    }

    public String getWarnings_0() {
        return warnings_0;
    }

    public void setWarnings_0(String warnings_0) {
        this.warnings_0 = warnings_0;
    }

    public String getWarnings_1() {
        return warnings_1;
    }

    public void setWarnings_1(String warnings_1) {
        this.warnings_1 = warnings_1;
    }

    public String getWarnings_2() {
        return warnings_2;
    }

    public void setWarnings_2(String warnings_2) {
        this.warnings_2 = warnings_2;
    }

    public String getNotes_2() {
        return notes_2;
    }

    public void setNotes_2(String notes_2) {
        this.notes_2 = notes_2;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIngredients_1() {
        return ingredients_1;
    }

    public void setIngredients_1(String ingredients_1) {
        this.ingredients_1 = ingredients_1;
    }

    public String getAdditives() {
        return additives;
    }

    public void setAdditives(String additives) {
        this.additives = additives;
    }

    public String getIngredients_2() {
        return ingredients_2;
    }

    public void setIngredients_2(String ingredients_2) {
        this.ingredients_2 = ingredients_2;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgAlt() {
        return imgAlt;
    }

    public void setImgAlt(String imgAlt) {
        this.imgAlt = imgAlt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Product() {
    }

    public Product(String mandatoryInfo_0, String mandatoryInfo_0_cn,
                   String allergyInfo, String allergyInfo_cn,
                   String analyticalConsituents, String analyticalConsituents_cn,
                   String companyAddress,
                   String notes_0, String notes_0_cn,
                   String notes_1, String notes_1_cn,
                   String statement, String statement_cn,
                   String placeOfOrigin,
                   String ingredients_0, String ingredients_0_cn,
                   String nutritionalInfo, String nutritionalInfo_cn,
                   String mandatoryInfo_1, String mandatoryInfo_1_cn,
                   String warnings_0, String warnings_0_cn,
                   String warnings_1, String warnings_1_cn,
                   String warnings_2, String warnings_2_cn,
                   String notes_2, String notes_2_cn,
                   String registrationNumber,
                   String ingredients_1, String ingredients_1_cn,
                   String additives, String additives_cn,
                   String ingredients_2, String ingredients_2_cn,
                   String barCode, String brand,
                   String description, String description_cn,
                   String img, String imgAlt, String link, String name,
                   Float price, String size) {
        this.mandatoryInfo_0 = mandatoryInfo_0;
        this.allergyInfo = allergyInfo;
        this.analyticalConsituents = analyticalConsituents;
        this.companyAddress = companyAddress;
        this.notes_0 = notes_0;
        this.notes_1 = notes_1;
        this.statement = statement;
        this.placeOfOrigin = placeOfOrigin;
        this.ingredients_0 = ingredients_0;
        this.nutritionalInfo = nutritionalInfo;
        this.mandatoryInfo_1 = mandatoryInfo_1;
        this.warnings_0 = warnings_0;
        this.warnings_1 = warnings_1;
        this.warnings_2 = warnings_2;
        this.notes_2 = notes_2;
        this.registrationNumber = registrationNumber;
        this.ingredients_1 = ingredients_1;
        this.additives = additives;
        this.ingredients_2 = ingredients_2;
        this.barCode = barCode;
        this.brand = brand;
        this.description = description;
        this.img = img;
        this.imgAlt = imgAlt;
        this.link = link;
        this.name = name;
        this.price = price;
        this.size = size;
        this.mandatoryInfo_0_cn = mandatoryInfo_0_cn;
        this.allergyInfo_cn = allergyInfo_cn;
        this.analyticalConsituents_cn = analyticalConsituents_cn;
        this.notes_0_cn = notes_0_cn;
        this.notes_1_cn = notes_1_cn;
        this.statement_cn = statement_cn;
        this.ingredients_0_cn = ingredients_0_cn;
        this.nutritionalInfo_cn = nutritionalInfo_cn;
        this.mandatoryInfo_1_cn = mandatoryInfo_1_cn;
        this.warnings_0_cn = warnings_0_cn;
        this.warnings_1_cn = warnings_1_cn;
        this.warnings_2_cn = warnings_2_cn;
        this.notes_2_cn = notes_2_cn;
        this.ingredients_1_cn = ingredients_1_cn;
        this.additives_cn = additives_cn;
        this.ingredients_2_cn = ingredients_2_cn;
        this.description_cn = description_cn;
    }

    public Product(String mandatoryInfo_0, String allergyInfo, String analyticalConsituents, String companyAddress, String notes_0, String notes_1, String statement, String placeOfOrigin, String ingredients_0, String nutritionalInfo, String mandatoryInfo_1, String warnings_0, String warnings_1, String warnings_2, String notes_2, String registrationNumber, String ingredients_1, String additives, String ingredients_2, String barCode, String brand, String description, String img, String imgAlt, String link, String name, Float price, String size) {
        this.mandatoryInfo_0 = mandatoryInfo_0;
        this.allergyInfo = allergyInfo;
        this.analyticalConsituents = analyticalConsituents;
        this.companyAddress = companyAddress;
        this.notes_0 = notes_0;
        this.notes_1 = notes_1;
        this.statement = statement;
        this.placeOfOrigin = placeOfOrigin;
        this.ingredients_0 = ingredients_0;
        this.nutritionalInfo = nutritionalInfo;
        this.mandatoryInfo_1 = mandatoryInfo_1;
        this.warnings_0 = warnings_0;
        this.warnings_1 = warnings_1;
        this.warnings_2 = warnings_2;
        this.notes_2 = notes_2;
        this.registrationNumber = registrationNumber;
        this.ingredients_1 = ingredients_1;
        this.additives = additives;
        this.ingredients_2 = ingredients_2;
        this.barCode = barCode;
        this.brand = brand;
        this.description = description;
        this.img = img;
        this.imgAlt = imgAlt;
        this.link = link;
        this.name = name;
        this.price = price;
        this.size = size;
    }
}
