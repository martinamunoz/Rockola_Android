package com.example.skyper.musica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;
import com.example.skyper.musica.BD.OperacionesBD;
import com.example.skyper.musica.Comunicacion.EnviarServer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private Toolbar toolbar;
    private List items;
    private Context context;
    private int idEvento;
    private String imei;
    protected OperacionesBD datos;

    private void setToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitleToolbar("Música");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setTitleToolbar(String aux)
    {
        toolbar.setTitle(aux);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        invalidateOptionsMenu();
        return true;
    }
    private void setElementos()
    {
        setContentView(R.layout.activity_main);
        context=this;
        imei=getIMEI(context);
        idEvento=getIdEvento();
        listView = (ListView) findViewById(R.id.listView);
        items = new ArrayList();
        listView.setAdapter(new Item_adapter(this, items));
        datos = OperacionesBD.obtenerInstancia(this);
        new EnviarServer().solicitarCanciones(items, context);
    }

    private int getIdEvento()
    {
        return 1;
    }

    private void setListeners()
    {

    }
    public String getIMEI(Context context)
    {
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        return imei;
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
    public void insertarItem(int idCancion, String nombre)
    {
        items.add(new List_item(idCancion, nombre));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setElementos();
        setToolbar();
        setListeners();
    }
}
