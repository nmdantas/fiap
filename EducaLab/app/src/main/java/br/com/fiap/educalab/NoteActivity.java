package br.com.fiap.educalab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    protected void OnCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
                setContentView(R.layout.notepad_layout);

        NoteManager nm = new NoteManager(getApplicationContext());
                nm.CreateNewDirectory();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {
       EditText et_note = (EditText) findViewById(R.id.et_note);
                extras = getIntent().getExtras();
        if(extras != null){
                filename = extras.getString(filename);
               NoteManager nm = new NoteManager(this);
                String text = nm.readNote(filename);
                et_note.setText(text);
        }
    }*/

    /*public void onClick(DialogInterface dialog, int whichButton) {
            String value = input.getText().toString();
            String filename = value + ".txt";
            new NoteManager(this).SaveNote(filename,R.id.et_note.getText().toString());
    }*/

	private void SaveNote(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Note naem");
                alert.setMessage("Please input the note name");
        final EditText input = new EditText(this);
                alert.setView(input);
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                    String value = input.getText().toString();
                    String filename = value +".txt";
			  }
        });
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.<br />
            }
        });
			alert.show();
    }


}
