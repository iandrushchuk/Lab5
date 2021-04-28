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


public class Register extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button ExitButton = (Button) view.findViewById(R.id.Back);
        Button ConfButton = (Button) view.findViewById(R.id.Conf);

        ExitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                fragment = new LogIn();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_placee, fragment);
                ft.commit();
                MainActivity.whatscene = 0;
            }
        });

        ConfButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText nam = (EditText) getView().findViewById(R.id.Nam);
                EditText logg = (EditText) getView().findViewById(R.id.Logg);
                EditText pas1 = (EditText) getView().findViewById(R.id.Pas1);
                EditText pas2 = (EditText) getView().findViewById(R.id.Paspov);
                if (nam.getText().toString().equals(""))
                {
                    nam.setText("Имя Не Введено");
                    logg.setText("");
                    pas1.setText("");
                    pas2.setText("");
                }
                else
                {
                    if (logg.getText().toString().equals(""))
                    {
                        nam.setText("Логин Не Введен");
                        logg.setText("");
                        pas1.setText("");
                        pas2.setText("");
                    }
                    else
                    {
                        if (pas1.getText().toString().equals(""))
                        {
                            nam.setText("Пароль Не Введен");
                            logg.setText("");
                            pas1.setText("");
                            pas2.setText("");
                        }
                        else
                        {
                            if (pas1.getText().toString().equals(pas2.getText().toString()))
                            {
                                LogIn.nam4 = nam.getText().toString();
                                LogIn.log4 = logg.getText().toString();
                                LogIn.pas4 = pas1.getText().toString();

                                nam.setText("Успешная Регистрация!");
                                logg.setText("");
                                pas1.setText("");
                                pas2.setText("");
                            }
                            else
                            {
                                nam.setText("Пароли Не Совпадают!");
                                logg.setText("");
                                pas1.setText("");
                                pas2.setText("");
                            }
                        }
                    }
                }
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
