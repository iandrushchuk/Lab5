package com.example.alab5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class LogIn extends Fragment
{

    String nam;
    String log;
    String pas;

    public static String nam4 = "0";
    public static String log4 = "Login";
    public static String pas4 = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        Button Sign1Button = (Button) view.findViewById(R.id.Siup1);
        Button EnterButton = (Button) view.findViewById(R.id.Entr1);

        Sign1Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                fragment = new Register();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_placee, fragment);
                ft.commit();
                MainActivity.whatscene = 1;
                //getFragmentManager().beginTransaction().replace(R.id.fr_place, new Register()).commit();
            }
        });

        EnterButton.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText loga = (EditText) getView().findViewById(R.id.Log1);
                EditText pasa = (EditText) getView().findViewById(R.id.Pas1);
                if (loga.getText().toString().equals(""))
                {
                    loga.setText("Логин Не Введен");
                    pasa.setText("");
                }
                else
                {
                    if (pasa.getText().toString().equals(""))
                    {
                        loga.setText("Пароль Не Введен");
                        pasa.setText("");
                    }
                    else
                    {
                        if (loga.getText().toString().equals(log4))
                        {
                            if (pasa.getText().toString().equals(pas4))
                            {
                                Fragment fragment = null;
                                fragment = new Galery();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.fr_placee, fragment);
                                ft.commit();
                                MainActivity.whatscene = 1;
                            }
                            else
                            {
                                loga.setText("Не Верный Пароль");
                                pasa.setText("");
                            }
                        }
                        else
                        {
                            loga.setText("Учетной Записи Нет");
                            pasa.setText("");
                        }
                    }
                }
            }
        }));

        return view;
        //return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    //public void Sign1(View view)
    //{

    //}

    //public void Entr(View view)
    //{

    //}

}
