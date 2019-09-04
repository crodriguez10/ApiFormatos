/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.rest;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demojasper.factory.FactoryFormat;
import com.example.demojasper.model.DataWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cesar.rodriguez
 */
@RestController
@RequestMapping("/formatos")
public class ApiFormatosController {

    @Autowired
    private AmazonS3 s3client;
    @Value("${amazon.aws.bucketName}")
    private String bucket;
    @Autowired
    private FactoryFormat factoryFormat;
    /**
     * Metodo para generar un PDF usando un archivo de JasperReports y un modelo
     * de data
     *
     * @param dataWrapper
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPDF", produces = "application/pdf")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<?> getPDF(@RequestBody DataWrapper dataWrapper, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            
            byte[] responseData = factoryFormat.createFormat(response, s3client, bucket, dataWrapper);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw e;
        }
    }

}
