package com.nps.roopesh.smartcampusiot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    EditText username;
    EditText password;
    Button AddUserButton,SignInButton,login_anyway;
    static String email;
    Boolean SigninButtonClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        SignInButton=(Button)findViewById(R.id.SignInButton);
        login_anyway = (Button)findViewById(R.id.login_anyway);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_text;
                String password_text;
                username_text=username.getText().toString();
                password_text=password.getText().toString();

                if(TextUtils.isEmpty(username_text)){
                    show_message("Username can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(password_text)){
                    show_message("Password can't be empty");
                    return;
                }

                if(!SigninButtonClicked) {
                    login(username_text, password_text);
                }

            }
        });

        login_anyway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signin.this,QrcodeActivity1.class);
                startActivity(i);
                Signin.this.finish();
            }
        });

    }

    void show_message(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    private void login(final String username_text, String password_text){

    }

    void display_message(AlertDialog.Builder builder, String title, String msg){


        builder.setTitle(title).setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
}