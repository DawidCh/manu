/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuliczek.model;

import java.io.File;

/**
 *
 * @author kalosh
 */
public class InputDataHolder {
    private File inputFile;
    private String sheetName;
    private String shopIdColumn;
    private String percentageColumn;
    private String companiesName;
    private String period;

    public InputDataHolder(File inputFile, String sheetName, String shopIdColumn, String percentageColumn, String companiesName, String period) {
        this.inputFile = inputFile;
        this.sheetName = sheetName;
        this.shopIdColumn = shopIdColumn;
        this.percentageColumn = percentageColumn;
        this.companiesName = companiesName;
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setCompaniesName(String companiesName) {
        this.companiesName = companiesName;
    }

    public void setInputFile(File outputFile) {
        this.inputFile = outputFile;
    }

    public void setPercentageColumn(String percentageColumn) {
        this.percentageColumn = percentageColumn;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setShopIdColumn(String shopIdColumn) {
        this.shopIdColumn = shopIdColumn;
    }

    public String getCompaniesName() {
        return companiesName;
    }

    public File getInputFile() {
        return inputFile;
    }

    public String getPercentageColumn() {
        return percentageColumn;
    }

    public String getSheetName() {
        return sheetName;
    }

    public String getShopIdColumn() {
        return shopIdColumn;
    }
}
