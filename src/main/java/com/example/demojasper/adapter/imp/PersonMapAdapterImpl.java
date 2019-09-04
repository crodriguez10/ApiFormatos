/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper.adapter.imp;

import com.example.demojasper.adapter.DataMapAdapter;
import com.example.demojasper.model.Person;
import com.example.demojasper.util.Parametros;
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
        parameters.put(Parametros.NOMBRE_VALUE, this.getNombre());
        parameters.put(Parametros.APELLIDO_VALUE, this.getApellido());
        parameters.put(Parametros.EDAD_VALUE, this.getEdad());
        parameters.put(Parametros.CARGO_VALUE, this.getCargo());
        parameters.put(Parametros.PROFESION_VALUE, this.getProfesion());

        return parameters;
    }

    @Override
    public String getkeyResource() {
        return ResourcePath.KEY_TEST;
    }
    
}
