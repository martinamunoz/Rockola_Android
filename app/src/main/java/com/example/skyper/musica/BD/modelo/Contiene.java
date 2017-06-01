package com.example.skyper.musica.BD.modelo;

/**
 * Created by Skyper on 23/04/2017.
 */

public class Contiene
{
    public int ID_Cancion;
    public int ID_Playlist;

    public Contiene(int ID_Cancion, int ID_Playlist)
    {
        this.ID_Cancion = ID_Cancion;
        this.ID_Playlist = ID_Playlist;
    }

    public int getID_Cancion()
    {
        return ID_Cancion;
    }

    public void setID_Cancion(int ID_Cancion)
    {
        this.ID_Cancion = ID_Cancion;
    }

    public int getID_Playlist()
    {
        return ID_Playlist;
    }

    public void setID_Playlist(int ID_Playlist)
    {
        this.ID_Playlist = ID_Playlist;
    }
}
