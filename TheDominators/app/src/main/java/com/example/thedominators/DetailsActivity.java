package com.example.thedominators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        final String name = extras.getString("Name");
        String url = extras.getString("Url");
        String city = extras.getString("city");
        String about = extras.getString("about");
        final String lati = extras.getString("Lati");
        final String longi = extras.getString("Longi");
        String river = extras.getString("river");
        TextView naam = findViewById(R.id.name);
        ImageView urll = findViewById(R.id.url);
        TextView cityy = findViewById(R.id.city);
        TextView aboutt = findViewById(R.id.about);
        TextView riverr = findViewById(R.id.river);

        naam.setText(name.toString());
        Glide.with(this).load(url).into(urll);
        cityy.setText(city.toString());
        aboutt.setText(about.toString());
        riverr.setText(river.toString());

        findViewById(R.id.txtlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsActivity.this, LocationActivity.class);
                intent.putExtra("Lati",lati);
                intent.putExtra("Longi",longi);
                intent.putExtra("Name",name);
                startActivity(intent);
            }
        });



    }


}