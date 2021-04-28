package com.example.alab5;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Galery extends Fragment
{
    public static int mojpls = 0;
    public static RecyclerView recyclerView;
    public static ArrayList<Item> items = new ArrayList<Item>();

    public static String titagada1;
    public static Uri imageUri1;
    public static String infagada1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_galery, container, false);
        Button AddButton = (Button) view.findViewById(R.id.Add);

        recyclerView = view.findViewById(R.id.Recycler);
        Galery g = new Galery();
        recyclerView.setLayoutManager(new LinearLayoutManager(g.getContext()));

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mojpls == 0)
                {
                    mojpls = 1;
                    Fragment fragment = null;
                    fragment = new AddPhoto();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.add(R.id.fr_dadd, fragment);
                    ft.commit();
                    MainActivity.whatscene = 2;
                }
            }
        });
        items.clear();
        ReadLine();
        return view;
    }

    public static void AddItemPLS(String titleda, Uri imageda, String infda)
    {
        items.add(new Item(titleda,infda, imageda));
        ItemAdapter itemAdapter = new ItemAdapter(items);
        recyclerView.setAdapter(itemAdapter);
    }

    public static void ReadLine()
    {
        try
        {
            //
            File file = new File("/data/data/com.example.alab5/files/Save.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            //
            while (line != null)
            {
                line = Vizener.decrypt(line);
                if (line.length() <= 14)
                {
                    titagada1 = line;
                    File estli = new File("/data/data/com.example.alab5/files/" + titagada1 + ".png");
                    if (estli.exists())
                    {
                        imageUri1 = Uri.parse("file:////data/data/com.example.alab5/files/" + titagada1 + ".png");
                        line = reader.readLine();
                        line = Vizener.decrypt(line);
                        infagada1 = line;
                        AddItemPLS(titagada1, imageUri1, infagada1);
                    }
                }
                line = reader.readLine();
            }
        }
        catch (IOException ex)
        {
            Log.d("File Open Error2", ex.toString());
        }
    }
}
