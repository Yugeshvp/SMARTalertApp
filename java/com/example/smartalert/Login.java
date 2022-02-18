package com.example.smartalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    TextView t1,t2,t3,t4;
    dbHelper db;
    public static String unamewel;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("Language", "");
        if (lang.equals("null") || lang.isEmpty())
        {
            lang = "English";
        }

        db = new dbHelper(this);
        e1 = (EditText) findViewById(R.id.unameL);
        e2 = (EditText) findViewById(R.id.passL);
        b1 = (Button) findViewById(R.id.login);
        b2 = (Button) findViewById(R.id.gochgpass);
        t1 = (TextView)findViewById(R.id.log1);
        t2 = (TextView)findViewById(R.id.log2);
        t3 = (TextView)findViewById(R.id.log3);
        t4 = (TextView)findViewById(R.id.log4);

        languageCheck();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                    //Login Authentication Process
                String unameL = e1.getText().toString();
                String passL = e2.getText().toString();
                Boolean validcreds = db.validcreds(unameL, passL);
                if (unameL.equals("") || passL.equals("")) {
                    if(lang.equals("தமிழ்"))
                    {
                        Toast.makeText(getApplicationContext(), "காலியாக உள்ள இடத்தை நிறப்புக.", Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("English"))
                    {
                        Toast.makeText(getApplicationContext(), "Please fill the empty fields.", Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("Nederlands"))
                    {
                        Toast.makeText(getApplicationContext(), "Gelieve de lege velden in te vullen.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (validcreds == true) {
                        if(lang.equals("தமிழ்"))
                        {
                            Toast.makeText(getApplicationContext(), "உள்ளே நுழைந்தது", Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("English"))
                        {
                            Toast.makeText(getApplicationContext(), "Logged in successfully.", Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("Nederlands"))
                        {
                            Toast.makeText(getApplicationContext(), "Succesvol ingelogd.", Toast.LENGTH_SHORT).show();
                        }
                        e1.setText(""); //clearing the credentials on log in
                        e2.setText("");
                        unamewel = unameL;
                        Intent i = new Intent(Login.this, Menu.class); //going to the menu activity
                        finish();
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {
                        if(lang.equals("தமிழ்"))
                        {
                            Toast.makeText(getApplicationContext(), "தவறானது, மீண்டும் முயற்சி செய்க.", Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("English"))
                        {
                            Toast.makeText(getApplicationContext(), "Wrong Credentials. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("Nederlands"))
                        {
                            Toast.makeText(getApplicationContext(), "Verkeerde inloggegevens. Probeer het a.u.b. opnieuw.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ChangePass.class); //going to the ChangePass activity
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        languageCheck();  //Checking the language
    }

    private void languageCheck()   //UI Language Management
    {
        if(lang.equals("தமிழ்"))
        {
            t1.setText("வெல்கம் டூ ஸ்மார்ட் அலர்ட் ஆப் - SmartAlert!");
            t2.setText("மேலும் தொடர, புதிய அக்கவுன்டை உருவாக்கு:");
            t3.setText("பெயர்:");
            t4.setText("கடவுச்சொல்:");
            e1.setHint ("பெயரை எழுது");
            e2.setHint ("கடவுச்சொல்லை எழுது");
            b1.setText ("உள்ளே நுழை");
            b2.setText ("கடவுச்சொல்லை மாற்று");
        }
        else if(lang.equals("English"))
        {
            t1.setText("Welcome to the SmartAlert App!");
            t2.setText("To enter the app, please fill in your account's credentials:");
            t3.setText("Username:");
            t4.setText("Password:");
            e1.setHint("type your username here");
            e2.setHint("type your password here");
            b1.setText("Login");
            b2.setText("Change Password");
        }
        else if(lang.equals("Nederlands"))
        {
            t1.setText ("Welkom bij de SmartAlert-app!");
            t2.setText ("Om toegang te krijgen tot de app, vult u de inloggegevens van uw account in:");
            t3.setText ("Gebruikersnaam:");
            t4.setText ("Wachtwoord:");
            e1.setHint ("typ hier uw gebruikersnaam");
            e2.setHint ("typ hier uw wachtwoord");
            b1.setText ("Inloggen");
            b2.setText ("Wachtwoord wijzigen");
        }
    }
}
