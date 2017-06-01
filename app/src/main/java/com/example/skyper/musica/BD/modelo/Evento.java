package com.example.skyper.musica.BD.modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Skyper on 23/04/2017.
 */

public class Evento
{
    int idEvento;
    String nombre;
    String inicio;
    String fin;

    public Evento(int idEvento, String nombre, String inicio, String fin)
    {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getIdEvento()
    {
        return idEvento;
    }

    public void setIdEvento(int idEvento)
    {
        this.idEvento = idEvento;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getInicio()
    {
        return inicio;
    }

    public void setInicio(String inicio)
    {
        this.inicio = inicio;
    }

    public String getFin()
    {
        return fin;
    }

    public void setFin(String fin)
    {
        this.fin = fin;
    }
}
