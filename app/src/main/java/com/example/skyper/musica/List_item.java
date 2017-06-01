package com.example.skyper.musica;

public class List_item
{
    private int idCancion;
    private String titulo;

    public List_item(int idCancion, String titulo)
    {
        this.idCancion = idCancion;
        this.titulo = titulo;
    }

    public int getIdCancion()
    {
        return idCancion;
    }

    public void setIdCancion(int idCancion)
    {
        this.idCancion = idCancion;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
}
