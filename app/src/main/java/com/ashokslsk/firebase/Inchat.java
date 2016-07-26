package com.ashokslsk.firebase;

import android.annotation.TargetApi;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * Created by surabhil on 6/17/16.
 */
public class Inchat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle verifysavedInstanceState) {
        super.onCreate(verifysavedInstanceState);
        setContentView(R.layout.inchat);
        Firebase.setAndroidContext(this);

        Bundle bundle = getIntent().getExtras();
        String id2;
        String id  = bundle.getString("id");
        String id1  = bundle.getString("id1");
        if(id1.equals("first")) {
            id2 = id + "/second";
            id1=id+"/first";
        }
        else {
            id2 = id+"/first";
            id1=id+"/second";
        }
        final LinearLayout chatspace = (LinearLayout)findViewById(R.id.chatspace);
        final TextView me = (TextView)findViewById(R.id.messagebox);


        final Firebase tmp = new Firebase("https://yahoo-9e00b.firebaseio.com/"+id1);
        final Firebase tmp2 = new Firebase("https://yahoo-9e00b.firebaseio.com/"+id2);
        final Button send = (Button)findViewById(R.id.send);
        final ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
        ImageView connect1 =(ImageView) findViewById(R.id.connect1);
        connect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        scroll.setBackgroundResource(R.drawable.scrollview);
        tmp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount();
                for(int i =0;i<count;i++)
                {
                    String recieve = dataSnapshot.child(Integer.toString(i)).getValue().toString();
                    //display value

                    chatspace.addView(createNewestTextView(recieve));
                    scroll.post(new Runnable() { public void run() { scroll.fullScroll(View.FOCUS_DOWN); } });
                }
                if(count!=0)
                    tmp.removeValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = me.getText().toString();
                me.setText("");
                //dipslay the message in android

                chatspace.addView(createNewTextView(s));
                scroll.post(new Runnable() { public void run() { scroll.fullScroll(View.FOCUS_DOWN); } });



                tmp2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = (int) dataSnapshot.getChildrenCount();
                        String ss = Integer.toString(count);
                        tmp2.removeEventListener(this);
                        tmp2.child(ss).setValue(s);


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(70, 5, 10, 5);
        final TextView textView = new TextView(this);
        lparams.gravity=Gravity.RIGHT;
        textView.setBackgroundResource(R.drawable.corner);
        textView.setLayoutParams(lparams);
        //textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.RIGHT);
        textView.setTextSize(16);
        textView.setText(text);
        return textView;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private TextView createNewestTextView(String text) {

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        lparams.setMargins(10, 5, 70, 5);
        //textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        textView.setBackgroundResource(R.drawable.recieved);
        textView.setTextSize(16);
        textView.setText(text);

        return textView;
    }
}
