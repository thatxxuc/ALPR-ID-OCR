package com.uci.ta.licenseplatereader.utils.ocr.filters.license_plate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.uci.ta.licenseplatereader.utils.ocr.ILicensePlate;
import com.uci.ta.licenseplatereader.utils.ocr.LicensePlate;

public class IDLicensePlateFilter implements ILicensePlateFilter {

    private static String UK_LICENSE_PLATE ="(([A-Z]{1,2})*(| )[0-9]{1,4} [A-Z]{1,3})";
    private static String UK_LICENSE_PLATE_TEMP ="(([A-Z]{1,2})*(| )[0-9ILDS]{1,4} [A-Z]{1,3})";

    @Override
    public ILicensePlate extract(String src) throws IllegalArgumentException {

        String tempLicense="";
        Pattern patternPre = Pattern.compile(UK_LICENSE_PLATE_TEMP);
        Matcher matcher1 = patternPre.matcher(src);
        if(matcher1.find()){
            String group = matcher1.group(0);
            String areaCode = group.substring(0, 2);

            String random = group.substring(2, 4);
            random=random.replaceAll("I", "1");
            random=random.replaceAll("L", "1");
            random=random.replaceAll("D", "0");
            random=random.replaceAll("S", "5");

            String subdaerah = group.substring(4, group.length());

            tempLicense=areaCode+random+subdaerah;
        }
        //Log.e("PostProcess UK", tempLicense);
        Pattern pattern = Pattern.compile(UK_LICENSE_PLATE);
        // String text = graphic.getTextBlock().getValue().trim();
        Matcher matcher = pattern.matcher(tempLicense);
        if(matcher.find()){
            ILicensePlate license = new LicensePlate("ID", matcher.group(0));
            return license;
        }
        throw new IllegalArgumentException("ID License not valid");
    }
}
