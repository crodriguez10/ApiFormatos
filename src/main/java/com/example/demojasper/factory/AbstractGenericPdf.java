/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.factory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demojasper.util.Constantes;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.MediaType;

/**
 *
 * @author cesar.rodriguez
 */
public abstract class AbstractGenericPdf{
   
    public String key;
    
    public byte[] generarPDF(HttpServletResponse response, AmazonS3 s3client, String bucket, Map<String, Object> map) throws Exception {

        byte[] returnObject;

        //get file from S3
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucket, key));
        InputStream reporteJasper = s3Object.getObjectContent();
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, map, new JREmptyDataSource());
        returnObject = JasperExportManager.exportReportToPdf(jasperPrint);

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "filename=archivo.pdf");
        response.setHeader("Correcto", "OK");
        response.setHeader("Formato", Constantes.FORMATO_TEST);
        response.setStatus(200);

        return returnObject;

    }
    
}
