package com.hhtxproject.piafriendscollege.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Respectupper on 2018/6/6.
 */

public class DialogExit {

    public DialogExit(Activity activity){
        dialog_Exit(activity);
    }
    private void dialog_Exit(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        android.os.Process.killProcess(android.os.Process
//                                .myPid());
                        activity.finish();
                    }
                });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        if(!activity.isFinishing())
        {
            builder.create().show();
        }
    }
}
