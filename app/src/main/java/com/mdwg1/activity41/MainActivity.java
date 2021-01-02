package com.mdwg1.activity41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private EditText dname, dpassword;
    private Button button;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dname = (EditText) findViewById(R.id.dname);
        dpassword = (EditText) findViewById(R.id.dpassword);
        button = (Button) findViewById(R.id.button);
        check = (CheckBox) findViewById(R.id.check);

        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPref.edit();

        checkSharedPreferences();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //enregistrement des pref de checkbox
                if (check.isChecked()) {
                    //définir checkbox lors l'app demarrer
                    mEditor.putString(getString(R.string.checkbox),"True") ;
                    mEditor.commit();

                    //enregistrement du nom
                    String name = dname.getText().toString();
                    mEditor.putString(getString(R.string.name),name) ;
                    mEditor.commit();

                    //enregistrement du password
                    String password  = dpassword.getText().toString();
                    mEditor.putString(getString(R.string.password),password) ;
                    mEditor.commit();


                }else {
                    mEditor.putString(getString(R.string.checkbox), "False") ;
                    mEditor.commit() ;

                    mEditor.putString(getString(R.string.name),"") ;
                    mEditor.commit();

                    mEditor.putString(getString(R.string.password),"");
                    mEditor.commit();
                }
            }
        });
    }

    private void checkSharedPreferences() {
        String checkbox = mPref.getString(getString(R.string.checkbox), "False");
        String name = mPref.getString(getString(R.string.name), "");
        String password = mPref.getString(getString(R.string.password), "");

        dname.setText(name) ;
        dpassword.setText(password);

        if (checkbox.equals("True")){
            check.setChecked(true);
        }else{
            check.setChecked(false);
        }
    }

}