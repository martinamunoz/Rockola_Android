package com.example.skyper.musica.BD.modelo;

import org.json.JSONException;
import org.json.JSONObject;


public class Cancion
{
    public int idCancion;
    public String nombre;

    public Cancion(int idCancion, String nombre)
    {
        this.idCancion = idCancion;
        this.nombre = nombre;
    }

    public int getIdCancion()
    {
        return idCancion;
    }

    public void setIdCancion(int idCancion)
    {
        this.idCancion = idCancion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String toJSON()
    {
        JSONObject jsonObject= new JSONObject();
        try
        {
            jsonObject.put("idCancion", getIdCancion());
            jsonObject.put("nombre", getNombre());
            return jsonObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
