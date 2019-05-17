package br.com.bitcodeti.guia.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.bitcodeti.guia.Control.AdapterEventos;
import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.EventoVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Dados;
import br.com.bitcodeti.guia.Uteis.Web;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Eventos extends AppCompatActivity {
    ListView listaDeEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        listaDeEventos = (ListView) findViewById(R.id.lista_eventos);
        AdapterEventos adp = new AdapterEventos(Eventos.this, Dados.listaDeEvetos);
        listaDeEventos.setAdapter(adp);
        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ControlePassagem.evento = (EventoVO) listaDeEventos.getItemAtPosition(position);
                Intent i = new Intent(Eventos.this, EventoVer.class);
                startActivity(i);
            }
        });
        if(Dados.listaDeEvetos.size()==0 || Dados.listaDeEvetos==null){
            Toast.makeText(Eventos.this, "Nenhum evento próximo cadastrado!", Toast.LENGTH_LONG).show();
        }
    }
}
