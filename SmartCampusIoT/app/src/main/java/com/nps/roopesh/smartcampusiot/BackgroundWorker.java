package com.nps.roopesh.smartcampusiot;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by roopesh on 6/4/18.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {


    Context context;
    Signin signin;
    String output = "";
    String request_type;

    static String baseurl="http://192.168.1.84:8008/";
    BackgroundWorker(Context ctx){
        context=ctx;
    }

    BackgroundWorker(Context ctx, Signin signin){
        context = ctx;
        this.signin = signin;
    }



    @Override
    protected String doInBackground(String... params) {
        String ipAddress=params[0];
        //baseurl = "http://" + ipAddress;
        request_type = params[0];



        if(request_type == "login"){
            String username = params[1];
            String password = params[2];
            try{

                URL url=new URL(baseurl + "login.php");

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTf-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");
                //post data is username and password
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                //Toast.makeText(context,httpURLConnection.getResponseCode() + "--" , Toast.LENGTH_LONG).show();

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                String line="",output1="";

                int j=0;
                while((line=bufferedReader.readLine())!=null){
                    if(request_type == "login"){
//                        Toast.makeText(context, "Done" + j, Toast.LENGTH_LONG).show();
                            output += line;
                    }
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();




            }catch(MalformedURLException e){
//                output += e.getMessage() + "some excpt" + e.toString();
                e.printStackTrace();
            }catch (IOException e){
//                output += e.getMessage() + "some excpt" + e.toString();
                e.printStackTrace();
            }


        }




        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(request_type.contains("login")){
            if(output.contains("correct")){
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show();
                Intent i = new Intent(signin,Menu.class);
                context.startActivity(i);
                signin.finish();
            }else if (output.contains("wrong")){
                Toast.makeText(context, "Incorrect username/password", Toast.LENGTH_LONG).show();

            }
        }


    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
