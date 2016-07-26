# Firebasestarters
Super simple firebase integration


```sh
package com.yourpackage.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView fireData;
    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        fireData = (TextView) findViewById(R.id.firedata);
        mRef = new Firebase("https://dazzling-inferno-6462.firebaseio.com/condition");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String SuperData = (String) dataSnapshot.getValue();
                fireData.setText(SuperData);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}

```

## Android xml Layout
```sh
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.ashokslsk.firebase.MainActivity">

    <TextView
        android:id="@+id/firedata"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />


</RelativeLayout>


```
![Screenshot](https://github.com/ashokslsk/Firebasestarters/blob/master/screens/screen.png)

## Setup
Update [`MainActivity`](/app/src/main/java/com/firebase/androidchat/MainActivity.java) and replace
`https://android-chat.firebaseio-demo.com` with a reference to your Firebase.

## What's here
## More about Firebase on Android

You can do lots more with Firebase on Android. Check out our Android
[Quickstart guide](https://www.firebase.com/docs/java-quickstart.html) to learn more.


Finally your done now.

- Execute the code with your api key and you are done simple as it is 

* [For more codes, funs and for queries be in touch with @ashokslsk ](https://github.com/ashokslsk)

## License

```
    Copyright 2016 Ashokslsk.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
