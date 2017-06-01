package com.example.skyper.musica.Comunicacion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.skyper.musica.BD.OperacionesBD;
import com.example.skyper.musica.BD.modelo.Cancion;
import com.example.skyper.musica.List_item;
import com.example.skyper.musica.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EnviarServer extends AppCompatActivity
{
    Context context;
    private void mostrarToast(String mensaje)
    {
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }
    private String getIMEI(Context context)
    {
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return mngr.getDeviceId();
    }
    private String getIPServer()
    {
        return "192.168.1.42:80";
    }
    private int getIdEvento()
    {
        return 1;
    }

    public void enviarVotoCancion(int idCancion, int tipoVoto, Context context)
    {
        int[] params=new int[2];
        params[0] = idCancion;
        params[1] = tipoVoto;
        this.context = context;
        new EnviarVotoCancion().execute(params);
    }
    public void solicitarCanciones(List params, Context context)
    {
        this.context = context;
        new SolicitarCanciones().execute(params);
    }
    public void enviarRegistroIMEI(Context context)
    {
        this.context=context;
        new EnviarRegistroIMEI().execute();
    }
    private class EnviarVotoCancion extends AsyncTask< int[], Void, Boolean>
    {
        Boolean retorno;
        @Override
        protected void onProgressUpdate(Void... progress)
        {
        }
        @Override
        protected void onPostExecute(Boolean result)
        {
            if(result)
                mostrarToast("Voto enviado");
        }

        @Override
        protected Boolean doInBackground(int[]... params)
        {
            int[] parametros=params[0];
            int idCancion = parametros[0];
            int tipoVoto=parametros[1];
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = "http://"+getIPServer()+"/insertaVoto.php";
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("imei",getIMEI(context));
            jsonBody.put("idEvento", getIdEvento());
            jsonBody.put("idCancion", idCancion);
            jsonBody.put("tipoVoto",tipoVoto);
            retorno=false;
            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, URL,new JSONObject(jsonBody),
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            mostrarToast("Servidor respondio solicitud");
                            retorno=true;
                        }
                    },
                    new Response.ErrorListener()
                    {

                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            mostrarToast("Error al enviar voto");
                            error.printStackTrace();
                        }

                    });
            requestQueue.add(jsonObjectRequest);
            return retorno;
        }
    }
    private class EnviarRegistroIMEI extends AsyncTask< Void, Void, Boolean>
    {
        Boolean retorno;
        public Boolean getRetorno()
        {
            return retorno;
        }
        @Override
        protected void onProgressUpdate(Void... progress)
        {
        }
        @Override
        protected void onPostExecute(Boolean result)
        {
            if(result)
                mostrarToast("");
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = "http://"+getIPServer()+"/registraUsuario.php";
            JSONObject jsonObject = new JSONObject();
            try
            {
                jsonObject.put("imei",getIMEI(context));
                jsonObject.put("idEvento",getIdEvento());
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            retorno=false;
            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, URL,jsonObject,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            retorno=true;
                            mostrarToast("Bienvenido al Evento");
                            Intent intent = new Intent().setClass(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            mostrarToast("Error al intentar ingresar al evento");
                            error.printStackTrace();
                        }

                    });
            requestQueue.add(jsonObjectRequest);
            return retorno;
        }
    }
    private class SolicitarCanciones extends AsyncTask<List, Void, Boolean>
    {
        Boolean retorno;
        @Override
        protected void onProgressUpdate(Void... progress)
        {
        }
        @Override
        protected void onPostExecute(Boolean result)
        {
            if(result)
                mostrarToast("Canciones cargadas");
        }

        @Override
        protected Boolean doInBackground(final List... params)
        {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = "http://"+getIPServer()+"/enviarCanciones.php";
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try
            {
                jsonObject.put("imei",getIMEI(context));
                jsonObject.put("idEvento",getIdEvento());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
            retorno=false;
            JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(Request.Method.POST, URL,jsonArray,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            mostrarToast("Obteniendo lista de canciones del evento");
                            try
                            {
                                for(int i=0;i<response.length();i++)
                                {

                                    JSONObject jresponse = response.getJSONObject(i);
                                    int idCancion = jresponse.getInt("idCancion");
                                    String nombre = jresponse.getString("nombre");
                                    Cancion cancion = new Cancion(idCancion, nombre);
                                    OperacionesBD datos = OperacionesBD.obtenerInstancia(context);
                                    try
                                    {
                                        datos.getDb().beginTransaction();
                                        datos.insertarCancion(cancion);
                                        datos.getDb().setTransactionSuccessful();
                                        insertarItem(params[0],idCancion,nombre);
                                    }
                                    finally
                                    {
                                        datos.getDb().endTransaction();
                                    }
                                    retorno=true;
                                }

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            mostrarToast("Error al recibir canciones del evento");
                            error.printStackTrace();
                        }

                    });
            requestQueue.add(jsonArrayRequest);
            return retorno;
        }
        private void insertarItem(List items,int idCancion, String nombre)
        {
            items.add(new List_item(idCancion, nombre));
        }
    }
}


