package br.com.fiap.educalab;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.educalab.adapter.ListAdapter;
import br.com.fiap.educalab.models.Objetivos;

public class ListActivity extends AppCompatActivity{

    Drawable imgClick = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lista_layout);

        GridView grid = (GridView) findViewById(R.id.f_gridview);

        final List<Objetivos> objetivos = new ArrayList<Objetivos>();

        Objetivos meta = new Objetivos("Aprender inglês");
        objetivos.add(meta);

        /*meta = new Objetivos("Aprender a tocar violão");
        objetivos.add(meta);
        meta = new Objetivos("Fazer exercícios");
        objetivos.add(meta);*/

        grid.setAdapter(new ListAdapter(this,objetivos));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(ListActivity.this,"Item selecionado",Toast.LENGTH_LONG).show();
                Intent it = new Intent(ListActivity.this, NoteActivity.class);
                startActivity(it);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
