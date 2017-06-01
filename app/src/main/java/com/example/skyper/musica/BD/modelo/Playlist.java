package com.example.skyper.musica.BD.modelo;

/**
 * Created by Skyper on 23/04/2017.
 */

public class Playlist
{
    int idPlaylist;
    String nombre;

    public int getIdPlaylist()
    {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist)
    {
        this.idPlaylist = idPlaylist;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Playlist(int idPlaylist, String nombre)
    {

        this.idPlaylist = idPlaylist;
        this.nombre = nombre;
    }
}
