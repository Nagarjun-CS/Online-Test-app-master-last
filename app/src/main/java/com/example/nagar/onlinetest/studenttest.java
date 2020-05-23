package com.example.nagar.onlinetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class studenttest extends AppCompatActivity {

    ImageView imageView3;
    TextView textView17;
    Button button6;
    Animation rounding;
    ProgressDialog progressDialog;
    //studentlogin S;
    int counter = -1;
    int inum;

    String stdusn;

    public void start(View view)
    {
        imageView3.startAnimation(rounding);

        final long millisinFuture= 10000;
        long delayTime=1000;

        new CountDownTimer(millisinFuture, delayTime) {

            public void onTick(long millisUntilFinished) {
                int h = (int) (millisUntilFinished / 1000) / 3600;
                int m = (int) ((millisUntilFinished / 1000) % 3600) / 60;
                int s = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted;
                if (h > 0) {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%d:%02d:%02d", h, m, s);
                } else {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%02d:%02d", m, s);
                }
                textView17.setText(timeLeftFormatted);
            }

            public void onFinish() {
                textView17.setText("done!");
                //button11.setEnabled(false);
                imageView3.getAnimation().cancel();
                imageView3.clearAnimation();
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenttest);

        imageView3 = (ImageView)findViewById(R.id.imageView3);
        textView17 = (TextView)findViewById(R.id.textView17);
        button6 = (Button)findViewById(R.id.button6);

        rounding = AnimationUtils.loadAnimation(this,R.anim.rounding);

        Intent intent = getIntent();
        stdusn = intent.getStringExtra("usn");



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        /*if(!(studentRepass.getText().toString().equals(studentPass.getText().toString()))){
            Toast.makeText(this, "Password did not match", Toast.LENGTH_SHORT).show();
            studentPass.setText("");
            studentRepass.setText("");
            progressDialog.dismiss();
            return;

        }*/
        /*if (testCode.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Test code is missing ", Toast.LENGTH_SHORT).show();

        }*/
        /*if (studentPass.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " USN is missing ", Toast.LENGTH_SHORT).show();
        }*/
        /*if (studentClass.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Class is missing ", Toast.LENGTH_SHORT).show();
        }
        if (studentEmail.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Email is missing ", Toast.LENGTH_SHORT).show();
        }*/
        Log.e("aaaaaaaaaaaaaa",stdusn);
        JsonObject jsonObject = new JsonObject();
        //jsonObject.addProperty("usn", studentUsn.getText().toString());
        //jsonObject.addProperty("name", studentName.getText().toString());
        //jsonObject.addProperty("email",studentEmail.getText().toString());
        jsonObject.addProperty("stdusn",stdusn);
        //jsonObject.addProperty("pass",studentPass.getText().toString());

        IRetrofitStudentTest jsonPostService = ServiceGenerator.createService(IRetrofitStudentTest.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
        Call<List<studentTestObject>> call = jsonPostService.postRawJSON(jsonObject);
        Log.e("call",jsonObject.toString());
        call.enqueue(new Callback<List<studentTestObject>>() {

            @Override
            public void onResponse(Call<List<studentTestObject>> call, Response<List<studentTestObject>> response) {
                try{
                    progressDialog.dismiss();

                    //Log.e("response-success", response.body().getName());
                    //Log.e("response-success", response.body().getStatus());
                    Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                        /*if(response.body().getStatus().equals("wrong")){
                            progressDialog.dismiss();
                            Toast.makeText(studenttest.this, "Wrong test code", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),student.class);
                            startActivity(intent);
                            //testCode.setText("");
                            //Toast.makeText(studenttest.this, "Wrong test code", Toast.LENGTH_SHORT).show();
                        }*/
                    List<studentTestObject> list = response.body();
                    List<String> id = new ArrayList<>();
                    List<String> a = new ArrayList<>();
                    List<String> b = new ArrayList<>();
                    List<String> c = new ArrayList<>();
                    List<String> d = new ArrayList<>();
                    List<String> qns = new ArrayList<>();
                    List<String> des = new ArrayList<>();



                    for(studentTestObject i : list )
                    {
                        //counter++;
                        id.add(i.getId());
                        //Log.e("wwwwwwwwwwwwwww",i.getId());
                        if(id.get(0).equals("0000") )
                        {
                            Toast.makeText(studenttest.this, "Currently no test available", Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(getApplicationContext(),studenttest.class);
                            //intent.putExtra("usn",stdusn);
                            //startActivity(intent);
                            return;
                        }
                        if ((inum = Integer.parseInt(i.getId()))<1850)
                        {
                            des.add(i.getQns());
                            Log.e("dessssssssssss",des.get(0));
                        }
                        else
                        {
                            a.add(i.getA());
                            b.add(i.getB());
                            c.add(i.getC());
                            d.add(i.getD());
                            qns.add(i.getQns());
                        }


                        Log.e("qns",i.getQns());


                    }



                    // Toast.makeText(student.this, "Already Registred", Toast.LENGTH_SHORT).show();




                }catch (Exception e){
                    Log.i("eeeeeeeee","5555555555555");

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<studentTestObject>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(studenttest.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                Log.e("response-success", "22222222222222222222222222");
                Log.e("response-failure", call.toString());
            }

        });






    }
}
