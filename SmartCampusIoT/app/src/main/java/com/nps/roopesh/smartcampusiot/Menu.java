package com.nps.roopesh.smartcampusiot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Menu extends AppCompatActivity {

    RadioButton radioButton1,radioButton2,radioButton3;
    Button proceed_with_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        radioButton1=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        proceed_with_choice=(Button)findViewById(R.id.proceed_with_choice);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(radioButton2.isChecked()){
                    //radioButton2.toggle();
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);

                //}
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radioButton1.setChecked(false);
                radioButton3.setChecked(false);

//                if(radioButton1.isChecked()){
//                    //radioButton1.toggle();
//                    radioButton1.setChecked(false);
//                }
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
            }
        });


        proceed_with_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton1.isChecked()){
                    startActivity(new Intent(getApplicationContext(),QrcodeActivity1.class));
                }else if(radioButton2.isChecked()){
//                    startActivity(new Intent(getApplicationContext(),AuctionResult.class));
                }else{
//                    startActivity(new Intent(getApplicationContext(),UpdateWeight.class));
                    Menu.this.finish();
                }
            }
        });



    }
}
