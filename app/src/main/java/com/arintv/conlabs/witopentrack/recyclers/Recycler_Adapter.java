package com.arintv.conlabs.witopentrack.recyclers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arintv.conlabs.witopentrack.R;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // recycler_items_row.xml의 TextView를 불러와 사용할 홀더
    public static class Recycler_Holder extends RecyclerView.ViewHolder {
        TextView ListexTitle;
        TextView ListexSubtitle;

        Recycler_Holder(View view) {
            super(view);
            ListexTitle = view.findViewById(R.id.RecyclerViewItemTitle);
            ListexSubtitle = view.findViewById(R.id.RecyclerViewItemSubTitle);
        }
    }

    // Recycler_Adapter에 사용할 ArrayList
    private ArrayList<Recycler_Item_Info> recyclerItemInfos;
    public Recycler_Adapter(ArrayList<Recycler_Item_Info> recyclerItemInfos){
        this.recyclerItemInfos = recyclerItemInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_items_row, viewGroup, false);
        return new Recycler_Holder(view);
    }

    // 홀더에 지정한 아이템과 연결
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Recycler_Holder recyclerHolder = (Recycler_Holder) holder;

        recyclerHolder.ListexTitle.setText(recyclerItemInfos.get(position).exTitle);
        recyclerHolder.ListexSubtitle.setText(recyclerItemInfos.get(position).exSubtitle);
    }

    @Override
    public int getItemCount() {
        return recyclerItemInfos.size();
    }
}
