package com.example.skyper.musica.BD;

public class ContratoBD
{
    interface ColumnasEvento
    {
        String ID = "idEvento";
        String nombre = "nombre";
        String inicio = "fecha_hora_inicio";
        String fin = "fecha_hora_fin";
        String ID_Playlist="Playlist_idPlaylist"; //Clave foranea desde Playlist hace referencia a su ID que es la PK.
    }
    interface ColumnasVoto
    {
        String ID = "idVoto";
        String negativos = "negativos";
        String positivos = "positivos";
        String nReproducciones = "nReproducciones";
        String ID_Cancion="Cancion_idCancion"; //Clave foranea desde Cancion hace referencia a su ID que es la PK.
        String ID_Evento="Evento_idEvento"; //Clave foranea desde Evento hace referencia a su ID que es la PK.
        String evento_ID_Playlist="Evento_Playlist_idPlaylist"; //Clave foranea desde Evento hace referencia a la ID de un Playlist, precisamente a idPlaylist.
    }
    interface ColumnasPlaylist
    {
        String ID = "idPlaylist";
        String nombre = "nombre";
    }

    interface ColumnasCancion
    {
        String ID = "idCancion";
        String nombre = "nombre";
    }

    interface ColumnasContiene
    {
        String ID_Cancion="Cancion_idCancion"; //Clave foranea desde Cancion hace referencia a su ID que es la PK.
        String ID_Playlist="Playlist_idPlaylist"; //Clave foranea desde Playlist hace referencia a su ID que es la PK.
    }

    public static class Playlist implements ColumnasPlaylist
    {
    }
    public static class Evento implements ColumnasEvento
    {
    }
    public static class Cancion implements ColumnasCancion
    {
    }
    public static class Voto implements ColumnasVoto
    {
    }
    public static class Contiene implements ColumnasContiene
    {
    }
    private ContratoBD()
    {
    }
}
