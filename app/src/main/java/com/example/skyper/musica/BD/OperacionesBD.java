package com.example.skyper.musica.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.skyper.musica.BD.modelo.Cancion;
import com.example.skyper.musica.BD.modelo.Evento;
import com.example.skyper.musica.BD.modelo.Playlist;
import com.example.skyper.musica.BD.modelo.Voto;


public final class OperacionesBD
{
    private static BDCliente baseDatos;

    private static OperacionesBD instancia = new OperacionesBD();

    private OperacionesBD()
    {
    }

    public static OperacionesBD obtenerInstancia(Context contexto)
    {
        if (baseDatos == null)
        {
            baseDatos = new BDCliente(contexto);
        }
        return instancia;
    }

    //EVENTO
    public int insertarEvento(Evento evento)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Evento.ID, evento.getIdEvento());
        valores.put(ContratoBD.Evento.nombre, evento.getNombre());
        valores.put(ContratoBD.Evento.inicio, evento.getInicio());
        valores.put(ContratoBD.Evento.fin, evento.getFin());
        //Insertar Fila
        db.insertOrThrow(BDCliente.Tablas.EVENTO, null, valores);
        return 1;
    }
    public boolean modificarEvento(Evento eventoNuevo, int idEventoViejo)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Evento.ID, eventoNuevo.getIdEvento());
        valores.put(ContratoBD.Evento.nombre, eventoNuevo.getNombre());
        valores.put(ContratoBD.Evento.inicio, eventoNuevo.getInicio());
        valores.put(ContratoBD.Evento.fin, eventoNuevo.getFin());
        String whereClause = String.format(ContratoBD.Evento.ID+" = %s", Integer.toString(idEventoViejo));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.EVENTO, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarEvento(Evento eventoNuevo, int idEventoViejo)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Evento.ID, eventoNuevo.getIdEvento());
        valores.put(ContratoBD.Evento.nombre, eventoNuevo.getNombre());
        valores.put(ContratoBD.Evento.inicio, eventoNuevo.getInicio());
        valores.put(ContratoBD.Evento.fin, eventoNuevo.getFin());
        String whereClause = String.format(ContratoBD.Evento.ID+" = %s", Integer.toString(idEventoViejo));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.EVENTO, valores, whereClause, null);
        return resultado > 0;
    }

    public boolean eliminarEvento(Evento evento)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format(ContratoBD.Evento.ID+" = %s", Integer.toString(evento.getIdEvento()));
        int resultado = db.delete(BDCliente.Tablas.EVENTO, whereClause, null);
        return resultado > 0;
    }

    public Cursor obtenerEventos()
    {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BDCliente.Tablas.EVENTO);
        return db.rawQuery(sql, null);
    }

    //VOTO
    public int insertarVoto(Voto voto)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Voto.ID, voto.getIdVoto());
        valores.put(ContratoBD.Voto.negativos, voto.getNegativos());
        valores.put(ContratoBD.Voto.positivos, voto.getPositivos());
        valores.put(ContratoBD.Voto.nReproducciones, voto.getnReproducciones());
        valores.put(ContratoBD.Voto.ID_Cancion, voto.getID_Cancion());
        valores.put(ContratoBD.Voto.ID_Evento, voto.getID_Evento());
        //Insertar Fila
        db.insertOrThrow(BDCliente.Tablas.VOTO, null, valores);
        return 1;
    }
    public boolean modificarVoto(Voto votoNuevo, int idVotoViejo)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Voto.negativos, votoNuevo.getNegativos());
        valores.put(ContratoBD.Voto.positivos, votoNuevo.getPositivos());
        valores.put(ContratoBD.Voto.nReproducciones, votoNuevo.getnReproducciones());
        valores.put(ContratoBD.Voto.ID_Cancion, votoNuevo.getID_Cancion());
        valores.put(ContratoBD.Voto.ID_Evento, votoNuevo.getID_Evento());
        String whereClause = String.format(ContratoBD.Voto.ID+" = %s", Integer.toString(idVotoViejo));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.VOTO, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarVoto(Voto votoNuevo, int idVotoViejo)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Voto.negativos, votoNuevo.getNegativos());
        valores.put(ContratoBD.Voto.positivos, votoNuevo.getPositivos());
        valores.put(ContratoBD.Voto.nReproducciones, votoNuevo.getnReproducciones());
        valores.put(ContratoBD.Voto.ID_Cancion, votoNuevo.getID_Cancion());
        valores.put(ContratoBD.Voto.ID_Evento, votoNuevo.getID_Evento());
        String whereClause = String.format(ContratoBD.Voto.ID+" = %s", Integer.toString(idVotoViejo));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.VOTO, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarVoto(Voto voto)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format(ContratoBD.Voto.ID+" = %s", Integer.toString(voto.getIdVoto()));
        int resultado = db.delete(BDCliente.Tablas.VOTO, whereClause, null);
        return resultado > 0;
    }

    public Cursor obtenerVotos()
    {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BDCliente.Tablas.VOTO);
        return db.rawQuery(sql, null);
    }
    //Playlist
    public int insertarPlaylist(Playlist playlist)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Playlist.ID, playlist.getIdPlaylist());
        valores.put(ContratoBD.Playlist.nombre, playlist.getNombre());
        //Insertar Fila
        db.insertOrThrow(BDCliente.Tablas.PLAYLIST, null, valores);
        return 1;
    }
    public boolean modificarPlaylist(Playlist playlistNueva, int idPlaylistVieja)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Playlist.ID, playlistNueva.getIdPlaylist());
        valores.put(ContratoBD.Playlist.nombre, playlistNueva.getNombre());
        String whereClause = String.format(ContratoBD.Voto.ID+" = %s", Integer.toString(idPlaylistVieja));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.PLAYLIST, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarPlaylist(Playlist playlistNueva, int idPlaylistVieja)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Playlist.ID, playlistNueva.getIdPlaylist());
        valores.put(ContratoBD.Playlist.nombre, playlistNueva.getNombre());
        String whereClause = String.format(ContratoBD.Playlist.ID+" = %s", Integer.toString(idPlaylistVieja));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.PLAYLIST, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarPlaylist(Playlist playlist)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format(ContratoBD.Playlist.ID+" = %s", Integer.toString(playlist.getIdPlaylist()));
        int resultado = db.delete(BDCliente.Tablas.PLAYLIST, whereClause, null);
        return resultado > 0;
    }

    public Cursor obtenerPlaylist()
    {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BDCliente.Tablas.PLAYLIST);
        return db.rawQuery(sql, null);
    }
    //Cancion
    public int insertarCancion(Cancion cancion)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Cancion.ID, cancion.getIdCancion());
        valores.put(ContratoBD.Cancion.nombre, cancion.getNombre());
        //Insertar Fila
        db.insertOrThrow(BDCliente.Tablas.CANCION, null, valores);
        return 1;
    }
    public boolean modificarCancion(Cancion cancionNueva, int idCancionNueva)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Cancion.ID, cancionNueva.getIdCancion());
        valores.put(ContratoBD.Cancion.nombre, cancionNueva.getNombre());
        String whereClause = String.format(ContratoBD.Cancion.ID+" = %s", Integer.toString(idCancionNueva));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.CANCION, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarCancion(Cancion cancionNueva, int idCancionNueva)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContratoBD.Cancion.ID, cancionNueva.getIdCancion());
        valores.put(ContratoBD.Cancion.nombre, cancionNueva.getNombre());
        String whereClause = String.format(ContratoBD.Cancion.ID+" = %s", Integer.toString(idCancionNueva));
        //String[] whereArgs = {Integer.toString(reclamoNuevo.getnReclamo())};
        int resultado = db.update(BDCliente.Tablas.CANCION, valores, whereClause, null);
        return resultado > 0;
    }
    public boolean eliminarCancion(Cancion cancion)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = String.format(ContratoBD.Cancion.ID+" = %s", Integer.toString(cancion.getIdCancion()));
        int resultado = db.delete(BDCliente.Tablas.CANCION, whereClause, null);
        return resultado > 0;
    }

    public Cursor obtenerCancion()
    {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BDCliente.Tablas.CANCION);
        return db.rawQuery(sql, null);
    }

    public SQLiteDatabase getDb()
    {
        return baseDatos.getWritableDatabase();
    }
}
