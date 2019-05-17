package br.com.bitcodeti.guia.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bitcodeti.guia.Model.LocalVO;
import br.com.bitcodeti.guia.R;
import br.com.bitcodeti.guia.Uteis.Strings;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Frederyk Antunnes on 21/04/2017.
 */

public class AdapterLocais extends BaseAdapter {
    private final List<LocalVO> categorias;
    private final Context contexto;

    public AdapterLocais(Context context, List<LocalVO> lista) {
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

        LocalVO evento  = this.categorias.get(position);

        LayoutInflater inflater = LayoutInflater.from(contexto);

        View view = inflater.inflate(R.layout.lista_local, parent, false);

        TextView nome =  (TextView) view.findViewById(R.id.lista_locais_nome);
        nome.setText(evento.getNome().toString());

        TextView endereco =  (TextView) view.findViewById(R.id.lista_locais_endereco);
        endereco.setText(evento.getEndereco().toString());

        TextView categoria =  (TextView) view.findViewById(R.id.lista_locais_categoria);
        categoria.setText(ControlePassagem.categoria.toString());


        ImageView img = (ImageView) view.findViewById(R.id.foto_local);
        Picasso.with(contexto).load(Strings.pastaFoto+evento.getFoto()).placeholder(R.drawable.carregando_imagem).error(R.drawable.local).into(img);

        return view;
    }

}
