package br.com.fiap.educalab;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NoteManager {

    private Context context;

    private String path = Environment.getExternalStorageDirectory().toString();

        public NoteManager(Context _context) {
                this.context = _context;
        }
        public NoteManager(){
	}

        public void CreateNewDirectory() {
            File mydir = new File(this.path);
            if (!mydir.exists())
                mydir.mkdirs();
        }


    public void SaveNote(String sFileName, String sBody){
        try
                {
                File root = new File(this.path);
        if (!root.exists()) {
                root.mkdirs();
        }
                File file = new File(root, sFileName);
                FileWriter writer = new FileWriter(file);
                writer.append(sBody);
                writer.flush();
                writer.close();
                Toast.makeText(this.context,"Saved", Toast.LENGTH_SHORT).show();
	    }
	    catch(IOException e)
                {
                e.printStackTrace();
  }
    }
}
