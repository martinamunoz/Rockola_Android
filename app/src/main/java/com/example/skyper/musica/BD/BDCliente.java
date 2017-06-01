package com.example.skyper.musica.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;


public class BDCliente extends SQLiteOpenHelper
{

        private static final String NOMBRE_BASE_DATOS = "cliente.db";

        private static final int VERSION_ACTUAL = 1;

        private final Context contexto;

        interface Tablas
        {
            String EVENTO = "evento";
            String CANCION = "cancion";
            String PLAYLIST = "playlist";
            String VOTO = "voto";
            String CONTIENE = "contiene";
        }

        interface Referencias
        {

            String ID_Playlist = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                    Tablas.PLAYLIST, ContratoBD.Playlist.ID);
            String ID_Cancion = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                    Tablas.CANCION, ContratoBD.Cancion.ID);
            String ID_Evento = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                    Tablas.EVENTO, ContratoBD.Evento.ID);
            String evento_ID_Playlist = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                    Tablas.EVENTO, ContratoBD.Evento.ID_Playlist);

        }
        public BDCliente(Context contexto)
        {
            super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
            this.contexto = contexto;
        }

        @Override
        public void onOpen(SQLiteDatabase db)
        {
            super.onOpen(db);
            if (!db.isReadOnly()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    db.setForeignKeyConstraintsEnabled(true);
                } else {
                    db.execSQL("PRAGMA foreign_keys=ON");
                }
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.EVENTO);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.VOTO);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.PLAYLIST);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.CANCION);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.CONTIENE);
            //Tabla EVENTO.
            db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s INTEGER UNIQUE NOT NULL, %s TEXT NOT NULL,%s TEXT NOT NULL," +
                            "%s TEXT NOT NULL)",
                            Tablas.EVENTO, BaseColumns._ID,
                            ContratoBD.Evento.ID, ContratoBD.Evento.nombre,ContratoBD.Evento.inicio,
                            ContratoBD.Evento.fin));
            //Tabla VOTO.
            db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s INTEGER UNIQUE NOT NULL, %s INTEGER NOT NULL,%s INTEGER NOT NULL," +
                            "%s INTEGER NOT NULL, %s INTEGER NOT NULL %s," +
                            "%s INTEGER NOT NULL %s)",
                            Tablas.VOTO, BaseColumns._ID,
                            ContratoBD.Voto.ID, ContratoBD.Voto.negativos,ContratoBD.Voto.positivos,
                            ContratoBD.Voto.nReproducciones, ContratoBD.Voto.ID_Cancion, Referencias.ID_Cancion,
                            ContratoBD.Voto.ID_Evento, Referencias.ID_Evento));
            /*//Tabla PLAYLIST.
            db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s INTEGER NOT NULL ,%s TEXT NOT NULL)",
                            Tablas.PLAYLIST, BaseColumns._ID,
                            ContratoBD.Playlist.ID, ContratoBD.Playlist.nombre));*/

            //Tabla CANCION.
            db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s INTEGER NOT NULL,%s TEXT NOT NULL)",
                            Tablas.CANCION, BaseColumns._ID,
                            ContratoBD.Cancion.ID,ContratoBD.Cancion.nombre));
            /*
            //Tabla CONTIENE.
            db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "%s INTEGER  NOT NULL %s)",
                            Tablas.CONTIENE, BaseColumns._ID,
                            ContratoBD.Contiene.ID_Cancion, Referencias.ID_Cancion));*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.EVENTO);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.VOTO);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.PLAYLIST);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.CANCION);
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.CONTIENE);
            onCreate(db);
        }


    }
