package com.example.skyper.musica.BD.modelo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Skyper on 23/04/2017.
 */

public class Voto
{
    int idVoto;
    int negativos;
    int positivos;
    int nReproducciones;
    int ID_Cancion;
    int ID_Evento;

    public Voto(int idVoto, int negativos, int positivos, int nReproducciones, int ID_Cancion, int ID_Evento)
    {
        this.idVoto = idVoto;
        this.negativos = negativos;
        this.positivos = positivos;
        this.nReproducciones = nReproducciones;
        this.ID_Cancion = ID_Cancion;
        this.ID_Evento = ID_Evento;
    }

    public int getIdVoto()
    {
        return idVoto;
    }

    public void setIdVoto(int idVoto)
    {
        this.idVoto = idVoto;
    }

    public int getNegativos()
    {
        return negativos;
    }

    public void setNegativos(int negativos)
    {
        this.negativos = negativos;
    }

    public int getPositivos()
    {
        return positivos;
    }

    public void setPositivos(int positivos)
    {
        this.positivos = positivos;
    }

    public int getnReproducciones()
    {
        return nReproducciones;
    }

    public void setnReproducciones(int nReproducciones)
    {
        this.nReproducciones = nReproducciones;
    }

    public int getID_Cancion()
    {
        return ID_Cancion;
    }

    public void setID_Cancion(int ID_Cancion)
    {
        this.ID_Cancion = ID_Cancion;
    }

    public int getID_Evento()
    {
        return ID_Evento;
    }

    public void setID_Evento(int ID_Evento)
    {
        this.ID_Evento = ID_Evento;
    }
}
