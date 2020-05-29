package com.uci.ta.licenseplatereader.utils.ocr;

import java.util.ArrayList;
import java.util.List;

import com.uci.ta.licenseplatereader.utils.ocr.filters.license_plate.ILicensePlateFilter;
import com.uci.ta.licenseplatereader.utils.ocr.filters.license_plate.IDLicensePlateFilter;

public class LicensePlateManager {

    static LicensePlateManager instance=null;

    public static LicensePlateManager getInstance(){
        if(instance==null){
            instance=new LicensePlateManager();
        }
        return instance;
    }

    private List<ILicensePlateFilter> filters;

    private LicensePlateManager(){
        filters= new ArrayList<>();
//        filters.add(new IrishLicensePlateFilter());
//        filters.add(new NortherIrelandLicensePlateFilter());
        filters.add(new IDLicensePlateFilter());
       // filters.add(new IDLicensePlateFilter());
        //filters.add(new UK1983LicensePlateFilter());
        //filters.add(new UK1963LicensePlateFilter());
      //  filters.add(new UKDiplomaticLicensePlateFilter());

    }

    public ILicensePlate extractLicense(String src) throws IllegalArgumentException {
        for(ILicensePlateFilter filter: filters){
            try {
                return filter.extract(src);
            }catch (Exception e){
            }
        }
        throw new IllegalArgumentException("Unable to extract a license plate");
    }

}
