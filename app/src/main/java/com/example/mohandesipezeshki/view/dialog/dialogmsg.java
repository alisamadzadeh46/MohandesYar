package com.example.mohandesipezeshki.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohandesipezeshki.R;

public class dialogmsg extends Dialog {
    TextView text;
    Button bastan;
    Context context;
    String msg;
    public dialogmsg(  Context context , String msg) {
        super(context);
        this.context=context;
        this.msg=msg;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        text=(TextView)findViewById(R.id.text);
        bastan=(Button)findViewById(R.id.bastan);
        bastan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        text.setText(msg);
    }
}
