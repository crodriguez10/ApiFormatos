/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.model;
import java.util.List;

/**
 *
 * @author Cesar Rodriguez
 */
public class DataWrapperGeneric{
    
    private List<KeyValue> listKeyValue;
    private String keyResource;

    public DataWrapperGeneric(List<KeyValue> listKeyValue, String keyResource) {
        this.listKeyValue = listKeyValue;
        this.keyResource = keyResource;
    }

    public DataWrapperGeneric() {
    }
    
    

    public List<KeyValue> getListKeyValue() {
        return listKeyValue;
    }

    public void setListKeyValue(List<KeyValue> listKeyValue) {
        this.listKeyValue = listKeyValue;
    }

    

    public String getKeyResource() {
        return keyResource;
    }

    public void setKeyResource(String keyResource) {
        this.keyResource = keyResource;
    }
    
}
