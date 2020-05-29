/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uci.ta.licenseplatereader.ui.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.CommonStatusCodes;


import com.uci.ta.licenseplatereader.R;
import com.uci.ta.licenseplatereader.common.IntentData;
import com.uci.ta.licenseplatereader.utils.camera.OcrLicensePlateListener;
import com.uci.ta.licenseplatereader.utils.camera.VisionImageProcessor;
import com.uci.ta.licenseplatereader.utils.ocr.ILicensePlate;

/**
 * Activity for the multi-tracker app.  This app detects text and displays the value with the
 * rear facing camera. During detection overlay graphics are drawn to indicate the position,
 * size, and contents of each TextBlock.
 */
public final class OcrLicensePlateCaptureActivity extends OcrCaptureActivity {
    private static final String TAG = OcrLicensePlateCaptureActivity.class.getSimpleName();

    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout layoutTutorial;

    /**
     * Initializes the UI and creates the detector pipeline.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.ocr_capture_license_plate);
        onCreateInit(icicle);
        layoutTutorial = findViewById(R.id.tutorialLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(layoutTutorial);
        setActionBarTitle(getString(R.string.scan_plate));
        setBackButton();
    }

    @Override
    protected VisionImageProcessor getTextProcessor() {
        return new OcrLicensePlateDetectorProcessor(new OcrLicensePlateListener() {
            @Override
            public void onLicensePlate(ILicensePlate drivingLicense) {
                Intent data = new Intent();
                data.putExtra(IntentData.KEY_LICENSE_PLATE, drivingLicense);
                setResult(CommonStatusCodes.SUCCESS, data);
                finish();
            }
        });
    }
}
