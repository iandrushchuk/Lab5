package com.example.alab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    public static int whatscene = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.fr_placee, new LogIn()).commit();
        }
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;
        switch (whatscene)
        {
            case 0:
                finish();
                break;
            case 1:
                fragment = new LogIn();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_placee, fragment);
                ft.commit();
                whatscene = 0;
                break;
            case 2:
                fragment = fm.findFragmentById(R.id.fr_dadd);
                fm.beginTransaction() .remove(fragment) .commit();
                Galery.mojpls = 0;
                whatscene = 1;
                break;
        }
    }
}