package com.example.conami.dokimemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.app.Activity;
import android.widget.SeekBar;
import android.widget.Toast;


public class MessageSendingFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public MessageSendingFragment() {}

    public static MessageSendingFragment newInstance(int sectionNumber) {
        MessageSendingFragment fragment = new MessageSendingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_messages_sending, container, false);

        /* call when layout touched */
        LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.layout_sending_message);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                closeSoftwareKeyboard(view);
                return false;
            }
        });

        /* call when send-button tapped */
        Button buttonSendMessage = (Button)rootView.findViewById(R.id.button_send);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextTitle   = (EditText)getActivity().findViewById(R.id.edit_text_sending_title);
                EditText editTextMessage = (EditText)getActivity().findViewById(R.id.edit_text_sending_message);
                String title   = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();

                if (title.equals("")) {
                    new AlertDialog.Builder(getActivity()).setTitle("").setMessage("タイトルを入力してください").show();
                    return;
                }

                if(message.equals("")) {
                    new AlertDialog.Builder(getActivity()).setTitle("").setMessage("メッセージを入力してください").show();
                    return;
                }

                /* close software keyboard */
                closeSoftwareKeyboard(view);

                /* show HotToCommunicateDialogFragment */
                Bundle bundle = new Bundle();
                HowToCommunicateDialogFragment howToCommunicateFragment = new HowToCommunicateDialogFragment();
                howToCommunicateFragment.setArguments(bundle);
                howToCommunicateFragment.setTargetFragment(MessageSendingFragment.this, 0);
                howToCommunicateFragment.show(getFragmentManager(), "how_to_dialog");
            }
        });

        return rootView;
    }

    /* call when ok-button tapped */
    public void onOkClick() {

        //SeekBer
        SeekBar seekBar = (SeekBar) getActivity().findViewById(R.id.FrustrationBar);
        int prg = seekBar.getProgress() - 5;
        String value = String.valueOf(prg);

        /* get text from edit text box */
        EditText editTextTitle = (EditText) getActivity().findViewById(R.id.edit_text_sending_title);
        EditText editTextMessage = (EditText) getActivity().findViewById(R.id.edit_text_sending_message);
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        /* send http request */
        String json = "{\"frustrations\": [{\"title\": \"" + title + "\",\"message\": \"" + message + "\",\"value\": \"" + value + "\"}]}";
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.post("http://210.140.70.106/api/v1/users/" + UserModel.getId() + "/frustrations.json", json);

        /* clear edit text box */
        editTextTitle.getEditableText().clear();
        editTextMessage.getEditableText().clear();

        /* show dialog */
        new AlertDialog.Builder(getActivity()).setTitle("").setMessage("送信しました").show();
    }

    /* close software keyboard */
    void closeSoftwareKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
