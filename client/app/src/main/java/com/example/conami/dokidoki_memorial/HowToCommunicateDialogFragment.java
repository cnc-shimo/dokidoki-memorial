package com.example.conami.dokidoki_memorial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by matsushita on 2016/11/12.
 */

public class HowToCommunicateDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle("タイトル")
                .setMessage("メッセージ")
                .create();
    }

    @Override
    public void onPause(){
        super.onPause();

        // onPause でダイアログを閉じる場合
        dismiss();
    }
}
