package br.com.bitcodeti.guia.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import br.com.bitcodeti.guia.Control.AdapterEventos;
import br.com.bitcodeti.guia.Control.AdapterEventosPricipal;
import br.com.bitcodeti.guia.Control.ControlePassagem;
import br.com.bitcodeti.guia.Model.EventoVO;

import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Dados;
import br.com.bitcodeti.guia.Uteis.Strings;
import br.com.bitcodeti.guia.Uteis.Web;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listaDeEventos;
    List<EventoVO> listas = new ArrayList<EventoVO>();
    ImageButton btn_categoria, btn_buscar, btn_evento, btn_divulgar, btn_mapa;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if(!Splash.isOnline(Principal.this)){
            Intent i = new Intent(Principal.this, SemInternet.class);
            startActivity(i);
            finish();
        }else{
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("GUIA");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btn = (Button) findViewById(R.id.btn_divulgar);

        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/01.jpeg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/02.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/03.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/04.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/05.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/06.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/07.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/08.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/09.jpg"));
                banners.add(new RemoteBanner(Strings.pastaFoto+"fotos/10.jpg"));
        //add banner using resource drawable
        //banners.add(new DrawableBanner(R.drawable.));
        bannerSlider.setBanners(banners);

        if (ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Principal.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
        }
        carregar();
     //   eventoDeBotao();

        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ControlePassagem.evento = (EventoVO) listaDeEventos.getItemAtPosition(position);
                Intent i = new Intent(Principal.this, EventoVer.class);
                startActivity(i);
            }
        });

        Snackbar.make(findViewById(R.id.drawer_layout), "Bem vindo ao Guia da Cidade!", Snackbar.LENGTH_LONG).show();
botao();

if(Dados.listaDeCategorias.size()==0 || Dados.listaDeCategorias==null){
    Toast.makeText(Principal.this, "Não foi possível carregar informações, atualize os dados!", Toast.LENGTH_LONG).show();
}

    }

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent n = new Intent(Principal.this, Divulgar.class);
            startActivity(n);
        }
    });
    }

    private void carregar() {

        listaDeEventos = (ListView)findViewById(R.id.lista_eventos_principal);
        carregarLista();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
           Intent i = new Intent(Principal.this, Splash.class);
           startActivity(i);
           finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categorias) {
            Intent i = new Intent(Principal.this, Categorias.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_comofunciona) {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
            dialogo.setTitle("GUIA Princesa Isabel");
            dialogo.setMessage("É um App de divulgação, onde mostra eventos, lugares onde ir na cidade de Princesa Isabel.\n\nPara divulgar seu evento ou estabelecimento é bem simples, basta ir no menu DIVULGAR e escolher a opção e Cadastrar sua divulgação!");
            dialogo.setNeutralButton("Sair", null);
            dialogo.show();
        } else if (id == R.id.nav_divulga) {
            Intent i = new Intent(Principal.this, Divulgar.class);
            startActivity(i);

        } else if (id == R.id.nav_home) {
            if(Splash.isOnline(Principal.this)){ carregarLista();}else{
               carregarLista();
            }
        } else if (id == R.id.nav_eventos) {
            Intent i = new Intent(Principal.this, Eventos.class);
            startActivity(i);

        } else if (id == R.id.nav_radio87) {
//            Uri uri = Uri.parse("https://www.radios.com.br/aovivo/radio-princesa-isabel-879-fm/31826");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);

            Intent i = new Intent(Principal.this, RadioFM87.class);
            startActivity(i);

        } else if (id == R.id.nav_radio92) {
//            Uri uri = Uri.parse("https://tudoradio.com/player/estado/17/5254");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);

            Intent i = new Intent(Principal.this, RadioFM92.class);
            startActivity(i);

        } else if (id == R.id.nav_mapa) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=Princesa Isabel, PB"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else{
                Snackbar.make(findViewById(R.id.drawer_layout), "Seu dispositivo não tem apps de Mapa!", Snackbar.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_procurar) {
            Intent i = new Intent(Principal.this, Procurar.class);
            startActivity(i);
        } else if (id == R.id.nav_sobre) {

            AlertDialog.Builder dialogo = new AlertDialog.Builder(Principal.this);
            dialogo.setTitle("GUIA Princesa Isabel");
            dialogo.setMessage("O App foi desenvolvido pela empresa BitCode TI, com o intuito de divulgar cidades, lugares, eventos, entre outros. O melhor de tudo, é Grátis a divulgação. \n\nQuer saber mais?" +
                    "\nwww.bitcodeti.com.br\n\nVersão 1.1");
            dialogo.setNeutralButton("Sair", null);
            dialogo.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void carregarLista() {

        int i = Dados.listaDeEvetos.size();
        List<EventoVO> list = new ArrayList<EventoVO>();

        if(i>=3){
            list.add(Dados.listaDeEvetos.get(0));
            list.add(Dados.listaDeEvetos.get(1));
            list.add(Dados.listaDeEvetos.get(2));
        }else if(i>1 && i<3){
            list.add(Dados.listaDeEvetos.get(0));
            list.add(Dados.listaDeEvetos.get(1));
        }else if(i==1){
            list.add(Dados.listaDeEvetos.get(0));
        }else{
            btn.setVisibility(View.VISIBLE);
            listaDeEventos.setVisibility(View.INVISIBLE);
            LinearLayout ll = (LinearLayout) findViewById(R.id.line_ev);
            ll.setVisibility(View.INVISIBLE);

        }

        AdapterEventosPricipal adp = new AdapterEventosPricipal(Principal.this, list);
        listaDeEventos.setAdapter(adp);

    }

    public void botao(){
        ImageButton btn_hosp = (ImageButton) findViewById(R.id.btn_hosp);
        ImageButton btn_rest = (ImageButton) findViewById(R.id.btn_rest);
        ImageButton btn_post = (ImageButton) findViewById(R.id.btn_postos);
        ImageButton btn_gov = (ImageButton) findViewById(R.id.btn_gov);
        ImageButton btn_lazer = (ImageButton) findViewById(R.id.btn_laser);
        ImageButton btn_bar = (ImageButton) findViewById(R.id.btn_bar);

        btn_hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=40;
                ControlePassagem.categoria="Saúde";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=41;
                ControlePassagem.categoria="Restaurantes";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=29;
                ControlePassagem.categoria="Combustível";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });
        btn_gov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=39;
                ControlePassagem.categoria="Governamental";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });
        btn_lazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=36;
                ControlePassagem.categoria="Lazer";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });
        btn_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlePassagem.id_categoria=37;
                ControlePassagem.categoria="Bar & Chopperia";
                Intent i = new Intent(Principal.this, Locais.class);
                startActivity(i);
            }
        });

    }


}
