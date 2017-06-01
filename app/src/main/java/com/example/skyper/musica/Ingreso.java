package com.example.skyper.musica;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.skyper.musica.BD.OperacionesBD;
import com.example.skyper.musica.BD.modelo.Evento;
import com.example.skyper.musica.Comunicacion.EnviarServer;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingreso extends AppCompatActivity
{
    private Button button2;
    private Toolbar toolbar;
    private Context context;
    private TextView imei;
    protected OperacionesBD datos;

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
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


    public String getIMEI(Context context)
    {

        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        return imei;

    }


    private void setListeners()
    {
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //enviarRegistroIMEI(getIMEI(context), 1);
                new EnviarServer().enviarRegistroIMEI(context);
            }
        });
    }
    /*
    public void enviarRegistroIMEI(String imei, int idEvento)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.2.106:80/registraUsuario.php";
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("imei",imei);
            jsonObject.put("idEvento",1);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, URL,jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        mostrarToast("Bienvenido al Evento");
                        Intent intent = new Intent().setClass(context, MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        mostrarToast("Error en el envío");
                        error.printStackTrace();
                    }

                });
        requestQueue.add(jsonObjectRequest);
    }
    @Override*/
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setElementos();
        setToolbar();
        setListeners();
    }
    private void insertaEvento()
    {
        try
        {
            datos.getDb().beginTransaction();
            Evento evento = new Evento(1,"Festa","22","23");
            datos.insertarEvento(evento);
            datos.getDb().setTransactionSuccessful();
        }
        finally
        {
            datos.getDb().endTransaction();
        }
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
        setContentView(R.layout.ingreso);
        context=this;
        button2 = (Button)findViewById(R.id.button2);
        imei = (TextView) findViewById(R.id.imei);
        imei.setText((getIMEI(context)));
        datos = OperacionesBD.obtenerInstancia(this);
        //insertaEvento();
    }
}
