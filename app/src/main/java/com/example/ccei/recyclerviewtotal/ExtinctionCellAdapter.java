package com.example.ccei.recyclerviewtotal;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ccei on 2016-07-11.
 */
public class ExtinctionCellAdapter extends RecyclerView.Adapter<ExtinctionCellAdapter.ViewHolder> implements View.OnClickListener {
    private static final int[] bgColors = {
        0XAA000000, 0XFF800000, 0XAA008000, 0XFF000080
    };

    private static Random random = new Random();

    private int expandedPosition = -1;

    private ArrayList<Integer> mDataset;
    private Context mContext;

    public ExtinctionCellAdapter(Context mContext, ArrayList<Integer> mDataset) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell_layout, parent, false);
        ViewHolder holder = new ViewHolder(itemRoot);

        holder.itemView.setOnClickListener(ExtinctionCellAdapter.this);
        holder.itemView.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorIndex = random.nextInt(bgColors.length);
        String memberName = RandomArrayList.getGirlGroupName(mDataset.get(position));
        holder.extinctionName.setText(memberName);
        holder.extinctionImage.setBackgroundColor(bgColors[colorIndex]);
        holder.extinctionImage.setImageResource(mDataset.get(position).intValue());

        if(position == expandedPosition){
            holder.itemExpandArea.setVisibility(View.VISIBLE);
        }
        else{
            holder.itemExpandArea.setVisibility(View.GONE);
        }

        holder.extinctionName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String memberName = RandomArrayList.getGirlGroupName(mDataset.get(holder.getLayoutPosition()));

        if(expandedPosition >= 0){
            int prev = expandedPosition;
            notifyItemChanged(expandedPosition);
        }
        expandedPosition = holder.getLayoutPosition();
        notifyItemChanged(expandedPosition);
        Toast.makeText(mContext, "클릭한 아이템-" + memberName, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView extinctionImage;
        TextView extinctionName;
        LinearLayout itemExpandArea;

        public ViewHolder(View itemView) {
            super(itemView);

            extinctionImage = (ImageView) itemView.findViewById(R.id.extinction_imageView);
            extinctionName = (TextView) itemView.findViewById(R.id.extinction_name);
            itemExpandArea = (LinearLayout) itemView.findViewById(R.id.item_expand_area);
        }
    }
}
