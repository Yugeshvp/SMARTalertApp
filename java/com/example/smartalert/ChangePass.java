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


public class ChangePass extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView t1,t2,t3,t4,t5;
    Button b1;
    dbHelper db;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("Language", "");
        if (lang.equals("null") || lang.isEmpty())
        {
            lang = "English";
        }

        db = new dbHelper(this);
        e1 = (EditText)findViewById(R.id.unameCP);
        e2 = (EditText)findViewById(R.id.oldpass);
        e3 = (EditText)findViewById(R.id.newpass);
        e4 = (EditText)findViewById(R.id.cnewpass);
        b1 = (Button)findViewById(R.id.chgpass);
        t1 = (TextView)findViewById(R.id.change1);
        t2 = (TextView)findViewById(R.id.change2);
        t3 = (TextView)findViewById(R.id.change3);
        t4 = (TextView)findViewById(R.id.change4);
        t5 = (TextView)findViewById(R.id.change5);

        languageCheck();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                          //Password Change Checks (validity of creds + if passwords match)
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                Boolean validcreds = db.validcreds(s1, s2);

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                    if(lang.equals("தமிழ்"))
                    {
                        Toast.makeText(getApplicationContext(), "காலியாக உள்ள இடத்தை நிறப்புக.", Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("English"))
                    {
                        Toast.makeText(getApplicationContext(),"Please fill the empty fields.",Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("Nederlands"))
                    {
                        Toast.makeText(getApplicationContext(),"Gelieve de lege velden in te vullen.",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(validcreds==false){
                    if(lang.equals("தமிழ்"))
                    {
                        Toast.makeText(getApplicationContext(), "உங்கள் நடப்புக் கணக்கின் சான்றுகள் தவறாக உள்ளன. தயவுசெய்து மீண்டும் முயற்சி செய்க.", Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("English"))
                    {
                        Toast.makeText(getApplicationContext(), "The credentials of your current account are wrong. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("Nederlands"))
                    {
                        Toast.makeText(getApplicationContext(), "De inloggegevens van uw huidige account zijn onjuist. Probeer het a.u.b. opnieuw.", Toast.LENGTH_SHORT).show();
                    }

                }
                else if(s3.equals(s4))
                {
                    Boolean changePass = db.changePass(s1,s2,s3);
                    if (changePass==true)
                    {
                        if(lang.equals("தமிழ்"))
                        {
                            Toast.makeText(getApplicationContext(),"கடவுச்சொல் மாற்றப்பட்டது.",Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("English"))
                        {
                            Toast.makeText(getApplicationContext(),"Password Change Successful.",Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("Nederlands"))
                        {
                            Toast.makeText(getApplicationContext(),"Wachtwoordwijziging geslaagd.",Toast.LENGTH_SHORT).show();
                        }
                        Intent i = new Intent(ChangePass.this,Login.class);
                        startActivity(i);
                    }
                    else
                    {
                        if(lang.equals("தமிழ்"))
                        {
                            Toast.makeText(getApplicationContext(),"கடவுச்சொல்லை மாற்ற முடியவில்லை.",Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("English"))
                        {
                            Toast.makeText(getApplicationContext(),"Password Change Failed.",Toast.LENGTH_SHORT).show();
                        }
                        else if(lang.equals("Nederlands"))
                        {
                            Toast.makeText(getApplicationContext(),"Veranderen van wachtwoord mislukt.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    if(lang.equals("தமிழ்"))
                    {
                        Toast.makeText(getApplicationContext(),"புதிய கடவுச்சொல் பொருந்தவில்லை.",Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("English"))
                    {
                        Toast.makeText(getApplicationContext(),"New passwords do not match.",Toast.LENGTH_SHORT).show();
                    }
                    else if(lang.equals("Nederlands"))
                    {
                        Toast.makeText(getApplicationContext(),"Nieuwe wachtwoorden komen niet overeen.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {  //Checking the language
        super.onResume();
        languageCheck();
    }

    private void languageCheck()    //UI Language Management
    {
        if(lang.equals("தமிழ்"))
        {
            t1.setText ("கடவுச்சொல்லை மாற்ற தங்களது தற்போதைய அக்கவுண்ட் விவரங்களை மற்றும் புதிய கடவுச்சொல்லை பதிவு செய்.");
            t2.setText ("பெயர்:");
            t3.setText ("முந்தைய கடவுச்சொல்:");
            t4.setText ("புதிய கடவுச்சொல்:");
            t5.setText ("உறுதியான புதிய கடவுச்சொல்லை எழுது:");
            e1.setHint ("பெயரை எழுது");
            e2.setHint ("முந்தைய கடவுச்சொல்லை எழுது");
            e3.setHint ("புதிய கடவுச்சொல்லை எழுது");
            e4.setHint ("மீண்டும் புதிய கடவுச்சொல்லை எழுது");
            b1.setText ("கடவுச்சொல்லை மாற்று");
        }
        else if(lang.equals("English"))
        {
            t1.setText("To change your password, please fill in your current account's credentials and the new password you wish to set.");
            t2.setText("Username:");
            t3.setText("Old Password:");
            t4.setText("New Password:");
            t5.setText("Confirm New Password:");
            e1.setHint("type your username here");
            e2.setHint("type your old password here");
            e3.setHint("type your new password here");
            e4.setHint("retype your new password here");
            b1.setText("Change Your Password");
        }
        else if(lang.equals("Nederlands"))
        {
            t1.setText ("Om uw wachtwoord te wijzigen, vult u de inloggegevens van uw huidige account in en het nieuwe wachtwoord dat u wilt instellen.");
            t2.setText ("Gebruikersnaam:");
            t3.setText ("Oud wachtwoord:");
            t4.setText ("Nieuw wachtwoord:");
            t5.setText ("Bevestig nieuw wachtwoord:");
            e1.setHint ("typ hier uw gebruikersnaam");
            e2.setHint ("typ hier uw oude wachtwoord");
            e3.setHint ("typ hier uw nieuwe wachtwoord");
            e4.setHint ("typ hier uw nieuwe wachtwoord opnieuw");
            b1.setText ("Verander uw wachtwoord");
        }
    }
}
