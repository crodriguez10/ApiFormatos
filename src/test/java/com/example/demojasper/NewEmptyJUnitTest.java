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
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import com.example.demojasper.factory.FactoryFormat;
import com.example.demojasper.factory.impl.FactoryFormatGenericImpl;
import com.example.demojasper.model.DataWrapperGeneric;
import com.example.demojasper.model.KeyValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar.rodriguez
 */
public class NewEmptyJUnitTest extends Mockito{
    
    DataWrapper dataWrapper;
    HttpServletResponse response;
    private static final String bucket = "cesar.bucket";
    BasicAWSCredentials awsCreds = new BasicAWSCredentials("acceskey", "secretkey");
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
            
            FactoryFormat<DataWrapper> factory = new FactoryFormatImpl();
            byte[] responseData = factory.createFormat(response, s3client, bucket, dataWrapper);
           
            assertTrue("File created", responseData.length > 0);
        } catch (Exception ex) {
            assertTrue("Error exception", false);
            System.out.println("Error generatePdfFactory: "+ex);
        }
    }
    
    @Test
    public void generatePdfCompany(){
        dataWrapper.setDataObjeto("{\"razonSocial\":\"Pragma\",\"direccion\":\"Avenida pasoancho carrera 80 local 401\",\"numeroEmpleados\":\"27\",\"nit\":\"8951222\"}");
        dataWrapper.setIdClase("com.example.demojasper.adapter.imp.CompanyMapAdapterImpl");
        
        FactoryFormat<DataWrapper> factory = new FactoryFormatImpl();
        byte[] responseData = factory.createFormat(response, s3client, bucket, dataWrapper);
        
        assertTrue("File created", responseData.length > 0);
    }
    
    @Test
    public void generatePdfGeneric(){
        DataWrapperGeneric dataWrapperGeneric = new DataWrapperGeneric();
        dataWrapperGeneric.setKeyResource("test.jasper");
        List<KeyValue> listKeyValue = new ArrayList<>();
        listKeyValue.add(new KeyValue("nombre", "lionel"));
        listKeyValue.add(new KeyValue("apellido", "messi"));
        listKeyValue.add(new KeyValue("edad", "31"));
        listKeyValue.add(new KeyValue("cargo", "delantero"));
        listKeyValue.add(new KeyValue("profesion", "futbolista"));
        dataWrapperGeneric.setListKeyValue(listKeyValue);
        
        FactoryFormat<DataWrapperGeneric> factory = new FactoryFormatGenericImpl();
        byte[] responseData = factory.createFormat(response, s3client, bucket, dataWrapperGeneric);
        System.out.println("size generic: "+responseData.length);
        assertTrue("File created", responseData.length > 0);
    }
    
}
