package com.uci.ta.licenseplatereader.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uci.ta.licenseplatereader.R;
import com.uci.ta.licenseplatereader.common.AdapterKendRecyclerView;
import com.uci.ta.licenseplatereader.common.Kendaraan;

import java.util.ArrayList;

public class DbReadActivity extends AppCompatActivity{
    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kendaraan> daftarKend;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.activity_db_read);
        /*** Inisialisasi RecyclerView & komponennya
         */
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_scan:
//                        Intent a = new Intent(DbReadActivity.this,MainActivity.class);
//                        startActivity(a);
//                        break;
//                    case R.id.navigation_list:
//                        break;
//                }
//                return false;
//            }
//        });

        //Queri


        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */
        database = FirebaseDatabase.getInstance().getReference();
        /**
         * Mengambil data dari Firebase Realtime DB
         */
        database.child("kendaraan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarKend = new ArrayList<Kendaraan>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Dosen
                     * Dan juga menyimpan primary key pada object Dosen
                     * untuk keperluan Edit dan Delete data
                     */
                    Kendaraan kend = noteDataSnapshot.getValue(Kendaraan.class);
                    kend.setNo_plat(noteDataSnapshot.getKey());
                    /**
                     * Menambahkan object Dosen yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarKend.add(kend);
                }
                /**
                 * Inisialisasi adapter dan data Dosen dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new AdapterKendRecyclerView(daftarKend, DbReadActivity.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DbReadActivity.class);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
