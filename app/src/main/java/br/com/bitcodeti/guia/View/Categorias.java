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

import br.com.bitcodeti.guia.Control.AdapterCategorias;
import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.CategoriaVO;

import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Dados;
import br.com.bitcodeti.guia.Uteis.Web;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categorias extends AppCompatActivity {

    ListView listaDeCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        carregar();
    }


    private void carregar() {
        listaDeCategorias = (ListView) findViewById(R.id.listaDeCategorias);
        AdapterCategorias adp = new AdapterCategorias(Categorias.this, Dados.listaDeCategorias);
        listaDeCategorias.setAdapter(adp);
        listaDeCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                CategoriaVO eventos = (CategoriaVO) listaDeCategorias.getItemAtPosition(position);
                ControlePassagem.categoria=eventos.getNome();
                ControlePassagem.id_categoria=eventos.getId();
                Intent i = new Intent(Categorias.this, Locais.class);
                startActivity(i);
            }
        });

        if(Dados.listaDeCategorias.size()==0 || Dados.listaDeCategorias==null){
            Toast.makeText(Categorias.this, "Não foi possível carregar informações, atualize os dados!", Toast.LENGTH_LONG).show();
        }
    }
}
