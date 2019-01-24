package com.arintv.conlabs.witopentrack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.view.Display;
import android.view.Window;
import android.widget.Toast;

public class App_Extra_Resources {

    private Activity rootActivity;

    public App_Extra_Resources(Activity activity) {
        rootActivity = activity;
    }

    public void setCustomDialogSize(Dialog dialog, float x_multiple, float y_multiple) {
        Display display = rootActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Window window = dialog.getWindow();

        int width = (int)(size.x * x_multiple);
        int height = (int)(size.y * y_multiple);

        try {
            window.setLayout(width, height);
        } catch (NullPointerException e) {
            Toast.makeText(rootActivity, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    public void callGooglePlayAppLink(String MarketAddress, int StringID) {
        AlertDialog.Builder GooglePlayLinkAlert = new AlertDialog.Builder(rootActivity);
        GooglePlayLinkAlert.setTitle(R.string.extra_resources_GP_Link_title);
        GooglePlayLinkAlert.setMessage(StringID);

        GooglePlayLinkAlert.setPositiveButton(R.string.accept, ((dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MarketAddress));
            intent.setPackage("com.android.vending");
            rootActivity.startActivity(intent);
        }));
        GooglePlayLinkAlert.setNegativeButton(R.string.cancel, ((dialog, which) -> {}));
        GooglePlayLinkAlert.create().show();
    }

}
