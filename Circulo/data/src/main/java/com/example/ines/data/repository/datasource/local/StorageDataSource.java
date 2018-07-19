package com.example.ines.data.repository.datasource.local;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.ines.data.repository.datasource.ReadWriteDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

public class StorageDataSource implements ReadWriteDataSource {

    private Context context;
    private String EXTERNAL_DIRECTORY = "CirculoApp";
    private String PATH = Environment.getExternalStorageDirectory().toString();

    @Inject
    StorageDataSource(Context context) {
        this.context = context;
    }

    @Override
    public List<String> getCirculos() throws IOException {
        String [] list = context.fileList();
        List<String> output = null;
        for (String i : list) output.add(i);
        return output;
    }

    @Override
    public void saveCirculo(String date, String name, String content) throws IOException {
        String fileName = date + "_" + name + ".txt";
        writeInternalStorage(fileName, content);
    }

    @Override
    public void newCirculo(String date, String name) throws IOException {
        File file = new File(context.getFilesDir(), date + "_" + name+".txt");
        String content = "<comentario><texto></texto></comentario>" +
                "<norma><texto></texto></norma>" +
                "<charla><texto></texto></charla>" +
                "<tertulia><texto></texto></tertulia>";
        writeInternalStorage(date+"_"+name, content);
    }

    @Override
    public Boolean existsCirculo(String date, String name) throws IOException {
        List<String> list = getCirculos();
        for ( String i : list) {
            if (i.contains(date+"_"+name)) return true;
        }
        return false;
    }

    /**
     * This function needs to:
     * - On external directory:
     *  - If directory does not exist, create it
     *  - If subdirectory type does not exist, create it
     *  - If subdirectory date does not exists, create it
     *  - If topic file does not exist, create it
     *  - Save text on file
     *  CirculoApp > name > date > topic_name_date.txt
     * - On internal directory:
     *  - If file does not exist, create it (date_type.txt)
     *  - Add to file the <text>direction</text> with the access to the text file
     */
    @Override
    public void editText(String date, String name, String topic, String text) throws IOException {
        String externalFileName = topic + "_" + name + "_" + date + ".txt";
        String internalFileName = date + "_" + name + ".txt";

        //Eternal storage
        File appDirectory = new File(PATH + "/" + EXTERNAL_DIRECTORY);
        appDirectory.mkdirs();
        appDirectory = new File(PATH + "/" + EXTERNAL_DIRECTORY + "/" + name);
        appDirectory.mkdirs();
        appDirectory = new File(PATH + "/" + EXTERNAL_DIRECTORY + "/" + name + "/" + date);
        appDirectory.mkdirs();

        File extFile = new File(appDirectory, externalFileName);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(extFile);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Internal Storage
        String content = readFile(internalFileName);

        String [] first = content.split("<" + topic + "><texto>");
        content = first[0] + "<" + topic + "><texto>" +
                PATH + "/" + EXTERNAL_DIRECTORY + "/" + name + "/" + date + internalFileName +
                first[1];
        writeInternalStorage(internalFileName, content);

    }

    private void writeInternalStorage(String internalFileName, String content) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(internalFileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getCirculo(String date, String name) throws IOException {
        return readFile (date + "_" + name + ".txt");
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder("");
        try {
            FileInputStream file = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readString = bufferedReader.readLine();
            while (readString != null) {
                content.append(readString);
                readString = bufferedReader.readLine();
            }
            inputStreamReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void removeCirculo(String date, String name) throws IOException {
        context.deleteFile(date+"_"+name+".txt");
    }
}
