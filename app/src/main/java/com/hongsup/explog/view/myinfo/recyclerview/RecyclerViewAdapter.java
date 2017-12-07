package com.hongsup.explog.view.myinfo.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hongsup.explog.R;

/**
 * Created by 정인섭 on 2017-12-05.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    public final static int HEADER = 99;
    public final static int CONTENT = 77;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch(viewType){
            case HEADER :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myinfo_header, parent, false);
                view.setTag("gg");
                break;
                
//            case CONTENT :
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myinfo_content, parent, false);
//                break;
        }
        
        return new MyHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return HEADER;
        }else{
            return CONTENT;
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TabLayout tabLayout;
        Button button;

        public MyHolder(View itemView) {
            super(itemView);
            tabLayout = itemView.findViewById(R.id.tabLayoutMyInfo);
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "첫번째", Toast.LENGTH_SHORT).show();
                }
            });
//            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    switch(tab.getPosition()){
//                        case 0 :
//                            Toast.makeText(itemView.getContext(), "첫번째", Toast.LENGTH_SHORT).show();
//                            break;
//
//                        case 1 :
//                            Toast.makeText(itemView.getContext(), "두번째", Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });
        }
    }
}
