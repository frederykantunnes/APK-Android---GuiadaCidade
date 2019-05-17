package br.com.bitcodeti.guia.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.LocalVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Strings;

import com.squareup.picasso.Picasso;

public class LocalVer extends AppCompatActivity {

    TextView nome, endereco, descricao, telefone, wifi;
    Button ligar;
    RatingBar estrelas;
    ImageButton rota;
    ImageView foto;
    ImageView todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_ver);
        if(!Splash.isOnline(LocalVer.this)){
            Intent i = new Intent(LocalVer.this, SemInternet.class);
            startActivity(i);
            finish();
        }else{
        carregar();
        setTitle(ControlePassagem.local.getNome());
    }}

    @Override
    protected void onResume() {
        if(!Splash.isOnline(LocalVer.this)){
            Intent i = new Intent(LocalVer.this, SemInternet.class);
            startActivity(i);
            finish();
        }
        super.onResume();
    }
    private void carregar() {
        foto = (ImageView) findViewById(R.id.local_foto);
        rota= (ImageButton)findViewById(R.id.local_rota);
        estrelas = (RatingBar)findViewById(R.id.local_estrelas);
        ligar=(Button)findViewById(R.id.local_btn_ligar);
        nome = (TextView)findViewById(R.id.local_nome);
        endereco = (TextView)findViewById(R.id.local_endereco);
        descricao = (TextView)findViewById(R.id.local_descricao);
        telefone = (TextView)findViewById(R.id.local_numero);
        wifi = (TextView)findViewById(R.id.local_wifi);
        todo = (ImageView) findViewById(R.id.img_todo_local);
        final LocalVO vo = ControlePassagem.local;


        nome.setText(vo.getNome());
        endereco.setText(vo.getEndereco());
        descricao.setText(vo.getDescricao());
        telefone.setText(vo.getTelefone());
        wifi.setText(vo.getWifi());
        estrelas.setNumStars(vo.getEstrelas());
        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(LocalVer.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocalVer.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:"+vo.getTelefone()));
                    startActivity(intentLigar);
                }
            }
        });
        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q="+vo.getEndereco()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else{
                    Snackbar.make(findViewById(R.id.act_verlocal), "Seu dispositivo não tem apps de Mapa!", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        Picasso.with(LocalVer.this).load(Strings.pastaFoto+vo.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.erro).into(foto);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setVisibility(View.VISIBLE);
                Picasso.with(LocalVer.this).load(Strings.pastaFoto+vo.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.erro).into(todo);
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setVisibility(View.INVISIBLE);
            }
        });

    }
}
