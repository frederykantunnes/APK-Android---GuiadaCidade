package br.com.bitcodeti.guia.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bitcodeti.guia.Model.EventoVO;

import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Strings;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Frederyk Antunnes on 21/04/2017.
 */

public class AdapterEventos extends BaseAdapter {
    private final List<EventoVO> categorias;
    private final Context contexto;

    public AdapterEventos(Context context, List<EventoVO> lista) {
        this.categorias = lista;
        this.contexto = context;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventoVO evento  = this.categorias.get(position);

        LayoutInflater inflater = LayoutInflater.from(contexto);

        View view = inflater.inflate(R.layout.lista_evento, parent, false);

        TextView nome =  (TextView) view.findViewById(R.id.lista_evento_nome);
        nome.setText(evento.getNome().toString());

        TextView local =  (TextView) view.findViewById(R.id.lista_evento_endereco);
        local.setText(evento.getLocal().toString());

        TextView datahora =  (TextView) view.findViewById(R.id.lista_evento_data);
        datahora.setText(obterDataPorExtenso(evento.getData())+"  -  "+evento.getHora()+"hs");

        TextView valor =  (TextView) view.findViewById(R.id.lista_evento_valor);
        valor.setText("R$ "+evento.getValor().toString());

        ImageView img = (ImageView) view.findViewById(R.id.foto_local_evento);
        Picasso.with(contexto).load(Strings.pastaFoto+evento.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.erro).into(img);

        return view;
    }
    public static  String  obterDataPorExtenso(Date dataAtual){
        Locale BRAZIL = new Locale("pt","BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, dd/MM", BRAZIL);
        return  dfmt.format(dataAtual);
    }
}
