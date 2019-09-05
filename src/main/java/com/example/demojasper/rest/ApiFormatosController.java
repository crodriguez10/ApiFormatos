/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.rest;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demojasper.factory.FactoryFormat;
import com.example.demojasper.model.DataWrapper;
import com.example.demojasper.model.DataWrapperGeneric;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Qualifier("model")
    private FactoryFormat factoryFormat;
    @Autowired
    @Qualifier("keyvalue")
    private FactoryFormat factoryFormatGeneric;

    /**
     * Metodo para generar un PDF usando un archivo de JasperReports y un modelo
     * de data
     *
     * @param dataWrapper
     * @param response
     * @param request
     * @return
     */
    @PostMapping(value = "/getPDF", produces = "application/pdf")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity getPDF(@RequestBody DataWrapper dataWrapper, HttpServletResponse response, HttpServletRequest request) {
        try {
            byte[] responseData = factoryFormat.createFormat(response, s3client, bucket, dataWrapper);
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            Logger.getLogger(ApiFormatosController.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping(value = "/getPDFGeneric", produces = "application/pdf")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity getPDFGeneric(@RequestBody DataWrapperGeneric dataWrapperGeneric, HttpServletResponse response, HttpServletRequest request) {
        try {
            byte[] responseData = factoryFormatGeneric.createFormat(response, s3client, bucket, dataWrapperGeneric);
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            Logger.getLogger(ApiFormatosController.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.badRequest().build();
        }

    }

}
