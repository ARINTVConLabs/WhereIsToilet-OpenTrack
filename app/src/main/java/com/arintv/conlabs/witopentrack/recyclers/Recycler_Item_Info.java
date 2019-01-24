package com.arintv.conlabs.witopentrack.recyclers;

// 직렬화 클래스: Recycler_Item_Info
// RecyclerView의 어댑터에서 사용할 클래스

import java.io.Serializable;

public class Recycler_Item_Info implements Serializable {

    public String exTitle; // 제목
    public String exSubtitle; // 부제목

    public Recycler_Item_Info(String exTitle, String exSubtitle) {
        this.exTitle = exTitle;
        this.exSubtitle = exSubtitle;
    }
}
