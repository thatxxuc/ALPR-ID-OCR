package com.uci.ta.licenseplatereader.utils.camera;


import com.uci.ta.licenseplatereader.utils.ocr.ILicensePlate;

public interface OcrLicensePlateListener {
    void onLicensePlate(ILicensePlate drivingLicense);
}
