//package com.example.covid19;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Switch;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
////import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Objects;
//
//public class general_aware_blog extends AppCompatActivity {
//    EditText name,additional;
//    Button submit;
//    String tName,tAdditional;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.general_aware_blog);
//        Database.getInstance().initializeCOVIDApp(getApplicationContext());
//
//        name = findViewById(R.id.name);
//
//        additional = findViewById(R.id.additional);
//
//        submit = findViewById(R.id.submit);
//        init();
//    }
//
//    private  void  getDetails(){
//        tName = name.getText().toString();
//
//        tAdditional = additional.getText().toString();
//
////        Log.d("good: ",tAge+" "+tCountry+" "+tCity);
////        Toast.makeText(this,"g: "+tAge+" "+tCountry,Toast.LENGTH_SHORT).show();
//
//    }
//    private void init() {
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                upload();
//            }
//        });
////       if(tAge == null || tArea == null || tCity == null || tCountry == null || tAdmit == null || tDiet == null || tState == null
////            || tMedicine == null || tHname == null || tHdoctor == null){
////           Toast.makeText(this,"Some fields are missing",Toast.LENGTH_SHORT).show();
////           return;
////       }
////
////        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("BlogsCOVID");
////        String key = dR.push().getKey();
////        COVIDBlog obj = new COVIDBlog(tName,tAge,tArea,tCity,tCountry,tAdditional,tAdmit,tDis,tDiet,tState,tMedicine,tDisease,tHname,tHdoctor,tRecover);
////        dR.child(key).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
////            @Override
////            public void onComplete(@NonNull Task<Void> task) {
////                if(task.isSuccessful()){
////                    Toast.makeText(blogwrite.this,"Successfully uploaded",Toast.LENGTH_SHORT).show();
////                    finish();
////                }
////
////                else{
////                    Toast.makeText(blogwrite.this,"Failed: "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
////                }
////            }
//        //});
//
//    }
//
//    private void upload() {
//        getDetails();
//
//        if (tName.isEmpty()) {
//            tName = "Anonymous";
//        }
//
//        if (tAdditional.isEmpty()) {
//            Toast.makeText(general_aware_blog.this,"Some Fields are Empty",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        //Log.d("good1: ",tAge+" "+tCountry+" "+tCity);
//        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("");
//        String key = dR.push().getKey();
//        COVIDBlog obj = new COVIDBlog(tName, tAge, tArea, tCity, tCountry, tAdditional, tAdmit, tDis, tDiet, tState, tMedicine, tDisease, tHname, tHdoctor, tRecover);
//        dR.child(key).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(blogwrite.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(blogwrite.this, "Failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//}
