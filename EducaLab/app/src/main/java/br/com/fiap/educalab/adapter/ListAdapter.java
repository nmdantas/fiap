package br.com.fiap.educalab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.com.fiap.educalab.R;
import br.com.fiap.educalab.models.Objetivos;


public class ListAdapter extends BaseAdapter{

    private Context ctx;
    //PackageManager pm = this.getPackageManager();
    //Intent intent = new Intent(Intent.ACTION_MAIN, null);

    List<Objetivos> l_objetivos;

    public ListAdapter(Context ctx, List<Objetivos> l_objetivos){
        this.ctx = ctx;
        this.l_objetivos = l_objetivos;
    }

    @Override
    public int getCount() {
        //tamanho da lista
        return l_objetivos.size();
    }

    @Override
    public Object getItem(int position) {
        //item referente a posição encontrada
        return l_objetivos.get(position);
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, final View convertView, ViewGroup viewGroup) {
        Objetivos objetivos = l_objetivos.get(position);
        //ApplicationInfo appInfo = rInfo.activityInfo.applicationInfo;

        View view = convertView;

        if(null==view){
            LayoutInflater layoutInflater = (LayoutInflater)
                    ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.lista_list_item,null);
        }

        //objetivo = (TextView) view.findViewById(R.id.item_livro_nome);
        //bg_objetivo = (ImageView) view.findViewById(R.id.list_image);


        //convertendo o q está depois do igual para um resultado q seja igual a textview
        TextView objetivo = (TextView) view.findViewById(R.id.item_livro_nome);
        //ImageView bg_objetivo = (ImageView) view.findViewById(R.id.list_image);

        //TextView t = (TextView) new TextView(ctx);
        objetivo.setText(objetivos.getNomeObjetivo());
        //bg_objetivo.setImageDrawable(objetivos.getBg_objetivo());
        return view;
    }
}