package com.uci.ta.licenseplatereader.ui.Viewpager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uci.ta.licenseplatereader.R;


public abstract class DemoViewHolder extends RecyclerView.ViewHolder {


    public TextView title;


    public DemoViewHolder(View view) {
        super(view);


        this.title = (TextView) view.findViewById(R.id.No_Pelanggaran);

    }


}