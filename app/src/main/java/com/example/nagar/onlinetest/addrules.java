package com.example.nagar.onlinetest;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class addrules extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    EditText editText15;
    EditText editText16;
    EditText editText19;
    EditText editText17;
    int p=0;



    public void publish(View view)
    {
        String name = editText19.getText().toString();
        String code = editText17.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, " Test name is missing ", Toast.LENGTH_SHORT).show();
        }
        if (code.isEmpty()) {
            Toast.makeText(this, " Test code is missing ", Toast.LENGTH_SHORT).show();
        }
        if (!name.isEmpty() && !code.isEmpty())
        {
            Toast.makeText(this, " Rules are added ", Toast.LENGTH_SHORT).show();
            editText19.setText("");
            editText17.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrules);

        editText15 = (EditText)findViewById(R.id.editText15);
        editText16 = (EditText)findViewById(R.id.editText16);
        editText19 = (EditText)findViewById(R.id.editText19);
        editText17 = (EditText)findViewById(R.id.editText17);

        editText15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=1;
                android.support.v4.app.DialogFragment timePicker  = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Timer Picker");
            }
        });

        editText16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=0;
                android.support.v4.app.DialogFragment timePicker  = new TimePickerFragment1();
                timePicker.show(getSupportFragmentManager(),"Timer Picker1");
            }
        });



    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if(p==1)
        {
            editText15.setText("Hour : "+  i  + " Minute : " + i1 );
        }
       if(p==0)
        {
            editText16.setText("Hour : "+  i  + " Minute : " + i1 );
        }

    }




}
