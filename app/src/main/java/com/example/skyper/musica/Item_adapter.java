package com.example.skyper.musica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skyper.musica.Comunicacion.EnviarServer;
import com.example.skyper.musica.List_item;
import com.example.skyper.musica.R;

import java.util.List;

/**
 * Created by Skyper on 23/04/2017.
 */

public class Item_adapter extends BaseAdapter
{
    private Context context;
    private List<List_item> items;

    public Item_adapter(Context context, List<List_item> items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return this.items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View rowView = convertView;

        if (convertView == null)
        {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // Set data into the view.
        ImageButton btnNegativo = (ImageButton) rowView.findViewById(R.id.btnNegativo);
        TextView titulo = (TextView) rowView.findViewById(R.id.titulo);
        TextView idCancion = (TextView) rowView.findViewById(R.id.idCancion);
        ImageButton btnPositivo = (ImageButton) rowView.findViewById(R.id.btnPositivo);
        //Carga de los atributos de esta clase.
        final List_item item = this.items.get(position);
        titulo.setText(item.getTitulo());
        idCancion.setText(String.valueOf(item.getIdCancion()));
        btnNegativo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new EnviarServer().enviarVotoCancion(item.getIdCancion(),(-1), context);
            }
        });

        btnPositivo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new EnviarServer().enviarVotoCancion(item.getIdCancion(),1, context);
            }
        });
        return rowView;
    }
}
