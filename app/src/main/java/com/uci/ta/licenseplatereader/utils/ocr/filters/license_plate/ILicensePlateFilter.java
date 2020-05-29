package com.uci.ta.licenseplatereader.utils.ocr.filters.license_plate;


import com.uci.ta.licenseplatereader.utils.ocr.ILicensePlate;

public interface ILicensePlateFilter {
    ILicensePlate extract(String src) throws IllegalArgumentException;
}
