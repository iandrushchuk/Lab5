package com.example.alab5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;

import com.squareup.picasso.Picasso;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static android.content.Context.MODE_PRIVATE;

public class AddPhoto extends Fragment
{
    public static String unicName;
    public static String MY_FILE = "Save.txt";
    public static String MY_FILE_BEZ_SHIFR = "SaveShifr.txt";

    ImageView photo;
    Uri imageUri;
    EditText titaga;
    EditText infaga;

    public static Bitmap bitmap;

    public static int mojno=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_photo, container, false);

        photo = view.findViewById(R.id.Kartinka);
        titaga = view.findViewById(R.id.TitleAga);
        infaga = view.findViewById(R.id.InfoAga);

        Button CloseButton = (Button) view.findViewById(R.id.Close);
        Button AddButton = (Button) view.findViewById(R.id.Add_Image);
        ImageView Addp = (ImageView) view.findViewById(R.id.Kartinka);

        Addp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                startActivityForResult(intent, 0);
            }

        });

        CloseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fr_dadd);
                fm.beginTransaction() .remove(fragment) .commit();
                Galery.mojpls = 0;
                MainActivity.whatscene = 1;
            }

        });

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mojno == 1 && titaga.getText().toString().equals("") != true && infaga.getText().toString().equals("") != true)
                {
                    String titagada = titaga.getText().toString();
                    String infagada = infaga.getText().toString();

                    unicName = titagada;
                    textPls(titagada, infagada);
                    ShifrLine(titagada, infagada);

                    Galery.AddItemPLS(titagada, imageUri, infagada);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fr_dadd);
                    fm.beginTransaction().remove(fragment).commit();
                    mojno = 0;
                    Galery.mojpls = 0;
                    MainActivity.whatscene = 1;
                }
            }

        });

        return view;
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode)
            {
                case 0:
                    imageUri = data.getData();
                    photo.setImageURI(imageUri);
                    mojno = 1;
                    bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
                    break;
            }
    }

    public void textPls(String title, String infpls)
    {
        FileOutputStream fos = null;
        try
        {
            fos = getContext().openFileOutput(unicName+".png", MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
        }
        catch (IOException ex)
        {
            Log.d("File Open Error", ex.toString());
        }
        finally
        {
            try
            {
                if (fos != null) fos.close();
            }
            catch (IOException ex)
            {
                Log.d("File Close Error", ex.toString());
            }
        }
        try
        {
            fos = getContext().openFileOutput(MY_FILE, Context.MODE_APPEND);
            fos.write(Vizener.encrypt(title).getBytes());
            fos.write("\n".getBytes());
            fos.write(Vizener.encrypt(infpls).getBytes());
            fos.write("\n".getBytes());
        }
        catch (IOException ex)
        {
            Log.d("File Open Error", ex.toString());
        }
        finally
        {
            try
            {
                if (fos != null) fos.close();
            }
            catch (IOException ex)
            {
                Log.d("File Close Error", ex.toString());
            }
        }
    }

    public void ShifrLine(String title, String infpls)
    {
        FileOutputStream fos = null;
        try
        {
            fos = getContext().openFileOutput(MY_FILE_BEZ_SHIFR, Context.MODE_APPEND);
            fos.write(title.getBytes());
            fos.write("\n".getBytes());
            fos.write(infpls.getBytes());
            fos.write("\n".getBytes());
        }
        catch (IOException ex)
        {
            Log.d("File Open Error", ex.toString());
        }
        finally
        {
            try
            {
                if (fos != null) fos.close();
            }
            catch (IOException ex)
            {
                Log.d("File Close Error", ex.toString());
            }
        }
    }
}