package br.com.bitcodeti.guia.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.bitcodeti.guia.Control.AdapterLocais;
import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.LocalVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Dados;
import br.com.bitcodeti.guia.Uteis.Web;
import com.wang.avi.AVLoadingIndicatorView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Locais extends AppCompatActivity {

    ListView listaDeLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);
        setTitle(ControlePassagem.categoria);

            List<LocalVO> lis = new ArrayList<LocalVO>();
            listaDeLocais = (ListView) findViewById(R.id.listaDeLoc);
            for (int i=0; i<Dados.listaDeLocais.size(); i++){
                if(Dados.listaDeLocais.get(i).getCategoria()==ControlePassagem.id_categoria){
                    lis.add(Dados.listaDeLocais.get(i));
                }
            }



            AdapterLocais adp = new AdapterLocais(Locais.this, lis);


        if(lis.size()==0 || lis==null){
           if(Dados.listaDeCategorias.size()==0 || Dados.listaDeCategorias==null){
               Toast.makeText(Locais.this, "Os dados não foram carregados, atualize os dados!", Toast.LENGTH_LONG).show();
           }else{
               Toast.makeText(Locais.this, "Nenhum local cadastrado nessa categoria, CADASTRE GRÁTIS :)", Toast.LENGTH_LONG).show();
           }
        }


            listaDeLocais.setAdapter(adp);
            listaDeLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                    ControlePassagem.local = (LocalVO) listaDeLocais.getItemAtPosition(position);
                    Intent i = new Intent(Locais.this, LocalVer.class);
                    startActivity(i);
                }
            });
    }
}
