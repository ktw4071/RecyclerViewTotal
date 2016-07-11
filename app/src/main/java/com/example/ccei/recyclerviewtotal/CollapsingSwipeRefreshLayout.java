package com.example.ccei.recyclerviewtotal;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ccei on 2016-07-11.
 */
public class CollapsingSwipeRefreshLayout extends SwipeRefreshLayout {
    private View myScrollableView = null;

    public CollapsingSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CollapsingSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTargetMyScrollableView(View view) {
        myScrollableView = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if(myScrollableView == null)
            return false;
        else{
            return ViewCompat.canScrollVertically(myScrollableView, -1);
        }
    }
}
