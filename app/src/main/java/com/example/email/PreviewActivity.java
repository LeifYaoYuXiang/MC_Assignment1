package com.example.email;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String from=bundle.getString("from");
        final String to=bundle.getString("to");
        final String cc=bundle.getString("cc");
        final String bcc=bundle.getString("bcc");
        final String subject=bundle.getString("subject");
        final String content=bundle.getString("content");


        TextView fromText=findViewById(R.id.pre_fromInformation);
        fromText.setText(from);

        TextView toText=findViewById(R.id.pre_toInformation);
        toText.setText(to);

        TextView ccText=findViewById(R.id.pre_ccInformation);
        ccText.setText(cc);

//        TextView bccText=findViewById(R.id.pre_bccInformation);
//        bccText.setText(bcc);

        TextView subjectText=findViewById(R.id.pre_subject);
        subjectText.setText(subject);

        TextView textView=findViewById(R.id.pre_content);
        textView.setText(content);

        Button send=this.findViewById(R.id.ready_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email=new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[] {to});
                email.putExtra(Intent.EXTRA_CC,new String[] {cc});
                email.putExtra(Intent.EXTRA_BCC,new String[]{bcc});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, content);

                email.setType("message/rfc822");

                Intent.createChooser(email,"Choose Email Client");
                startActivity(email);

            }
        });

        Button back=findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                }
        );





    }
}
