/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.adapter.imp;

import com.example.demojasper.adapter.DataMapAdapter;
import com.example.demojasper.model.Person;
import com.example.demojasper.util.ResourcePath;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cesar.rodriguez
 */
public class PersonMapAdapterImpl extends Person implements DataMapAdapter{
    
    @Override
    public Map<String, Object> generarDataMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombre", this.getNombre());
        parameters.put("apellido", this.getApellido());
        parameters.put("edad", this.getEdad());
        parameters.put("cargo", this.getCargo());
        parameters.put("profesion", this.getProfesion());

        return parameters;
    }

    @Override
    public String getkeyResource() {
        return ResourcePath.KEY_TEST;
    }
    
}
