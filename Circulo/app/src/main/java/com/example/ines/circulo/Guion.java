package com.example.ines.circulo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Guion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView init;
    Button e1, c1, s1, v1;
    Integer space1; //0: nothing, 1:edit, 2:load
    Integer space; //Identifier of space loading
    CirculosHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guion);
        setDrawer();
        initVariables();
        setTexts();
        setSpaces();
    }

    private void setSpaces() {
        //TODO: Repetir para todos los spaces
        Cursor c = helper.getParameter("estudiantes", "evangelio");
        if(c.moveToFirst()) {
            do{
                File file = new File(c.getString(c.getColumnIndex("evangelio")));
                //TODO: Warning! Code replicated
                StringBuilder new_text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        new_text.append(line);
                        new_text.append('\n');
                    }
                    br.close();
                    ((LinearLayout) findViewById(R.id.button_layout)).setVisibility(View.GONE);
                    ((LinearLayout) findViewById(R.id.edit_1)).setVisibility(View.GONE);
                    ((RelativeLayout) findViewById(R.id.doc_1)).setVisibility(View.VISIBLE);
                    ((LinearLayout) findViewById(R.id.buttons_edit)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.Text_1)).setText(new_text);
                }
                catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } while (c.moveToNext());
        }
    }

    private void setTexts() {
        //TODO: Repetir para todos los textos
        StringBuilder init_text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Inicio.html")));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                init_text.append(mLine);
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        init.setText(Html.fromHtml(init_text.toString()));
    }

    private void initVariables() {
        helper = new CirculosHelper(getApplicationContext());
        //TODO: Repetir configuración para cada espacio
        //TODO: *Adaptar los espacios con recycler view para añadir más de un elemento*

        /******************Configuración primer espacio ************************/
        space1 = 0;
        init = (TextView) findViewById(R.id.init_txt);
        e1 = (Button) findViewById(R.id.write_1);
        c1 = (Button) findViewById(R.id.load_1);
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LinearLayout) findViewById(R.id.button_layout)).setVisibility(View.GONE);
                ((RelativeLayout) findViewById(R.id.doc_1)).setVisibility(View.GONE);
                ((LinearLayout) findViewById(R.id.edit_1)).setVisibility(View.VISIBLE);
                ((LinearLayout) findViewById(R.id.buttons_edit)).setVisibility(View.VISIBLE);
                space1 = 1;
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.putExtra("space", 1);
                intent.setType("*/*");
                startActivityForResult(intent, 1);
                space1 = 2;
            }
        });
        s1 = (Button) findViewById(R.id.saveText_1);
        v1 = (Button) findViewById(R.id.erase_1);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: *Dar opción de editar texto cargado*

                String text;
                if(space1==1) text = ((EditText)findViewById(R.id.EditText_1)).getText().toString();
                else if (space1==2) text = ((TextView)findViewById(R.id.Text_1)).getText().toString();
                else return;
                //TODO: *Save on internal directory*
                String filename = "Estudianes_norma.txt";
                String path = Environment.getExternalStorageDirectory().toString();
                File appDirectory = new File(path + "/" + "Circulo");
                appDirectory.mkdirs();
                DateFormat df = new SimpleDateFormat("ddMMyyyy");
                String date = df.format(Calendar.getInstance().getTime());
                appDirectory = new File(path + "/" + "Circulo" + "/" + date);
                appDirectory.mkdirs();

                File file = new File(appDirectory, filename);
                FileOutputStream outputStream;
                try {
                    outputStream = new FileOutputStream(file);
                    outputStream.write(text.getBytes());
                    outputStream.close();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                helper.upgradeParameter("estudiantes", "evangelio", file.getAbsolutePath());
            }
        });
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Pop up para confirmar el vaciado
                ((EditText)findViewById(R.id.EditText_1)).setText("");
                ((RelativeLayout) findViewById(R.id.doc_1)).setVisibility(View.GONE);
                ((LinearLayout) findViewById(R.id.button_layout)).setVisibility(View.VISIBLE);
                ((LinearLayout) findViewById(R.id.buttons_edit)).setVisibility(View.GONE);
                ((LinearLayout) findViewById(R.id.edit_1)).setVisibility(View.GONE);
                space1 = 0;
            }
        });
        /**************************************************************************************/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //TODO: comprobar en que espacio se está cargando. Repetir para cada espacio
            //TODO: Read from docx (http://poi.apache.org/)
            
            Uri uri = data.getData();
            File file = null;
            try {
                file = new File(getFilePath(getApplicationContext(), uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            StringBuilder new_text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    new_text.append(line);
                    new_text.append('\n');
                }
                br.close();
                if(data.getExtras().getInt("space") == 1 ) {
                    ((LinearLayout) findViewById(R.id.button_layout)).setVisibility(View.GONE);
                    ((LinearLayout) findViewById(R.id.edit_1)).setVisibility(View.GONE);
                    ((RelativeLayout) findViewById(R.id.doc_1)).setVisibility(View.VISIBLE);
                    ((LinearLayout) findViewById(R.id.buttons_edit)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.Text_1)).setText(new_text);
                }
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private void setDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        //TODO: Pop up para confirmar
        //TODO: *Comprobar si hay algo editado sin guardar*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.raf) {

        } else if (id == R.id.mig) {

        } else if (id == R.id.gab) {

        } else if (id == R.id.coop) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
