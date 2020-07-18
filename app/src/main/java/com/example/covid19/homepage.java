package com.example.covid19;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class homepage extends AppCompatActivity {
    FloatingActionButton f;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        f = findViewById(R.id.floatingActionButton);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this,blogwrite.class);
                startActivity(i);
            }
        });
        //init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.updatePage) {
//            isCategory = false;
//            updatePage();
//            Toast.makeText(userdashboard.this,"Page Updated",Toast.LENGTH_SHORT).show();
//        }
        if (item.getItemId() == R.id.search) {
            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.custom, null);
            final EditText etCategory = alertLayout.findViewById(R.id.CC);

            android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
            alert.setTitle("Search Disease");
            alert.setView(alertLayout);
            alert.setPositiveButton("Apply", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String temp = etCategory.getText().toString().toLowerCase();
                    if (temp.isEmpty())
                        Toast.makeText(homepage.this, "Please enter Disease to search", Toast.LENGTH_SHORT).show();
                    else {
//                        try {
////                            category = temp;
////                            isCategory = true;
////                            updatePage();
//                        } catch (Exception e) {
//                        }

                        Intent intent = new Intent(homepage.this,search_resultpage.class);
                        intent.putExtra("disease",temp);
                        //Toast.makeText(nearbyngo.this,"Clicked",Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }
                }
            });
            android.app.AlertDialog dialog = alert.create();
            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }


}
