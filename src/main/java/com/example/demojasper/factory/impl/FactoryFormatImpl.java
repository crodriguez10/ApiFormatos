/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.factory.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demojasper.adapter.DataMapAdapter;
import com.example.demojasper.factory.AbstractGenericPdf;
import com.example.demojasper.model.DataWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.example.demojasper.factory.FactoryFormat;

/**
 *
 * @author cesar.rodriguez
 */
@Component
public class FactoryFormatImpl extends AbstractGenericPdf implements FactoryFormat{

    
    @Override
    public byte[] createFormat(HttpServletResponse response, AmazonS3 s3client, String bucket, DataWrapper dataWrapper) {
        
        byte[] responsePdf = null;
        
        try {
            ObjectMapper om = new ObjectMapper();
            
            DataMapAdapter dataMapAdapter = (DataMapAdapter) om.readValue(dataWrapper.getDataObjeto(), Class.forName(dataWrapper.getIdClase()));
            this.key = dataMapAdapter.getkeyResource();
            responsePdf = generarPDF(response, s3client, bucket, dataMapAdapter.generarDataMap());
            
        } catch (Exception ex) {
            System.out.println("Error createFormat: "+ex);
        }
        
        return responsePdf;
    }

    
    
}
