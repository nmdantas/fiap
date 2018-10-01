package br.com.fiap.educalab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.fiap.educalab.R;
import br.com.fiap.educalab.models.AplicativoInstalado;
import br.com.fiap.educalab.shared.SharedContent;

public class Adapter extends BaseAdapter{

    private Context ctx;
    //PackageManager pm = this.getPackageManager();
    //Intent intent = new Intent(Intent.ACTION_MAIN, null);

    List<AplicativoInstalado> list;

    public Adapter(Context ctx, List<AplicativoInstalado> list){
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        //tamanho da lista
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //item referente a posição encontrada
        return list.get(position);
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, final View convertView, ViewGroup viewGroup) {
        AplicativoInstalado app = list.get(position);
        //ApplicationInfo appInfo = rInfo.activityInfo.applicationInfo;

        View view = convertView;

        if(null==view){
            LayoutInflater layoutInflater = (LayoutInflater)
                    ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item,null);
        }

        //convertendo o q está depois do igual para um resultado q seja igual a textview
        TextView t = (TextView) view.findViewById(R.id.app_nome);
        ImageView iv = (ImageView) view.findViewById(R.id.app_icon);
        Switch sw = (Switch) view.findViewById(R.id.switch1);

        //TextView t = (TextView) new TextView(ctx);
        t.setText(app.getNome());
        iv.setImageDrawable(app.getIcon());
        sw.setChecked(app.getBlocked());
        sw.setTag(app);

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sSwOn;
                String sSwOff;
                Switch sw = (Switch) v;

                AplicativoInstalado app = (AplicativoInstalado) v.getTag();
                app.setBlocked(sw.isChecked());

                if (sw.isChecked()){
                    sSwOn = sw.getTextOn().toString();
                    Toast.makeText(ctx,sSwOn,Toast.LENGTH_SHORT).show();

                    SharedContent.addToBlackList(app.getPackageName());
                }
                else{
                    sSwOff = sw.getTextOff().toString();
                    Toast.makeText(ctx,sSwOff,Toast.LENGTH_SHORT).show();
                    SharedContent.removeFromBlackList(app.getPackageName());
                }
            }
        });
        return view;
    }
}
