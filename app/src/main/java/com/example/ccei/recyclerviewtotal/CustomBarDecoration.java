package com.example.ccei.recyclerviewtotal;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ccei on 2016-07-11.
 */
public class CustomBarDecoration extends RecyclerView.ItemDecoration {
    private int columnCount;
    private int columnHeight;

    public CustomBarDecoration(int columnCount, int columnHeight) {
        this.columnCount = columnCount;
        this.columnHeight = columnHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildAdapterPosition(view) < columnCount){
            outRect.set(0, columnHeight, 0, 0);
        }
        else{
            outRect.set(0, 0, 0, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
