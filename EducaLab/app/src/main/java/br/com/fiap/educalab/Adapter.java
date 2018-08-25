package br.com.fiap.educalab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.fiap.educalab.models.AplicativoInstalado;

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        AplicativoInstalado app = list.get(position);
        //ApplicationInfo appInfo = rInfo.activityInfo.applicationInfo;

        TextView t = new TextView(ctx);
        t.setText(app.getNome());

        return t;
    }

    //teste
    /*public PackageManager getPackageManager() {
        return pm;
    }*/
    //teste
}
