package com.uci.ta.licenseplatereader.ui.Viewpager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uci.ta.licenseplatereader.R;

import java.util.ArrayList;

/**
 * Created by SONU on 16/09/15.
 */
public class DummyFragment extends Fragment {
    private View view;

    private String title;//String for tab title
    String platno;
    private static RecyclerView recyclerView;

    public DummyFragment() {
    }

    @SuppressLint("ValidFragment")
    public DummyFragment(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dummy_fragment, container, false);


        platno = getActivity().getIntent().getExtras().getString("plat");

        setRecyclerView();
        return view;



    }
    //Setting recycler view
    private void setRecyclerView() {
        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersRef = rootRef.child("kendaraan");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> list = new ArrayList<>();
//                if(dataSnapshot.child(platno).exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String name = ds.child("pel").getValue(String.class);
                        list.add(name);
                    }
                    RecyclerView_Adapter adapter = new RecyclerView_Adapter(getActivity(), list);
                    recyclerView.setAdapter(adapter);// set adapter on recyclerview
//                }else {
//                    list.add("tidak ada pelanggaran");
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        usersRef.addListenerForSingleValueEvent(eventListener);


//        ArrayList<String> arrayList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            arrayList.add(title+" Items " + i);//Adding items to recycler view
//        }
//        RecyclerView_Adapter adapter = new RecyclerView_Adapter(getActivity(), arrayList);
//        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }
}
