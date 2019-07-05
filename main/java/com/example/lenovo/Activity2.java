package com.example.lenovo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mainwork.R;

import java.util.Timer;
import java.util.TimerTask;


public class Activity2 extends AppCompatActivity {


    private static EditText username;
    private static EditText password;
    Timer timer= new Timer();
    MyDbHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new MyDbHandler(this);

        final Context context = getApplicationContext();

//        int duration = Toast.LENGTH_LONG;
//        Toast toast = Toast.makeText(context,"hiiii", duration);
//        toast.show();
//remplir le tableau

        setContentView(R.layout.activity_2);
        final Button B2 = this.findViewById(R.id.B2);
        Button button2 = this.findViewById(R.id.button2);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.pass_word);

      /*  if (flag==true)
        {*/
            B2.setOnClickListener(new View.OnClickListener() {
   //             Cursor res;
                //boolean flag = true;
                int counter = 3;

                @Override
                public void onClick(View view) {

                    if(--counter==0) {
                        B2.setEnabled(false);

                        counter = 3;

                        timer.schedule(new TimerTask(){

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        B2.setEnabled(true);
                                    }
                                });

                            }
                        },5000);
                    }
                /*    String H = Global.md5(password.getText().toString());
                    Global.getInstance().setData(username.getText().toString());
                    res = myDb.getData();*/
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("ID :" + res.getString(0) + "\n");
//                    buffer.append("name :" + res.getString(1) + "\n");
//                    buffer.append("identification :" + res.getString(2) + "\n");
//                    buffer.append("password :" + res.getString(3) + "\n\n");
//                }


                  /*  String regex = "([a-zA-Z]+)";
                    Pattern pattern = Pattern.compile(regex);*/
                  //  Matcher matcher = pattern.matcher(username.getText());
               //     {
                        //condition for accepting
                     //   if (res.getCount()!=0 && matcher.find() && matcher.group(0).equals(username.getText().toString())) {
                           // if (matcher.find() && matcher.group(0).equals(username.getText().toString())) {

                            if (username.getText().toString().equals("user") && password.getText().toString().equals("123")) {
                                Intent intent3 = new Intent(getApplicationContext(), Activ.class);
                                startActivity(intent3);//

                              int duration = Toast.LENGTH_LONG;
                                Toast toast = Toast.makeText(context, "Welcome", duration);
                                toast.show();
                            }
                            else {
                              /*  Log.e("err", "username: " +username.getText().toString().equals(res.getString(1)));
                                Log.e("err", "H: " +  H);
                                Log.e("err", "res: " +  res.getString(0));*/

                                int duration = Toast.LENGTH_LONG;
                                Toast toast = Toast.makeText(context, "only use alphabetical chatachters " , duration);
                                toast.show();


                            }
                        //      }
                   /*     else{
                       /*     Log.e("err", "pattern: >>" + matcher.group(0).equals(username.getText().toString()) + "<<");
                            Log.e("err", "matcher: >>" + matcher.group(0).length()    + "<<");
                            Log.e("err", "matcher: >>" +username.getText().length()    + "<<");
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, "only use alphabetical chatachters " , duration);
                            toast.show();

                        }*/
                    }

           //     }

            });


            button2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.e("err", "nnnnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooooooo: ");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

            //    private void openActivity() {
            //        Intent intent;
            //        intent = new Intent(this, MainActivity.class);
            //        startActivity(intent);
            //
            //    }


            //    public void openActivity2() {
            //        Intent intent2 = new Intent(this, Activity_2.class);
            //        startActivity(intent2);
            //    }
        /*}
        else
            B2.setEnabled(false);*/

    }

}