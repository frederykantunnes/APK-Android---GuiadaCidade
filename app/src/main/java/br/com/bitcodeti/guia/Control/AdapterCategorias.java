package br.com.bitcodeti.guia.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.bitcodeti.guia.Model.CategoriaVO;
import br.com.bitcodeti.guia.R;

import java.util.List;

/**
 * Created by Frederyk Antunnes on 21/04/2017.
 */

public class AdapterCategorias extends BaseAdapter {
    private final List<CategoriaVO> categorias;
    private final Context contexto;

    public AdapterCategorias(Context context, List<CategoriaVO> lista) {
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

        CategoriaVO evento  = this.categorias.get(position);

        LayoutInflater inflater = LayoutInflater.from(contexto);

        View view = inflater.inflate(R.layout.lista_categorias, parent, false);

        TextView nome =  (TextView) view.findViewById(R.id.lista_categoria_nome);
        nome.setText(evento.getNome().toString());

        TextView descricao =  (TextView) view.findViewById(R.id.lista_categoria_descricao);
        descricao.setText(evento.getDescricao().toString());

        return view;
    }

}
