package com.example.thedominators;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {



    public ProfileFragment() {
        // Required empty public constructor
    }

    FirebaseAuth mAuth ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            View v = inflater.inflate(R.layout.demo_profile, container, false);
            v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            });
            return v;
        }
        else{
            View v= inflater.inflate(R.layout.fragment_profile, container, false);
            ImageView imageView = v.findViewById(R.id.profileimage);
            TextView textName = v.findViewById(R.id.name);
            TextView textEmail = v.findViewById(R.id.email);
            TextView logout = v.findViewById(R.id.logout);

            FirebaseUser user = mAuth.getCurrentUser();

            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(imageView);

            textName.setText(user.getDisplayName());
            textEmail.setText(user.getEmail());

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            });
            return v;

        }

    }
}