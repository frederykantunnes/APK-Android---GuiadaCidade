package br.com.bitcodeti.guia.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import br.com.bitcodeti.guia.Control.AdapterLocais;
import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.LocalVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Web;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Procurar extends AppCompatActivity {
    AVLoadingIndicatorView ind;
    Button btn_buscar;
    EditText campo_buscar;
    ListView listaDeLocais;
    List<LocalVO> listas = new ArrayList<LocalVO>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);
        if(!Splash.isOnline(Procurar.this)){
            Intent i = new Intent(Procurar.this, SemInternet.class);
            startActivity(i);
            finish();
        }else{
        carregarComponenstes();
        eventoDeBotao();
        ind = (AVLoadingIndicatorView)findViewById(R.id.avi_locais_busca);
        ind.hide();
    }}
    public void carregarComponenstes(){
        listaDeLocais = (ListView) findViewById(R.id.listaDeLocaisBusca);
        btn_buscar = (Button) findViewById(R.id.btn_buscar);
        campo_buscar = (EditText) findViewById(R.id.campo_buscar);

        listaDeLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ControlePassagem.local = (LocalVO) listaDeLocais.getItemAtPosition(position);
                Intent i = new Intent(Procurar.this, LocalVer.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        if(!Splash.isOnline(Procurar.this)){
            Intent i = new Intent(Procurar.this, SemInternet.class);
            startActivity(i);
            finish();
        }
        super.onResume();
    }

    private void eventoDeBotao() {
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo = campo_buscar.getText().toString();
                carregarLista(campo);


            }
        });
    }


    private void carregarLista(final String campo) {
        AsyncTask<Object, Object, String> bustarAssinc = new AsyncTask<Object, Object, String>() {

            @Override
            protected String doInBackground(Object... objects) {
                Web client = new Web();
                String resposta = client.listarLocaisBusca(campo);
                return resposta;
            }
            @Override
            protected void onPostExecute(String jsonString) {
                if(jsonString==null || jsonString.isEmpty() || jsonString.equalsIgnoreCase("0") || jsonString.equalsIgnoreCase("null")){
                    Snackbar.make(findViewById(R.id.act_buscar), "Nenhum lugar encontrado '"+campo+"'!", Snackbar.LENGTH_LONG).show();
                    ind.hide();
                }else{
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        int count = jsonArray.length();
                        for (int i = 0; i < count; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            LocalVO user = new LocalVO();

                            user.setId(Integer.parseInt(jsonObject.getString("id")));
                            user.setCategoria(Integer.parseInt(jsonObject.getString("categoria")));
                            //user.setStatus(Integer.parseInt(jsonObject.getString("status")));
                            user.setStatus(1);
                            user.setEstrelas(Integer.parseInt(jsonObject.getString("estrelas")));
                            user.setNome(jsonObject.getString("nome"));
                            user.setEndereco(jsonObject.getString("endereco"));
                            user.setFoto(jsonObject.getString("foto"));
                            user.setDescricao(jsonObject.getString("descricao"));
                            user.setTelefone(jsonObject.getString("telefone"));
                            user.setCelular(jsonObject.getString("celular"));
                            user.setWifi(jsonObject.getString("wifi"));
                            listas.add(user);
                        }
                        List<LocalVO> lista;
                        lista = listas;
                        AdapterLocais adp = new AdapterLocais(Procurar.this, lista);
                        listaDeLocais.setAdapter(adp);
                    } catch (JSONException e) {
                        e.printStackTrace();

                        Snackbar.make(findViewById(R.id.act_buscar), "Nenhum lugar encontrado '"+campo+"'!", Snackbar.LENGTH_LONG).show();
                        ind.hide();

                    }
                    ind.hide();
                }
            }
            @Override
            protected void onPreExecute() {
                listas.clear();
                ind.show();

            }
        }.execute();


    }
}
