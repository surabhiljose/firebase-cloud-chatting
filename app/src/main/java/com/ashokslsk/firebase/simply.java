package com.ashokslsk.firebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

/**
 * Created by surabhil on 6/25/16.
 */
public class simply {
/*
    Firebase mRef;
    Button mm,mf,fm,ff;
    static String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String countryCode = tm.getSimCountryIso();
        mm = (Button)findViewById(R.id.mm);
        mf = (Button)findViewById(R.id.mf);
        fm = (Button)findViewById(R.id.fm);
        ff = (Button)findViewById(R.id.ff);
        mf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode +"/fm");
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

                            startActivityForResult(intent, 1);


                        }
                        else{
                            final String rand = getRandomString(20);
                            final Firebase tmp = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode + "/mf/0");
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
        });

        fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode +"/fm");
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

            }});

        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rand = getRandomString(20);
                final Firebase tmp = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode + "/mm/0");
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
        });

        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rand = getRandomString(20);
                final Firebase tmp = new Firebase("https://yahoo-9e00b.firebaseio.com/" + countryCode + "/ff/0");
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
        });

        //Toast.makeText(getApplicationContext(), locale, Toast.LENGTH_SHORT).show();




    }
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
 */
}
