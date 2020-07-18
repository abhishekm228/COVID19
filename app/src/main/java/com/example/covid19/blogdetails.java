package com.example.covid19;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class blogdetails extends AppCompatActivity {
    String Key,Disease;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blogdetails);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        Key = getIntent().getStringExtra("key");
        Disease = getIntent().getStringExtra("Disease");
        updatePage();
    }

    public void updatePage() {
//        if(isInternet()==false){
//            Toast.makeText(this,"You Are Offline",Toast.LENGTH_LONG).show();
//            return;
//        }
        getDetails();
    }

    private void getDetails(){
        //final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Fetching Data.....");
//        progressDialog.setCanceledOnTouchOutside(false);
//        try{progressDialog.show();}
//        catch(Exception e) {return;}
//        progressBar.setVisibility(View.VISIBLE);

        //ngoEmail = ngoEmail.replaceAll("[^A-Za-z0-9]","-");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BlogsCOVID/"+Disease+"/"+Key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //progressDialog.dismiss();;
               // progressBar.setVisibility(View.INVISIBLE);
                COVIDBlog placeH = dataSnapshot.getValue(COVIDBlog.class);
                //Database.getInstance().addNgo(mNgo);
                //Toast.makeText(myngo.this,"Hello",Toast.LENGTH_LONG).show();
                init(placeH);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressDialog.dismiss();
               // progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(blogdetails.this,"Falied : "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(COVIDBlog placeH) {
        TextView tvName,tvCurrDisease,tvCountry,tvCity,tvAge,tvState,tvArea,tvDiet,tvMedicine,tvprevD,tvHospital,tvDoctor,tvAdmit,tvDis,tvAdd,tvRecovered;

        tvName = findViewById(R.id.tvName);
        tvName.setText("Name : "+placeH.gettName());

        tvCurrDisease = findViewById(R.id.tvCurrDisease);
        tvCurrDisease.setText("Disease : "+placeH.getCurrDisease());

        tvRecovered = findViewById(R.id.tvRecovered);
        tvRecovered.setText("Recovery Information : "+placeH.gettRecover());

        tvAge = (TextView) findViewById(R.id.tvAge);
        tvAge.setText("Age : "+placeH.gettAge());

        tvCountry = (TextView) findViewById(R.id.tvCountry);
        tvCountry.setText("Country : "+placeH.gettCountry());

        tvState = (TextView) findViewById(R.id.tvState);
        tvState.setText("State : "+placeH.gettState());

        tvCity = (TextView) findViewById(R.id.tvCity);
        tvCity.setText("City : "+placeH.gettCity());



        tvArea = (TextView) findViewById(R.id.tvArea);
        tvArea.setText("Area : "+placeH.gettArea());

        tvDiet = (TextView) findViewById(R.id.tvDiet);
        tvDiet.setText("Diet : "+placeH.gettDiet());

        tvMedicine = (TextView) findViewById(R.id.tvMedicine);
        tvMedicine.setText("Medicine : "+placeH.gettMedicine());

        tvprevD = (TextView) findViewById(R.id.tvprevDisease);
        tvprevD.setText("Previous Disease : "+placeH.gettDisease());

        tvHospital = (TextView) findViewById(R.id.tvHospital);
        tvHospital.setText("Hospital : "+placeH.gettHname());

        tvDoctor = (TextView) findViewById(R.id.tvDoctor);
        tvDoctor.setText("Doctor : "+placeH.gettHdoctor());

        tvAdmit = (TextView) findViewById(R.id.tvAdmit);
        tvAdmit.setText("Admit Date : "+placeH.gettAdmit());

        tvDis = (TextView) findViewById(R.id.tvDischarge);
        tvDis.setText("Discharge Date : "+placeH.gettDis());

        tvAdd = (TextView) findViewById(R.id.tvAdditional);
        tvAdd.setText("Additional Details: "+placeH.gettAdditional());
    }
}
