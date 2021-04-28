package com.example.alab5;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import static android.content.Context.MODE_PRIVATE;


public class Cardochka extends Fragment
{

    public static ImageView card;
    public static TextView infa;
    public static TextView titla;

    public static int index;

    public static String inftext;
    public static String titltext;
    public static Uri carduri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cardochka, container, false);
        Button CloseButtonPLS = (Button) view.findViewById(R.id.closethis);

        CloseButtonPLS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fr_dadd);
                fm.beginTransaction() .remove(fragment) .commit();
                MainActivity.whatscene = 1;

                File file = new File ("/data/data/com.example.alab5/files/" + titltext + ".png");
                if(file.exists()) file.delete();

                Galery.items.remove(index);
                clearfile();
                int count;
                count = Galery.items.size();
                for (int i = 0; i < count; i++)
                {
                    textPls(Galery.items.get(i).getTitile(), Galery.items.get(i).getInfo());
                    ShifrLine(Galery.items.get(i).getTitile(), Galery.items.get(i).getInfo());
                }
                Galery.items.clear();
                Galery.ReadLine();
            }

        });

        card = view.findViewById(R.id.Kartinka);
        infa = view.findViewById(R.id.InfoAga);
        titla = view.findViewById(R.id.TitleAga);

        titla.setText(titltext);
        infa.setText(inftext);
        card.setImageURI(carduri);

        return  view;
    }

    public void clearfile()
    {
        FileOutputStream fos = null;

        try
        {
            fos = getContext().openFileOutput(AddPhoto.MY_FILE, MODE_PRIVATE);
            fos.write("".getBytes());
            fos = getContext().openFileOutput(AddPhoto.MY_FILE_BEZ_SHIFR, MODE_PRIVATE);
            fos.write("".getBytes());
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

    public void textPls(String title, String infpls)
    {
        FileOutputStream fos = null;
        try
        {
            fos = getContext().openFileOutput(AddPhoto.MY_FILE, Context.MODE_APPEND);
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
            fos = getContext().openFileOutput(AddPhoto.MY_FILE_BEZ_SHIFR, Context.MODE_APPEND);
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
