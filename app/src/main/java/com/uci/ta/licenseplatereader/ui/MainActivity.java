package com.uci.ta.licenseplatereader.ui;

import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uci.ta.licenseplatereader.R;
import com.uci.ta.licenseplatereader.common.DBCreateActivity;
import com.uci.ta.licenseplatereader.common.IntentData;
import com.uci.ta.licenseplatereader.ui.Viewpager.Main;
import com.uci.ta.licenseplatereader.ui.ocr.OcrLicensePlateCaptureActivity;
import com.uci.ta.licenseplatereader.utils.ocr.ILicensePlate;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int RC_OCR_CAPTURE = 9003;
    private String id;
    AppCompatTextView textViewLicensePlate;
    AppCompatTextView nama;
    AppCompatImageButton buttonCamera;
    AppCompatImageButton btn;
    private AppCompatButton btCreateDB;
    private AppCompatImageButton btViewDB;
    private Context context;
    private DatabaseReference mDatabase;
    private ActionBar toolbar;
    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
//
//
//        bgapp = (ImageView) findViewById(R.id.bgapp);
//        clover = (ImageView) findViewById(R.id.clover);
//        textsplash = (LinearLayout) findViewById(R.id.textsplash);
//        texthome = (LinearLayout) findViewById(R.id.texthome);
//        menus = (LinearLayout) findViewById(R.id.menus);
//
//        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
//        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
//        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
//
//        texthome.startAnimation(frombottom);
//        menus.startAnimation(frombottom);

        textViewLicensePlate = findViewById(R.id.licensePlateScanned);

        btViewDB = findViewById(R.id.btnViewDB);
        btViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, DBCreateActivity.class);
                startActivity(intent1);
            }
        });

        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch Ocr capture activity.
                textViewLicensePlate.setText("");
                Intent intent = new Intent(MainActivity.this, OcrLicensePlateCaptureActivity.class);
                intent.putExtra(OcrLicensePlateCaptureActivity.AutoFocus, true);
                intent.putExtra(OcrLicensePlateCaptureActivity.UseFlash, false);

                startActivityForResult(intent, RC_OCR_CAPTURE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    final ILicensePlate license = data.getParcelableExtra(IntentData.KEY_LICENSE_PLATE);
                    //  id = intent.getStringExtra(konfigurasi.EMP_ID);
                    String plat = license.getLicensePlate();
                    Intent ini = new Intent(MainActivity.this, Main.class);
                    ini.putExtra("noplat",plat);
                    startActivity(ini);
//                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("kendaraan");
//                    db.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            String plat = license.getLicensePlate();
//                            if(dataSnapshot.child(plat).exists()){
//
//                                //textViewLicensePlate.setText(dataSnapshot.child(plat).getValue().toString());
//                                Toast.makeText(MainActivity.this,"Plat Dikenali",Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(MainActivity.this,"Plat Tidak Dikenali",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//
//                    textViewLicensePlate.setText(license.getLicensePlate());
                    Log.d(TAG, "Text read: " + license);
                } else {
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
