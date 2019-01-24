package com.arintv.conlabs.witopentrack;

/* 화장실 어디야? 오픈 트랙 / BSD 3-Clause License Adapted
 * 2019. 01. 13. 프로젝트 시작
 *
 * 이 소스는 화장실 어디야? 앱의 소스 중 공개 가능한 부분을 오픈 소스로 공개합니다.
 * 소스 및 목표 호환성은 안드로이드 기본 1.6이 아닌 1.8을 기반으로 작업합니다.
 * Kotlin을 이용하기 위해서는 1.6버전으로 낮추고 람다식을 수정한 뒤 "Convert Java File to Kotlin File"을 실행하기 바랍니다.
 *
 * Kakao에서 제공하고 있는 Daum Maps API는 ARM 계열 CPU 에서만 동작합니다.
 * PC에서 실행시 기본 에뮬레이터가 아닌 상용 앱 플레이어나 실물 기계를 이용해 확인하시기 바랍니다.
 *
 * 2019 (C) ARINTV Contents Creation Labs.
 * 2018 (C) Team Code Rangers
 * */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class App_Activity_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        Button MenuStart = findViewById(R.id.AppStartButton);

        MenuStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, App_Menu_Item.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}
