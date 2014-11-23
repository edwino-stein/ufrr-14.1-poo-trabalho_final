package com.correios.edwinos.consultacorreios.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by EdwinoS on 23/11/14.
 */
public class Dialog {

    public static void alertDialog(Context context, String title, String textBody){
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(textBody)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public static void questionDialog(final DialogResult context, final int questionIndex, String title, String textBody){

        new AlertDialog.Builder((Context) context).setTitle(title)
                .setMessage(textBody)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.onDialogResult(questionIndex, true);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.onDialogResult(questionIndex, false);
                        dialog.cancel();
                    }
                })
                .show();
    }

    public interface DialogResult{
        public void onDialogResult(int index, boolean result);
    }
}
