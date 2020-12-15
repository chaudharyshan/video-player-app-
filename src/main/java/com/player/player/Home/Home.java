package com.player.player.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;



import com.player.player.R;

import com.player.player.VideoFile;
import com.player.player.fragment.HomeFragment;
import com.player.player.fragment.infoFragment;
import com.player.player.fragment.searchFragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    private static final int REQUEST_CODE_PERMISSION =123 ;
    ChipNavigationBar chipNavigationBar;
    public static ArrayList<VideoFile> videoFiles = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        permission();


        chipNavigationBar = findViewById(R.id.BottomNavigationView);
        chipNavigationBar.setItemSelected(R.id.homeFragment,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new HomeFragment()).commit();
        bottomMenu();
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Home.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
        }
        else
        {
            videoFiles=getAllVideos(this);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE_PERMISSION)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                videoFiles=getAllVideos(this);

            }
            else
            {
                ActivityCompat.requestPermissions(Home.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
            }
        }


    }

    private void bottomMenu() {


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.homeFragment:
                        fragment = new HomeFragment();
                        break;
                    case R.id.searchFragment:
                        fragment = new searchFragment();
                        break;
                    case R.id.infoFragment:
                        fragment = new infoFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,fragment).commit();
            }
        });









    }

    public ArrayList<VideoFile> getAllVideos(Context context)
    {
        ArrayList<VideoFile> tempVideofiles = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
          MediaStore.Video.Media._ID,
          MediaStore.Video.Media.DATA,
          MediaStore.Video.Media.TITLE,
          MediaStore.Video.Media.SIZE,
          MediaStore.Video.Media.DATE_ADDED,
          MediaStore.Video.Media.DURATION,
          MediaStore.Video.Media.DISPLAY_NAME
        };
        Cursor cursor = context.getContentResolver().query(uri,projection,
                null,null,null);
        if(cursor !=null){
            while (cursor.moveToNext())
            {
                String id =cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String size = cursor.getString(3);
                String dateAdded = cursor.getString(4);
                String duration = cursor.getString(5);
                String fileName = cursor.getString(6);
                VideoFile videoFiles = new VideoFile(id,path,title,fileName,size,dateAdded,duration);

                //jast check
                Log.e("path",path);
                tempVideofiles.add(videoFiles);

            }
            cursor.close();
        }
        return  tempVideofiles;
    }

}