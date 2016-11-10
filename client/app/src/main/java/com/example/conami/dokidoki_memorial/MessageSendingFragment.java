package com.example.conami.dokidoki_memorial;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by matsushita on 2016/11/08.
 */

public class MessageSendingFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * default constructor
     */
    public MessageSendingFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MessageSendingFragment newInstance(int sectionNumber) {
        MessageSendingFragment fragment = new MessageSendingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_messages_sending, container, false);

        /* call when layout touched */
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout_sending_message);
        layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event){
                closeSoftwareKeyboard(view);
                return false;
            }

        });


        Button buttonSendMessage = (Button)rootView.findViewById(R.id.button_send);
        buttonSendMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /* close software keyboard */
                closeSoftwareKeyboard(view);

                /* get text from edit text box */
                EditText editTextTitle = (EditText)rootView.findViewById(R.id.edit_text_sending_title);
                EditText editTextMessage = (EditText)rootView.findViewById(R.id.edit_text_sending_message);
                String title   = editTextTitle.getText().toString();
                String message = editTextMessage.getText().toString();

                /* send http request */
                String json =
                    "{\"frustrations\": [ {\"title\": \""+title+"\",\"message\": \""+message+"\",\"value\": 0}]}";
                Log.d("json",json);
                HttpRequest httpRequest = new HttpRequest();
                httpRequest.post("http://210.140.69.130/api/v1/users/1/frustrations.json",json);
            }

        });


        return rootView;
    }

    void closeSoftwareKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
