package br.com.bitcodeti.guia.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

import br.com.bitcodeti.guia.Control.AdapterEventos;
import br.com.bitcodeti.guia.Model.CategoriaVO;
import br.com.bitcodeti.guia.Model.EventoVO;
import br.com.bitcodeti.guia.Model.LocalVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Dados;
import br.com.bitcodeti.guia.Uteis.Web;

public class Splash extends Activity {

    TextView status;
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        status = (TextView)findViewById(R.id.txt_status);
            new Handler().postDelayed(new Runnable() {
                /*
                 * Exibindo splash com um timer.
                 */
                @Override
                public void run() {

                        if(isOnline(Splash.this)){
                           carregarListaCategorias();
                        }else{
                            Intent i = new Intent(Splash.this, SemInternet.class);
                            startActivity(i);
                            finish();
                        }
                }
            }, SPLASH_TIME_OUT);

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }



    private void carregarListaCategorias() {
        AsyncTask<Object, Object, String> bustarAssinc = new AsyncTask<Object, Object, String>() {
            @Override
            protected String doInBackground(Object... objects) {
                return Web.listarCategorias();
            }
            @Override
            protected void onPostExecute(String jsonString) {
                if(jsonString==null || jsonString.isEmpty() || jsonString.equalsIgnoreCase("0") || jsonString.equalsIgnoreCase("null")){
                   // Snackbar.make(findViewById(R.id.splash), "Nenhuma categoria encontrada!", Snackbar.LENGTH_LONG).show();
                }else{
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        int count = jsonArray.length();
                        for (int i = 0; i < count; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            CategoriaVO user = new CategoriaVO();
                            user.setNome(jsonObject.getString("nome"));
                            user.setId(Integer.parseInt(jsonObject.getString("id")));
                            user.setDescricao(jsonObject.getString("descricao"));
                                Dados.listaDeCategorias.add(user);
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                      //  Snackbar.make(findViewById(R.id.splash), "Erro, tente novamente mais tarde!", Snackbar.LENGTH_LONG).show();
                    }


                }
                carregarListaLocais();
            }
            @Override
            protected void onPreExecute() {
                Dados.listaDeCategorias.clear();
                status.setText("Carregando Categorias...");
            }
        }.execute();
    }


    private void carregarListaLocais() {
        AsyncTask<Object, Object, String> bustarAssinc2 = new AsyncTask<Object, Object, String>() {
            @Override
            protected String doInBackground(Object... objects) {
                return Web.listarLocais();
            }
            @Override
            protected void onPostExecute(String jsonString) {

                if(jsonString==null || jsonString.isEmpty() || jsonString.equalsIgnoreCase("0") || jsonString.equalsIgnoreCase("null")){
                   // Snackbar.make(findViewById(R.id.splash), "Nenhum lugar encontrado!", Snackbar.LENGTH_LONG).show();
                }else{
                    try {
                        String a = jsonString;
                        JSONArray jsonArray = new JSONArray(a);
                        int count = jsonArray.length();
                        for (int i = 0; i < count; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            LocalVO user = new LocalVO();

                            user.setId(Integer.parseInt(jsonObject.getString("id")));
                            user.setCategoria(Integer.parseInt(jsonObject.getString("categoria")));
                            user.setStatus(1);
                            user.setEstrelas(Integer.parseInt(jsonObject.getString("estrelas")));
                            user.setNome(jsonObject.getString("nome"));
                            user.setEndereco(jsonObject.getString("endereco"));
                            user.setFoto(jsonObject.getString("foto"));
                            user.setDescricao(jsonObject.getString("descricao"));
                            user.setTelefone(jsonObject.getString("telefone"));
                            user.setCelular(jsonObject.getString("celular"));
                            user.setWifi(jsonObject.getString("wifi"));
                            Dados.listaDeLocais.add(user);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                       // Snackbar.make(findViewById(R.id.splash), "Nenhum lugar encontrado!", Snackbar.LENGTH_LONG).show();

                    }

                    carregarListaEventos();
                }
            }
            @Override
            protected void onPreExecute() {
                Dados.listaDeLocais.clear();
                status.setText("Carregando Locais...");

            }
        }.execute();


    }




    private void carregarListaEventos() {
        AsyncTask<Object, Object, String> bustarAssinc = new AsyncTask<Object, Object, String>() {
            @Override
            protected String doInBackground(Object... objects) {
                Web client = new Web();
                String resposta = client.listarEventos();
                return resposta;
            }
            @Override
            protected void onPostExecute(String jsonString) {
                if(jsonString==null || jsonString.isEmpty() || jsonString.equalsIgnoreCase("0") || jsonString.equalsIgnoreCase("null")){
                    //Snackbar.make(findViewById(R.id.splash), "Nenhum evento próximo!", Snackbar.LENGTH_LONG).show();
                }else{
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        int count = jsonArray.length();
                        for (int i = 0; i < count; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            EventoVO user = new EventoVO();

                            user.setId(Integer.parseInt(jsonObject.getString("id")));
                            user.setNome(jsonObject.getString("nome"));
                            user.setLocal(jsonObject.getString("local"));
                            user.setData(Date.valueOf(jsonObject.getString("data")));
                            user.setHora(jsonObject.getString("hora"));
                            user.setOrganizacao(jsonObject.getString("organizacao"));
                            user.setDescricao(jsonObject.getString("descricao"));
                            user.setAdicionais(jsonObject.getString("adicionais"));
                            user.setValor(jsonObject.getString("valor"));
                            user.setFoto(jsonObject.getString("foto"));

                            Dados.listaDeEvetos.add(user);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Snackbar.make(findViewById(R.id.splash), "Nenhum evento próximo!", Snackbar.LENGTH_LONG).show();
                    }
                }
                Intent i = new Intent(Splash.this, Principal.class);
                startActivity(i);
                finish();

            }
            @Override
            protected void onPreExecute() {
                Dados.listaDeEvetos.clear();
                status.setText("Carregando Eventos...");
            }
        }.execute();


    }



}