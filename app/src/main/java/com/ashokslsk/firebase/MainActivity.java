package com.ashokslsk.firebase;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CheckBox loc;
    Button im,ife,sm,sfe;
    String iam="u" ,seek="u";
    Firebase mRef,mRefi;
    static String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String countryCode = tm.getSimCountryIso();

        loc = (CheckBox)findViewById(R.id.checkBox);
       final Button connectme = (Button)findViewById(R.id.connectme);
       connectme.setEnabled(false);
        im = (Button)findViewById(R.id.imale);
        im.setBackgroundResource(R.drawable.notclicked);
        ife = (Button)findViewById(R.id.ifemale);
        ife.setBackgroundResource(R.drawable.notclicked);
        sm = (Button)findViewById(R.id.seekmale);
        sm.setBackgroundResource(R.drawable.notclicked);
        sfe = (Button)findViewById(R.id.seekfemale);
        sfe.setBackgroundResource(R.drawable.notclicked);

        im.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            im.setBackgroundResource(R.drawable.buttonclick);
            iam ="m";
                ife.setBackgroundResource(R.drawable.notclicked);
                if(iam.equals("u")||seek.equals("u"))
                {

                }
                else{
                    connectme.setEnabled(true);
                }

        }});

        ife.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ife.setBackgroundResource(R.drawable.buttonclick);
                iam ="f";
                im.setBackgroundResource(R.drawable.notclicked);
                if(iam.equals("u")||seek.equals("u"))
                {

                }
                else{
                    connectme.setEnabled(true);
                }

            }});

        sm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sm.setBackgroundResource(R.drawable.buttonclick);
                seek ="m";
                sfe.setBackgroundResource(R.drawable.notclicked);
                if(iam.equals("u")||seek.equals("u"))
                {

                }
                else{
                    connectme.setEnabled(true);
                }

            }});
        sfe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sfe.setBackgroundResource(R.drawable.buttonclick);
                seek ="f";
                sm.setBackgroundResource(R.drawable.notclicked);
                if(iam.equals("u")||seek.equals("u"))
                {

                }
                else{
                    connectme.setEnabled(true);
                }

            }});


        connectme.setOnClickListener(new View.OnClickListener() {
            String international = "no";

            @Override
            public void onClick(View v) {
                connectme.setText("Pairing");
                if(loc.isChecked()) {
                    mRefi = new Firebase("https://yahoo-9e00b.firebaseio.com/");
                    international = "yes";

                }else
                mRefi = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode );

                if(iam.equals("m")&& seek.equals("f"))
                {
                   mRef=mRefi.child("fm");
                            mRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    int count = (int) dataSnapshot.getChildrenCount();
                                    if (count > 0) {
                                        Random r = new Random();
                                        int i1 = r.nextInt(count);
                                        final String ss = Integer.toString(i1);
                                        String girlid = dataSnapshot.child(ss).getValue().toString();
                                        String lastgirl = dataSnapshot.child(Integer.toString(count - 1)).getValue().toString();
                                        mRef.removeEventListener(this);
                                        mRef.child(ss).setValue(lastgirl);
                                        mRef.child(Integer.toString(count - 1)).removeValue();
                                        Intent intent = new Intent(MainActivity.this,Inchat.class);
                                        intent.putExtra("id", girlid);
                                        intent.putExtra("id1", "second");
                                        intent.putExtra("asl","mf");
                                        intent.putExtra("international?", international);

                                        startActivityForResult(intent, 1);


                                    }
                                    else{
                                        final String rand = getRandomString(20);
                                        final Firebase  tmp=mRefi.child("mf").child("0");
                                        tmp.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                                if (dataSnapshot2.exists()) {
                                                    String s = dataSnapshot2.getValue().toString();
                                                    tmp.removeEventListener(this);
                                                    tmp.removeValue();
                                                    Intent intent = new Intent(MainActivity.this,Inchat.class);
                                                    intent.putExtra("id", s);
                                                    intent.putExtra("id1", "second");
                                                    startActivityForResult(intent, 1);


                                                } else {
                                                    tmp.removeEventListener(this);
                                                    tmp.setValue(rand);

                                                    Intent intent = new Intent(MainActivity.this,Inchat.class);
                                                    intent.putExtra("id", rand);
                                                    intent.putExtra("id1", "first");
                                                    startActivityForResult(intent, 1);

                                                }
                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {
                                            }
                                        });

                                    }
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });





                }
                if(iam.equals("f")&& seek.equals("m"))
                {   mRef=mRefi.child("fm");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String ran = getRandomString(20);
                            int count = (int) dataSnapshot.getChildrenCount();
                            String counts=Integer.toString(count);
                            mRef.removeEventListener(this);
                            mRef.child(counts).setValue(ran);
                            Intent intent = new Intent(MainActivity.this,Inchat.class);
                            intent.putExtra("id", ran);
                            intent.putExtra("id1", "first");
                            startActivityForResult(intent, 1);

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(iam.equals("m")&& seek.equals("m"))
                {
                    final String rand = getRandomString(20);
                    final Firebase tmp=mRefi.child("mm").child("0");
                    tmp.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {
                            if (dataSnapshot2.exists()) {
                                String s = dataSnapshot2.getValue().toString();
                                tmp.removeEventListener(this);
                                tmp.removeValue();
                                Intent intent = new Intent(MainActivity.this,Inchat.class);
                                intent.putExtra("id", s);
                                intent.putExtra("id1", "second");
                                startActivityForResult(intent, 1);

                            } else {
                                tmp.removeEventListener(this);
                                tmp.setValue(rand);

                                Intent intent = new Intent(MainActivity.this,Inchat.class);
                                intent.putExtra("id", rand);
                                intent.putExtra("id1", "first");
                                startActivityForResult(intent, 1);

                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });
                }
                if(iam.equals("f")&& seek.equals("f"))
                {
                    final String rand = getRandomString(20);
                    final Firebase tmp = mRefi.child("ff").child("0");
                    tmp.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {
                            if (dataSnapshot2.exists()) {
                                String s = dataSnapshot2.getValue().toString();
                                tmp.removeEventListener(this);
                                tmp.removeValue();
                                Intent intent = new Intent(MainActivity.this,Inchat.class);
                                intent.putExtra("id", s);
                                intent.putExtra("id1", "second");
                                startActivityForResult(intent, 1);

                            } else {
                                tmp.removeEventListener(this);
                                tmp.setValue(rand);

                                Intent intent = new Intent(MainActivity.this,Inchat.class);
                                intent.putExtra("id", rand);
                                intent.putExtra("id1", "first");
                                startActivityForResult(intent, 1);

                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });
                }

                       }});



    }
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
