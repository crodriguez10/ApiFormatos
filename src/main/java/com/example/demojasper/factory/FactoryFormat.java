/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.factory;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demojasper.model.DataWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar.rodriguez
 */
public interface FactoryFormat<T>{
    
    public byte[] createFormat(HttpServletResponse response, AmazonS3 s3client, String bucket, T data);
    
}
