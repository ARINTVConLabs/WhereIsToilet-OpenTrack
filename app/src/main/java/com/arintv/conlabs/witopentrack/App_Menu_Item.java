package com.arintv.conlabs.witopentrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.arintv.conlabs.witopentrack.examples.Activity_Example_GPSTrack;
import com.arintv.conlabs.witopentrack.examples.Activity_Example_LocationManager;
import com.arintv.conlabs.witopentrack.examples.Activity_Example_MapLevel;
import com.arintv.conlabs.witopentrack.examples.Activity_Example_URLScheme;
import com.arintv.conlabs.witopentrack.recyclers.Recycler_Adapter;
import com.arintv.conlabs.witopentrack.recyclers.Recycler_Listener;
import com.arintv.conlabs.witopentrack.recyclers.Recycler_item_List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.ContentValues.TAG;
import static com.kakao.util.maps.helper.Utility.getPackageInfo;

public class App_Menu_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_menu);

        callRecyclerView();
    }

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder ExitDialog = new android.app.AlertDialog.Builder(this);
        ExitDialog.setTitle(R.string.exitdialog_title);
        ExitDialog.setMessage(R.string.exitdialog_message);
        ExitDialog.setPositiveButton(R.string.accept, ((dialog, which) -> {
            finishAffinity(); // 최상위 액티비티 종료
        }));
        ExitDialog.setNegativeButton(R.string.cancel, ((dialog, which) -> {}));
        ExitDialog.create().show();
    }

    // RecyclerView 적용
    private void callRecyclerView() {
        RecyclerView MenuRecyclerView = findViewById(R.id.ItemRecycler);
        RecyclerView.LayoutManager MenuRecyclerLayoutManager;
        MenuRecyclerView.setHasFixedSize(true);
        MenuRecyclerLayoutManager = new LinearLayoutManager(this);
        MenuRecyclerView.setLayoutManager(MenuRecyclerLayoutManager);

        Recycler_Adapter recyclerAdapter = new Recycler_Adapter(new Recycler_item_List().MenuItems());
        MenuRecyclerView.setAdapter(recyclerAdapter);

        // Recycler ItemListener 추가
        MenuRecyclerView.addOnItemTouchListener(new Recycler_Listener(App_Menu_Item.this, MenuRecyclerView, ((view, position) -> {
            Toast.makeText(App_Menu_Item.this, position+"번 아이템 클릭", Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0: setIntent(Activity_Example_GPSTrack.class); break;
                case 1: setIntent(Activity_Example_MapLevel.class); break;
                case 2: setIntent(Activity_Example_LocationManager.class); break;
                case 5: setIntent(Activity_Example_URLScheme.class); break;
                case 7: getKeyHash(); break;
                default:
            }
        })));
    }

    private void setIntent(Class AppClass) {
        Intent intent = new Intent(this, AppClass);
        startActivity(intent);
    }


    // 키 해시 구하기: 26.1 이상 작동 불가 (Deprecated)
    private void getKeyHash() {
        AlertDialog.Builder KeyHashAlert = new AlertDialog.Builder(this);
        KeyHashAlert.setTitle(R.string.keyhasidialog_title);
        KeyHashAlert.setMessage("키 해시값: " + ApplicationKeyHash(this));
        KeyHashAlert.setPositiveButton(R.string.confirm, ((dialog, which) -> {})).create().show();

        Log.d("KeyHash", ApplicationKeyHash(this));
    }

    // 카카오에서 제공하는 키 해시 출력 메소드
    @SuppressWarnings("Deprecated")
    private static String ApplicationKeyHash(final Context context) { // 다음에서 제공하는 구형 메서드
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // 오레오 버전 미만라면 돌아가게 함 (8.0 미만, Android Nougat까지)
            PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
            if (packageInfo == null)
                return null;

            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
                }
            }
        }
        return null;
    }
}
