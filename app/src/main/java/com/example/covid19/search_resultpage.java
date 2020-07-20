package com.example.covid19;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class search_resultpage extends AppCompatActivity {
    String disease;
    RecyclerView recycleView;
    TextView tvMessage;
    Toolbar toolbar;
    ProgressBar progressBar;
     ArrayList<COVIDBlog> diseaseBlogs = new ArrayList<>();
    //ArrayList<COVIDBlog> filterList = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleresultpage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        disease=getIntent().getStringExtra("disease");
        recycleView = findViewById(R.id.recycle);
        tvMessage = findViewById(R.id.tvMessage);
        progressBar = findViewById(R.id.progressBar);
        updatePage();
    }

    private void updatePage() {
        getDiseaseList();
    }

    private void getDiseaseList() {

//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Fetching Subscribed NGO Events .....");
//        progressDialog.setCanceledOnTouchOutside(false);
//        try { progressDialog.show(); }
//        catch(Exception e) { return; }
        progressBar.setVisibility(View.VISIBLE);
        diseaseBlogs.clear();
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("BlogsCOVID/"+disease);
        //final int[] c = {0};
//        for(int i=0;i<.size();i++){
//            String key = ngodetails.get(i).getNgoEmail().replaceAll("[^A-Za-z0-9]", "-");
//            DatabaseReference Dr = dR.child(disease);
            dR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot eD: dataSnapshot.getChildren()){
                        COVIDBlog temp =  eD.getValue(COVIDBlog.class);
//                        if(isCategory==true){
//                            if(temp.getCategory().equalsIgnoreCase(category))
//                                eventdetails.add(temp);
//                        }
                       // else
                            diseaseBlogs.add(temp);
                    }
//                    c[0]++;
//                    if(c[0] ==ngodetails.size()){
//                        //progressDialog.dismiss();
//                        progressBar.setVisibility(View.INVISIBLE);
//                        
//                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    setRecyclerView(diseaseBlogs);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    c[0]++;
//                    if(c[0] ==ngodetails.size()){
//                        //progressDialog.dismiss();
//                        progressBar.setVisibility(View.INVISIBLE);
//                        
//                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(search_resultpage.this, "Failed : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }



    public void setRecyclerView(ArrayList<COVIDBlog> recyclerlist){
        if(recyclerlist.size()==0) {
            recycleView.setVisibility(View.GONE);
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText("Nothing to Show");
            return;
        }
        tvMessage.setVisibility(View.GONE);
        recycleView.setVisibility(View.VISIBLE);
        recycleView.setLayoutManager(new LinearLayoutManager(search_resultpage.this));
        recycleView.setAdapter(new DataAdapter(recyclerlist,search_resultpage.this));
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        ArrayList<COVIDBlog> diseaseList;
        Context context;

        public DataAdapter(ArrayList<COVIDBlog> diseaseList, Context context) {
            this.diseaseList = diseaseList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_view,parent,false);

            return new DataAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setIsRecyclable(false);

            COVIDBlog event = diseaseList.get(position);
            holder.tvAge.setText("Age: "+event.gettAge());

            holder.tvName.setText("Name : "+event.gettName());
            holder.tvCountry.setText("Country : "+event.gettCountry());

        }

        @Override
        public int getItemCount() {
            return diseaseList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tvName, tvAge, tvCountry;
            public ViewHolder(View itemView){
                super(itemView);
                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvAge = itemView.findViewById(R.id.tvAge);
                tvCountry = itemView.findViewById(R.id.tvCountry);
//                itemView.setOnCreateContextMenuListener(this);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_resultpage.this,blogdetails.class);
                //Toast.makeText(subact.this,ngoList.get(getLayoutPosition()).getNgoEmail(),Toast.LENGTH_LONG).show();
                intent.putExtra("key",diseaseList.get(getLayoutPosition()).getKey());
                intent.putExtra("Disease",diseaseList.get(getLayoutPosition()).getCurrDisease().toLowerCase());
                startActivity(intent);
            }


//            @Override
//            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                MenuItem share;
//                share = menu.add(0,1,1,"Share Event");
//                share.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        shareEvent(eventList.get(getLayoutPosition()));
//                        return true;
//                    }
//                });
//            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchfiltermenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.updatePage) {
            //isCategory = false;
            updatePage();
            Toast.makeText(search_resultpage.this,"Page Updated",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.filter) {
            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.filteralert, null);
            final EditText etCountry = alertLayout.findViewById(R.id.country);
            final EditText etState = alertLayout.findViewById(R.id.state);
            final EditText etCity = alertLayout.findViewById(R.id.city);
            final EditText etLower = alertLayout.findViewById(R.id.lower);
            final EditText etUpper = alertLayout.findViewById(R.id.upper);

            android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
            alert.setTitle("Filter");
            alert.setView(alertLayout);
            alert.setPositiveButton("Apply", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String country = etCountry.getText().toString();
                    String state = etState.getText().toString();
                    String city = etCity.getText().toString();
                    String lower = etLower.getText().toString();
                    String upper = etUpper.getText().toString();
//                    int l = Integer.parseInt(lower);
//                    int u = Integer.parseInt(upper);
//                    l = Math.min(l,u);
//                    u = Math.max(l,u);
                    if (country.isEmpty() && state.isEmpty() && city.isEmpty() && lower.isEmpty() && upper.isEmpty())
                        Toast.makeText(search_resultpage.this, "At least one fileld required", Toast.LENGTH_SHORT).show();
                    else {
//                        try {
////                            category = temp;
////                            isCategory = true;
////                            updatePage();
//
//                        } catch (Exception e) {
//                        }
                        int l1 = -1,u1 = Integer.MAX_VALUE;
                        if(!lower.isEmpty())l1 = Integer.parseInt(lower);
                       // if(lower.isEmpty()) l =-1;
                        if(!upper.isEmpty()) u1 = Integer.parseInt(upper);
                       // if(upper.isEmpty()) u=Integer.MAX_VALUE;
                        int l = Math.min(l1,u1);
                        int u = Math.max(l1,u1);

                        if(!country.isEmpty() && !state.isEmpty() && !city.isEmpty()) filter(country,state,city,l,u);
                        else if(!country.isEmpty() && !state.isEmpty()) filtercs(country,state,l,u);
                        else if(!state.isEmpty() && !city.isEmpty()) filtersc(state,city,l,u);
                        else if(!country.isEmpty() && !city.isEmpty()) filtercc(country,city,l,u);
                        else if(!country.isEmpty()) filterco(country,l,u);
                        else if(!city.isEmpty()) filterci(city,l,u);
                        else if(!state.isEmpty()) filters(state,l,u);
                        else if(!lower.isEmpty() || !upper.isEmpty()) filterlu(l,u);

                    }
                }
            });
            android.app.AlertDialog dialog = alert.create();
            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void filterlu(int l, int u) {
        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filters(String state,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettState().toLowerCase().compareTo(state.toLowerCase()) != 0
                    || age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filterci(String city,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettCity().toLowerCase().compareTo(city.toLowerCase()) != 0
                    || age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filterco(String country,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettCountry().toLowerCase().compareTo(country.toLowerCase()) != 0
                    || age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }


    public void filter(String country,String state,String city,int l,int u){

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
//            int uc = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettCountry().toLowerCase().compareTo(country.toLowerCase()) != 0
                    ||temp.gettState().toLowerCase().compareTo(state.toLowerCase()) != 0
                    ||temp.gettCity().toLowerCase().compareTo(city.toLowerCase()) != 0
                    || age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filtercs(String country, String state,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettCountry().toLowerCase().compareTo(country.toLowerCase()) != 0
                    ||temp.gettState().toLowerCase().compareTo(state.toLowerCase()) != 0
                    || age<l || age>u){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filtersc(String state, String city,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettState().toLowerCase().compareTo(state.toLowerCase()) != 0
                    ||temp.gettCity().toLowerCase().compareTo(city.toLowerCase()) != 0
                    || age<l || age>u ){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

    private void filtercc(String country, String city,int l,int u) {

        for(int i=0;i<diseaseBlogs.size();i++){
            COVIDBlog temp = diseaseBlogs.get(i);
            int age = Integer.parseInt(temp.gettAge().toString());
            if(temp.gettCountry().toLowerCase().compareTo(country.toLowerCase()) != 0
                    ||temp.gettCity().toLowerCase().compareTo(city.toLowerCase()) != 0
                    || age<l || age>u ){
                diseaseBlogs.remove(i);
            }
        }
        setRecyclerView(diseaseBlogs);
    }

}
