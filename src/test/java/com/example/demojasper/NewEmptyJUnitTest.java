/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.demojasper.factory.impl.FactoryFormatImpl;
import com.example.demojasper.model.DataWrapper;
import com.example.demojasper.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.demojasper.factory.FactoryFormat;

/**
 *
 * @author cesar.rodriguez
 */
public class NewEmptyJUnitTest extends Mockito{
    
    DataWrapper dataWrapper;
    HttpServletResponse response;
    private static final String bucket = "cesar.bucket";
    BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIATLFGFVK7A36IP353", "raK6u+kfisG0PH1cdTddvRvrYVb3+s4IgirYBmPZ");
    final AmazonS3 s3client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();
    
    
    public NewEmptyJUnitTest() {
    }
    
    @Before
    public void setUp() {
        System.out.println("initi data");
        dataWrapper = new DataWrapper();
        response = mock(HttpServletResponse.class);
        
        
    }
    
    @Test
    public void generatePdfFactoryPerson() throws IOException{
        try {
            dataWrapper.setDataObjeto("{\"nombre\":\"Cesar\",\"apellido\":\"Rodriguez\",\"edad\":\"27\",\"cargo\":\"Desarrollador\",\"profesion\":\"Ingeniero en sistemas\"}");
            dataWrapper.setIdClase("com.example.demojasper.adapter.imp.PersonMapAdapterImpl");
            
            FactoryFormat factory = new FactoryFormatImpl();
            byte[] responseData = factory.createFormat(response, s3client, bucket, dataWrapper);
           
            assertTrue("File created", responseData.length > 0);
        } catch (Exception ex) {
            assertTrue("Error exception", false);
            System.out.println("Error generatePdfFactory: "+ex);
        }
    }
    
    @Test
    public void generaTePdfCompany(){
        dataWrapper.setDataObjeto("{\"razonSocial\":\"Pragma\",\"direccion\":\"Avenida pasoancho carrera 80 local 401\",\"numeroEmpleados\":\"27\",\"nit\":\"8951222\"}");
        dataWrapper.setIdClase("com.example.demojasper.adapter.imp.CompanyMapAdapterImpl");
        
        FactoryFormat factory = new FactoryFormatImpl();
        byte[] responseData = factory.createFormat(response, s3client, bucket, dataWrapper);
        
        assertTrue("File created", responseData.length > 0);
    }
    
}
