/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.adapter.imp;

import com.example.demojasper.adapter.DataMapAdapter;
import com.example.demojasper.model.Company;
import com.example.demojasper.util.ResourcePath;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cesar.rodriguez
 */
public class CompanyMapAdapterImpl extends Company implements DataMapAdapter{

    @Override
    public Map<String, Object> generarDataMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("razonSocial", this.getRazonSocial());
        parameters.put("direccion", this.getDireccion());
        parameters.put("numeroEmpleados", this.getNumeroEmpleados());
        parameters.put("nit", this.getNit());
        
        return parameters;
    }

    @Override
    public String getkeyResource() {
        return ResourcePath.KEY_COMPANY;
    }
    
}
