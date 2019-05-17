package br.com.bitcodeti.guia.View;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.EventoVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Strings;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventoVer extends AppCompatActivity {
    TextView nome, end, descr, outrasinf, preco, organi, data;
    ImageView todo;
    ImageView foto;
    ImageButton rota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_ver);
        if(!Splash.isOnline(EventoVer.this)){
            Intent i = new Intent(EventoVer.this, SemInternet.class);
            startActivity(i);
            finish();
        }else{
        carregar();
        setTitle(ControlePassagem.evento.getNome());
    }}

    @Override
    protected void onResume() {
        if(!Splash.isOnline(EventoVer.this)){
            Intent i = new Intent(EventoVer.this, SemInternet.class);
            startActivity(i);
            finish();
        }
        super.onResume();
    }

    private void carregar() {
        nome = (TextView) findViewById(R.id.evnt_nome);
        end = (TextView) findViewById(R.id.evnt_endereco);
        descr = (TextView) findViewById(R.id.evnt_descri);
        outrasinf = (TextView) findViewById(R.id.evnt_outrasinfo);
        preco = (TextView) findViewById(R.id.evnt_preco);
        organi = (TextView) findViewById(R.id.evnt_organizacao);
        data = (TextView) findViewById(R.id.evnt_data);
        foto = (ImageView) findViewById(R.id.evnt_foto);
        todo = (ImageView) findViewById(R.id.img_todo);

        final EventoVO vo = ControlePassagem.evento;
        nome.setText(vo.getNome());
        end.setText(vo.getLocal());
        descr.setText(vo.getDescricao());
        outrasinf.setText("*"+vo.getAdicionais());
        preco.setText("R$ "+vo.getValor());
        organi.setText("Organização: "+vo.getOrganizacao());
        data.setText(obterDataPorExtenso(vo.getData())+" às "+vo.getHora()+"hs");
        Picasso.with(EventoVer.this).load(Strings.pastaFoto+vo.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.erro).into(foto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setVisibility(View.VISIBLE);
                Picasso.with(EventoVer.this).load(Strings.pastaFoto+vo.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.erro).into(todo);
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setVisibility(View.INVISIBLE);
            }
        });

        rota = (ImageButton)findViewById(R.id.evento_rota);
        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q="+vo.getLocal()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else{
                    Snackbar.make(findViewById(R.id.act_ev_ver), "Seu dispositivo não tem apps de Mapa!", Snackbar.LENGTH_LONG).show();
                }

            }
        });

    }

    public static  String  obterDataPorExtenso(Date dataAtual){
        Locale BRAZIL = new Locale("pt","BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, dd/MM", BRAZIL);
        return  dfmt.format(dataAtual);
    }
}
