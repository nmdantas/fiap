package br.com.fiap.educalab;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.educalab.adapter.Adapter;
import br.com.fiap.educalab.models.AplicativoInstalado;
import br.com.fiap.educalab.shared.SharedContent;
import br.com.fiap.educalab.timer.TimePickerFragment;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final int RC_SIGN_IN = 0324;
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //timer picker
            DialogFragment timepicker = new TimePickerFragment();
            timepicker.show(getSupportFragmentManager(),"time picker");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        SharedContent.setExpireDate(calendar.getTime());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                loadUserInfo(user);
                loadContent();
            } else {

                if (response == null) {
                    Toast.makeText(this, "Login Cancelado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private AlertDialog buildNotificationServiceAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setMessage(R.string.menssagem_permissao);
        alertDialogBuilder.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If you choose to not enable the notification listener
                        // the app. will not work as expected
                    }
                });
        return(alertDialogBuilder.create());
    }

    private boolean isNotificationServiceEnabled(){
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void loadContent() {
        //grid view
        GridView gv = findViewById(R.id.gridview);

        if (!isNotificationServiceEnabled()){
            enableNotificationListenerAlertDialog = buildNotificationServiceAlertDialog();
            enableNotificationListenerAlertDialog.show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //  .setAction("Action", null).show();
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                startActivity(it);
            }
        });

        PackageManager pm = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        final List<AplicativoInstalado> apps = new ArrayList<>();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, pm.PERMISSION_GRANTED);

        for (ResolveInfo rInfo : list) {
            ApplicationInfo appInfo = rInfo.activityInfo.applicationInfo;
            Drawable icon = appInfo.loadIcon(pm);
            String packageName = rInfo.resolvePackageName;
            //Switch sw = findViewById(R.id.switch1);


            Log.w("======================", "");
            Log.w("Installed Applications", appInfo.loadLabel(pm).toString());
            Log.w("Installed Applications", appInfo.packageName);
            //Log.w("Installed Applications", category.getDescription());
            Log.w("======================", "");

            apps.add(new AplicativoInstalado(appInfo.loadLabel(pm).toString(), appInfo.packageName,
                    appInfo.loadIcon(pm)));
        }

        gv.setAdapter(new Adapter(this, apps));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view, apps.get(i).getPackageName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void login() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        } else {
            loadUserInfo(user);
        }
    }

    private void loadUserInfo(FirebaseUser user) {
        Toast.makeText(this, String.format("Usuario logado - %s (%s)", user.getDisplayName(), user.getEmail()), Toast.LENGTH_SHORT).show();
    }

    private AlertDialog enableNotificationListenerAlertDialog;
}
