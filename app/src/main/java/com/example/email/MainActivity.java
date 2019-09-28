package com.example.email;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    protected void onStop() {
        Log.i("STOP","STOP");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i("Restart","ReStart");
        super.onRestart();
    }
    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send=this.findViewById(R.id.send);
        Button clear=this.findViewById(R.id.clear);

        final EditText to =this.findViewById(R.id.toInformation);
        final EditText from =this.findViewById(R.id.fromInformation);
        final EditText cc =this.findViewById(R.id.ccInformation);
        final EditText bcc =this.findViewById(R.id.bccInformation);
        final EditText subject =this.findViewById(R.id.subject);
        final EditText content =this.findViewById(R.id.content);

        SharedPreferences sharedPreferences=this.getSharedPreferences("email",MODE_PRIVATE);

        String past_from=sharedPreferences.getString("from","");
        from.setText(past_from);

        String past_to=sharedPreferences.getString("to","");
        from.setText(past_to);

        String past_cc=sharedPreferences.getString("cc","");
        from.setText(past_cc);

        String past_bcc=sharedPreferences.getString("bcc","");
        from.setText(past_bcc);

        String past_subject=sharedPreferences.getString("subject","");
        from.setText(past_subject);

        String past_content=sharedPreferences.getString("content","");
        from.setText(past_content);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
