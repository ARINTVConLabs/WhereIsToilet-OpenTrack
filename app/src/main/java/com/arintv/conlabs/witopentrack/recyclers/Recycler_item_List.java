package com.arintv.conlabs.witopentrack.recyclers;

import java.util.ArrayList;

public class Recycler_item_List {

    // 메뉴 리스트
    public ArrayList<Recycler_Item_Info> MenuItems() {
        ArrayList<Recycler_Item_Info> item = new ArrayList<>();
        setItems(item);
        return item;
    }

    // 예제를 확인할 메뉴는 여기에 추가한다
    private void setItems(ArrayList<Recycler_Item_Info> items) {
        items.add(new Recycler_Item_Info("GPS 트래킹 모드", "Daum Maps API의 TrackingMode 활용"));
        items.add(new Recycler_Item_Info("지도 레벨 컨트롤", "Daum Maps API의 지도 줌/위·경도 설정 활용"));
        items.add(new Recycler_Item_Info("지도 위치 컨트롤", "GPS/WPS 데이터 활용"));
        items.add(new Recycler_Item_Info("기본 마커/커스텀 마커 활용", "Daum Maps API의 마커/커스텀 마커 활용"));
        items.add(new Recycler_Item_Info("커스텀 말풍선", "Daum Maps API의 커스텀 말풍선 활용"));
        items.add(new Recycler_Item_Info("카카오맵 URL 스키마 활용", "길찾기 기능 구현하기"));
        items.add(new Recycler_Item_Info("오픈소스 라이선스 정보/참고","참고한 소스의 라이선스 정보/개발자 주소"));
        items.add(new Recycler_Item_Info("키 해시 구하기","Base64로 코딩해 출력할 키 해시값 출력"));
    }
}
