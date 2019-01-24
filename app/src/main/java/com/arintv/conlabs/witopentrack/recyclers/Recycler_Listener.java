package com.arintv.conlabs.witopentrack.recyclers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

// RecyclerView의 리스너는 터치 영역을 구해 해당 아이템의 터치 영역을 체크합니다.

public class Recycler_Listener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener itemClickListener;

    // Recycler 리스너 구현에 사용할 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 터치 아이템 리스너에 사용할 GestureDetector, 1회 Single 터치만 체크할 예정
    GestureDetector itemGestureDetector;

    public Recycler_Listener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        itemClickListener = listener;
        itemGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    // 터치 영역 체크: RecyclerView 부분만 리스너가 감지하도록
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if(childView != null && itemClickListener != null && itemGestureDetector.onTouchEvent(motionEvent)) {
            itemClickListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {}

}
