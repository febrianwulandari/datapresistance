package com.febrianwulandari.latihanstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namefile.txt";
    Button buat,ubah,baca,hapus;
    TextView tampilkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        /*mendeteksi lewat id */
        buat = findViewById(R.id.btnbuat);
        ubah = findViewById(R.id.btnbaca);
        baca = findViewById(R.id.btnubah);
        hapus = findViewById(R.id.btnhapus);
        tampilkan = findViewById(R.id.txtBaca);

        buat.setOnClickListener(this);
        ubah.setOnClickListener(this);
        baca.setOnClickListener(this);
        hapus.setOnClickListener(this);
    }
     void buat(){
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);

         FileOutputStream outputStream = null;
         try{
             file.createNewFile();
             outputStream = new FileOutputStream(file, true);
             outputStream.write(isiFile.getBytes());
             //dibersihkan streamnya
             outputStream.flush();
             //menutup streamnya
             outputStream.close();
         }
         catch (Exception e){
             e.printStackTrace();
         }
     }
     void ubah(){
         String Ubah = "Update Isi Data File Text";
         File file = new File(getFilesDir(), FILENAME);

         FileOutputStream outputStream = null;
         try{
             file.createNewFile();
             outputStream = new FileOutputStream(file, false);
             outputStream.write(Ubah.getBytes());
             //dibersihkan streamnya
             outputStream.flush();
             //menutup streamnya
             outputStream.close();
         }
         //cara 1  membuat catch
         catch (Exception e){
             e.printStackTrace();
         }
     }
     void baca(){
        File sdcard = getFilesDir();
        File file = new File(sdcard,FILENAME);

            if(file.exists()){
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    while (line != null){
                        text.append(line);
                        line = br.readLine();
                    }
                    br.close();
                }
                //cara 2 membuat catch
                catch (IOException e){
                    System.out.println("Error" + e.getMessage());
                }
                tampilkan.setText(text.toString());
            }

     }
     void hapus(){
        File file = new File(getFilesDir(),FILENAME);
        if(file.exists()){
            file.delete();
        }
     }

    //akan menjalankan sesuai void
     public void jalankanperintah(int id){
        switch (id){
            case R.id.btnbuat:
                buat();
                break;
            case R.id.btnubah:
                ubah();
                break;
            case R.id.btnbaca:
                baca();
                break;
            case R.id.btnhapus:
                hapus();
                break;
        }
     }
    @Override
    // v diambil berdasarkan (View v) yang ada di method onclick
    public void onClick(View v) {
        jalankanperintah(v.getId());
    }
}
