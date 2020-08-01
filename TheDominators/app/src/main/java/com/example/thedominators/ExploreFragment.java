package com.example.thedominators;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ExploreFragment extends Fragment {



    public ExploreFragment() {
        // Required empty public constructor
    }



    private DatabaseReference mDatabaseref;
    private List<Upload> UploadList;
    private List<Upload> filteredlist;
    ListView listView;
    // adapter class object
    DataAdapter adapter;
    SearchView search;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);
        listView = v.findViewById(R.id.listview);

        search = v.findViewById(R.id.llsearch);

        mDatabaseref = FirebaseDatabase.getInstance().getReference().child("Uploaded Data");
        UploadList = new ArrayList<>();
        filteredlist = new ArrayList<>();

        mDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UploadList.clear();
                for(DataSnapshot placeSnapshot : dataSnapshot.getChildren())
                {
                    Upload places = placeSnapshot.getValue(Upload.class);
                    UploadList.add(places);
                }
                adapter = new DataAdapter(getActivity(),UploadList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition = position;
                Upload upload = (Upload) listView.getItemAtPosition(position);
                String name = upload.getName();
                String imgurl = upload.getImgurl();
                String city = upload.getCity();
                String longi = upload.getLongi();
                String lati = upload.getLati();
                String about = upload.getAbout();
                String river = upload.getRiver();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Url", imgurl);
                intent.putExtra("Longi", longi);
                intent.putExtra("Lati", lati);
                intent.putExtra("city", city);
                intent.putExtra("about", about);
                intent.putExtra("river",river);
                startActivity(intent);
            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(search!=null)
                {
                    for (Upload upload:UploadList) {

                        if(upload.name.toLowerCase().startsWith(query.toLowerCase()))
                            filteredlist.add(upload);
                    }
                }
                else
                    filteredlist = UploadList;

                adapter = new DataAdapter(getActivity(),filteredlist);
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        CardView cardgallery = v.findViewById(R.id.cardgallery);
        cardgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Gallery Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        CardView cardriver = v.findViewById(R.id.cardriver);
        cardriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Rivers Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        v.findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UploadActivity.class));
                Toast.makeText(getActivity(),"Add Data Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        return v;

    }
}