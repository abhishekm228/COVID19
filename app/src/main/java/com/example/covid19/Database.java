package com.example.covid19;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.util.List;

public class Database {
    public Database() {
    }

    public static Database temp=null;


    public static Database getInstance(){
        if(temp==null) {
            temp = new Database();
        }
        return temp;
    }
    public void initializeCOVIDApp(Context context) {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("AIzaSyChjTZRZCjVthX4eI4Ckbr9mpo_3TJp77E")
                .setApplicationId("com.example.covid19")
                .setDatabaseUrl("https://covid19-4eca3.firebaseio.com/")
                .build();
        Boolean initialized = false;
        List<FirebaseApp> firebaseApps= FirebaseApp.getApps(context);
        for(FirebaseApp temp : firebaseApps){
            if(temp.getName() == "COVIDApp") {
                initialized = true;
                break;
            }
        }
        if(initialized == false) FirebaseApp.initializeApp(context, options, "COVIDApp");
    }


}
