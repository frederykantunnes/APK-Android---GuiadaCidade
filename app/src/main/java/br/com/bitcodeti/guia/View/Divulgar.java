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
import android.widget.Toast;

import br.com.bitcodeti.guia.R;

public class Divulgar extends AppCompatActivity {

    Button btn_ligar, btn_mapa, btn_site, btn_div_estab, btn_div_event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divulgar);
        if(!Splash.isOnline(Divulgar.this)){
            Intent i = new Intent(Divulgar.this, SemInternet.class);
            startActivity(i);
            finish();
        }else{
        btn_ligar = (Button)findViewById(R.id.btn_divulgar_ligar);
        btn_mapa = (Button)findViewById(R.id.btn_divulgar_maps);
        btn_site = (Button)findViewById(R.id.btn_divulgar_site);
            btn_div_estab = (Button)findViewById(R.id.div_est);
            btn_div_event = (Button)findViewById(R.id.div_evento);
        eventoDeBotao();
    }}

    @Override
    protected void onResume() {
        if(!Splash.isOnline(Divulgar.this)){
            Intent i = new Intent(Divulgar.this, SemInternet.class);
            startActivity(i);
            finish();
        }
        super.onResume();
    }

    private void eventoDeBotao() {
        btn_ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Divulgar.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Divulgar.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:083998416414"));
                    startActivity(intentLigar);
                }
            }
        });
        btn_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.bitcodeti.com.br";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btn_div_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://guia.bitcodeti.com.br/eventos/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                //Toast.makeText(Divulgar.this, "Em Breve estará disponível, AGUARDE!", Toast.LENGTH_LONG).show();
            }
        });

        btn_div_estab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://guia.bitcodeti.com.br/locais/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                //Toast.makeText(Divulgar.this, "Em Breve estará disponível, AGUARDE!", Toast.LENGTH_LONG).show();
//                Intent i = new Intent(Divulgar.this, MainActivity.class);
//                startActivity(i);

            }
        });

        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=Rua Hermes Maia, 118, Princesa Isabel-PB"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else{
                    Snackbar.make(findViewById(R.id.act_divulgar), "Seu dispositivo não tem apps de Mapa!", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

}
