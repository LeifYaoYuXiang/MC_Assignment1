package com.example.email;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String toInformation;
    private String fromInformation;
    private String ccInformation;
    private String bccInformation;
    private String subjectInformation;
    private String contentInformation;


    @Override
    protected void onDestroy() {
        /*Store the input data*/
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("from",fromInformation);
        editor.putString("to",toInformation);
        editor.putString("cc",ccInformation);
        editor.putString("bcc",bccInformation);
        editor.putString("subject",subjectInformation);
        editor.putString("content",contentInformation);
        editor.commit();

        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*bind the widget to variable*/
        final Button send=this.findViewById(R.id.send);
        final Button clear=this.findViewById(R.id.clear);
        final EditText to =this.findViewById(R.id.toInformation);
        final EditText from =this.findViewById(R.id.fromInformation);
        final EditText cc =this.findViewById(R.id.ccInformation);
        final EditText bcc =this.findViewById(R.id.bccInformation);
        final EditText subject =this.findViewById(R.id.subject);
        final EditText content =this.findViewById(R.id.content);

        /*initialize variables*/
        this.fromInformation="";
        this.toInformation="";
        this.ccInformation="";
        this.bccInformation="";
        this.subjectInformation="";
        this.contentInformation="";

        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        String past_from=sharedPreferences.getString("from","");
        from.setText(past_from);
        String past_to=sharedPreferences.getString("to","");
        to.setText(past_to);
        String past_cc=sharedPreferences.getString("cc","");
        cc.setText(past_cc);
        String past_bcc=sharedPreferences.getString("bcc","");
        bcc.setText(past_bcc);
        String past_subject=sharedPreferences.getString("subject","");
        subject.setText(past_subject);
        String past_content=sharedPreferences.getString("content","");
        content.setText(past_content);

        /*add listeners to edit_text*/
        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fromInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                toInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ccInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bcc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              bccInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subjectInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contentInformation=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Ready to jump to the second activity*/
                toInformation=to.getText().toString()+"";
                fromInformation=from.getText().toString()+"";
                ccInformation=cc.getText().toString();
                bccInformation=bcc.getText().toString();
                subjectInformation=subject.getText().toString();
                contentInformation=content.getText().toString();

                if(toInformation.equals("") || fromInformation.equals("")){
                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
                    normalDialog.setTitle("Warning!");
                    normalDialog.setMessage("Please input sender email and/or receiver email");
                    normalDialog.show();
                }else{
                    Intent intent=new Intent(MainActivity.this,PreviewActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("from",fromInformation);
                    bundle.putString("to",toInformation);
                    bundle.putString("cc",ccInformation);
                    bundle.putString("bcc",bccInformation);
                    bundle.putString("subject",subjectInformation);
                    bundle.putString("content",contentInformation);
                    intent.putExtras(bundle);

                    SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putString("from","");
                    editor.putString("to","");
                    editor.putString("cc","");
                    editor.putString("bcc","");
                    editor.putString("subject","");
                    editor.putString("content","");
                    editor.commit();

                    startActivity(intent);
                }



            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from.setText("");
                to.setText("");
                cc.setText("");
                bcc.setText("");
                subject.setText("");
                content.setText("");
            }
        });



    }
}
