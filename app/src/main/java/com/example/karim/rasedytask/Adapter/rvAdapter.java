package com.example.karim.rasedytask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.karim.rasedytask.Model.AdsData;
import com.example.karim.rasedytask.R;

import org.w3c.dom.Text;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.RVGroup> {
    Context _ctx;
    List<AdsData>dataList;

    public rvAdapter(Context _ctx, List<AdsData> dataList) {
        this._ctx = _ctx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RVGroup onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(_ctx).inflate(R.layout.rv_item,parent,false);
        return new RVGroup(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVGroup holder, final int position) {
        AdsData data=dataList.get(position);
        holder.titleText.setText(data.getTitle());
        Glide.with(_ctx).load(data.getPicture()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(dataList.get(position).getUrl());
            }
        });
    }

    private void openURL(String url){
        Intent urlIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        _ctx.startActivity(urlIntent);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class RVGroup extends RecyclerView.ViewHolder {
        TextView  titleText;
        ImageView imageView;
        public RVGroup(View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleText);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
