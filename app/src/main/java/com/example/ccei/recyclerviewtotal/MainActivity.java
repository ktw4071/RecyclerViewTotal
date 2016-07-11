package com.example.ccei.recyclerviewtotal;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private CollapsingSwipeRefreshLayout swiper;
    private RecyclerView mRecycler;

    private StaggeredGridLayoutManager mSGLM;
    private LinearLayout customBar;
    private TextView customTitle;

    private ExtinctionCellAdapter mAdapter;

    private ArrayList<Integer> itemsOfData;
    private Animation inAnim;
    private Animation outAnim;

    private int columnCount;
    private int customBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_in_top);
        outAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_out_top);
        customBar = (LinearLayout) findViewById(R.id.custom_view_bar);

        columnCount = 2;

        TypedValue typeValue = new TypedValue();
        if (getTheme().resolveAttribute(R.attr.actionBarSize, typeValue, true)){
            customBarHeight = TypedValue.complexToDimensionPixelSize(typeValue.data, getResources().getDisplayMetrics());
        }

        customTitle = (TextView) findViewById(R.id.custom_title);

        swiper = (CollapsingSwipeRefreshLayout) findViewById(R.id.swipe_container);
        swiper.setSize(SwipeRefreshLayout.LARGE);
        swiper.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if(itemsOfData != null && mAdapter != null){
                            Collections.shuffle(itemsOfData);
                            mAdapter.notifyDataSetChanged();
                        }
                        swiper.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        mRecycler = (RecyclerView) findViewById(R.id.zig_zag_recycler_view);

        mRecycler.addItemDecoration(new CustomBarDecoration(columnCount, customBarHeight));

        mSGLM = new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);

        mRecycler.setLayoutManager(mSGLM);

        itemsOfData = RandomArrayList.getShuffleArrayList();

        mAdapter = new ExtinctionCellAdapter(this, itemsOfData);

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 3){
                    if(customBar.getVisibility() == View.VISIBLE)
                        hideCustomBar();
                }

                else if(dy < -3){
                    if(customBar.getVisibility() == View.GONE)
                        showCustomBar();
                }
            }
        });

        mRecycler.setAdapter(mAdapter);
        swiper.setTargetMyScrollableView(mRecycler);
    }

    private void showCustomBar() {
        customBar.startAnimation(outAnim);
        customBar.setVisibility(View.GONE);
    }

    private void hideCustomBar() {
        customBar.startAnimation(inAnim);
        customBar.setVisibility(View.VISIBLE);
    }

//    public void addItemAtPosition(int position, String item){
//        itemsOfData.add(position, item);
//        mAdapter.notifyItemInserted(position);
//        mSGLM.scrollToPosition(position);
//    }
}
