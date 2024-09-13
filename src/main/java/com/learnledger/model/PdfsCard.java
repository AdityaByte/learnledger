/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnledger.model;

/**
 *
 * @author yeshank pawar
 */
public class PdfsCard{
    private String pdfHeading;
    private String pdfSubHeading;

    public String getPdfHeading() {
        return pdfHeading;
    }

    public void setPdfHeading(String pdfHeading) {
        this.pdfHeading = pdfHeading;
    }

    public String getPdfSubHeading() {
        return pdfSubHeading;
    }

    public void setPdfSubHeading(String pdfSubheading) {
        this.pdfSubHeading = pdfSubheading;
    }

    @Override
    public String toString() {
        return "PdfsCard{" + "pdfHeading=" + pdfHeading + ", pdfSubheading=" + pdfSubHeading + '}';
    }
    
    
    
}
