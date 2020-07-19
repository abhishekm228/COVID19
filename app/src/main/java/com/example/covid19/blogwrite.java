package com.example.covid19;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class blogwrite extends AppCompatActivity {
    EditText name,age,area,city,country,additional,admitdate,disdate,diet,state,medicine,pdisease,hname,hdoctor,currDisease;
    Switch recover;
    Button submit;
    String tName,tAge,tArea,tCity,tCountry,tAdditional,tAdmit,tDis,tDiet,tMedicine,tDisease,tHname,tHdoctor,tState,tRecover,tcurrDisease;
    Boolean decision;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog);
        Database.getInstance().initializeCOVIDApp(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.name);
        currDisease = findViewById(R.id.currdisease);
        age = findViewById(R.id.age);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        additional = findViewById(R.id.additional);
        admitdate = findViewById(R.id.dateofadmission);
        disdate = findViewById(R.id.dateofdischarge);
        diet = findViewById(R.id.diet);
        state = findViewById(R.id.state);
        medicine = findViewById(R.id.medicine);
        pdisease = findViewById(R.id.pdisease);
        recover = findViewById(R.id.recover);
        hname = findViewById(R.id.hname);
        hdoctor = findViewById(R.id.hdoctorname);
        submit = findViewById(R.id.submit);
        init();
    }

    private  void  getDetails(){
        tName = name.getText().toString();
        tcurrDisease = currDisease.getText().toString();
        tAge = age.getText().toString();
        tArea = area.getText().toString();
        tCity = city.getText().toString();
        tCountry = country.getText().toString();
        tAdditional = additional.getText().toString();
        tAdmit = admitdate.getText().toString();
        tDis = disdate.getText().toString();
        tDiet = diet.getText().toString();
        tState = state.getText().toString();
        tMedicine = medicine.getText().toString();
        tDisease = pdisease.getText().toString();
        tHname = hname.getText().toString();
        tHdoctor = hdoctor.getText().toString();
        decision = recover.isChecked();
        Log.d("good: ",tAge+" "+tCountry+" "+tCity);
       // Toast.makeText(this,"g: "+tAge+" "+tCountry,Toast.LENGTH_SHORT).show();
        if(decision == false)
            tRecover = "Not Recovered";
        else tRecover = "Recovered";
    }
    private void init() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
//       if(tAge == null || tArea == null || tCity == null || tCountry == null || tAdmit == null || tDiet == null || tState == null
//            || tMedicine == null || tHname == null || tHdoctor == null){
//           Toast.makeText(this,"Some fields are missing",Toast.LENGTH_SHORT).show();
//           return;
//       }
//
//        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("BlogsCOVID");
//        String key = dR.push().getKey();
//        COVIDBlog obj = new COVIDBlog(tName,tAge,tArea,tCity,tCountry,tAdditional,tAdmit,tDis,tDiet,tState,tMedicine,tDisease,tHname,tHdoctor,tRecover);
//        dR.child(key).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(blogwrite.this,"Successfully uploaded",Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//                else{
//                    Toast.makeText(blogwrite.this,"Failed: "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            }
        //});

    }

    private void upload() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Upload in Progress .....");
        progressDialog.setCanceledOnTouchOutside(false);
        try { progressDialog.show(); }
        catch(Exception e) { return; }
        getDetails();
        if (tcurrDisease.isEmpty() || tAge.isEmpty() || tArea.isEmpty() || tCity.isEmpty() || tCountry.isEmpty() || tAdmit.isEmpty() || tDiet.isEmpty() || tState.isEmpty()
                || tMedicine.isEmpty() || tHname.isEmpty() || tHdoctor.isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, "Some fields are missing", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tName.isEmpty()) {
            tName = "Anonymous";
        }
        if (tDis.isEmpty()) {
            tDis = "Not Dismissed";
        }
        if (tAdditional.isEmpty()) {
            tAdditional = "Nothing to Show";
        }
        if (tDisease.isEmpty()) {
            tDisease = "No Previous Disease Found";
        }
        Log.d("good1: ",tAge+" "+tCountry+" "+tCity);
        String lower = tcurrDisease.toLowerCase();
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("BlogsCOVID/"+lower);
        String key = dR.push().getKey();
        COVIDBlog obj = new COVIDBlog(tName, tcurrDisease, tAge, tCountry, tState, tCity, tArea, tDiet, tMedicine, tDisease, tHname, tHdoctor, tAdmit, tDis, tAdditional, tRecover, key);
        dR.child(key).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(blogwrite.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(blogwrite.this, "Failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
