/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.factory.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demojasper.factory.AbstractGenericPdf;
import com.example.demojasper.factory.FactoryFormat;
import com.example.demojasper.model.DataWrapperGeneric;
import com.example.demojasper.model.KeyValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Rodriguez
 */
@Component
@Qualifier("keyvalue")
public class FactoryFormatGenericImpl extends AbstractGenericPdf implements FactoryFormat<DataWrapperGeneric>{

    private Map<String, Object> generarMap(List<KeyValue> listKeyValue) {
        Map<String, Object> parameters = new HashMap<>();
        for (KeyValue keyValue : listKeyValue) {
            parameters.put(keyValue.getKey(), keyValue.getValue());
        }
        return parameters;
    }

    @Override
    public byte[] createFormat(HttpServletResponse response, AmazonS3 s3client, String bucket, DataWrapperGeneric data) {
        this.setKey(data.getKeyResource());
        return generarPDF(response, s3client, bucket, generarMap(data.getListKeyValue()));
    }
    
}
