package com.example.conami.dokimemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
/**
 * Created by matsushita on 2016/11/12.
 */

public class HowToCommunicateDialogFragment extends DialogFragment {
    ArrayList<HowTo> howTos = new ArrayList<HowTo>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        howTos.add(new HowTo("First Step",  "不満の原因を見極めよう"));
        howTos.add(new HowTo("Second Step", "どうして欲しかったのかを伝えよう"));
        howTos.add(new HowTo("Therd Step",  "日頃の感謝も一緒に伝えよう"));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = new Dialog(builder.getContext());
        dialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(
                LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.how_to_communicate_dialog, null);
        ListView listView = (ListView) layout.findViewById(R.id.how_to_list);
        HowToAdapter adapter = new HowToAdapter(getActivity());
        adapter.setHowTo(howTos);
        listView.setAdapter(adapter);
        builder.setTitle("不満を伝える３ステップ!!");
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* 無理やり呼び出し元のフラグメントを取得 */
                MessageSendingFragment target = (MessageSendingFragment)getTargetFragment();
                if(target==null){
                    Log.d("error","target is null point");
                }
                target.onOkClick();
                // OK button pressed
            }
        });
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }

    @Override
    public void onPause(){
        super.onPause();

        // onPause でダイアログを閉じる場合
        dismiss();
    }

    public class HowTo{
        long id;
        String title;
        String content;

        HowTo(String title, String content){
            this.setTitle(title);
            this.setContent(content);
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    public class HowToAdapter extends BaseAdapter{
        Context context;
        LayoutInflater layoutInflater = null;
        ArrayList<HowToCommunicateDialogFragment.HowTo> howTos;
        public HowToAdapter(Context context) {
            this.context = context;
            this.layoutInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public void setHowTo(ArrayList<HowTo> howTos) {
            this.howTos = howTos;
        }

        @Override
        public int getCount() {
            return howTos.size();
        }

        @Override
        public Object getItem(int position) {
            return howTos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return howTos.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.how_to_communicate_dialog_row,parent,false);

            ((TextView)convertView.findViewById(R.id.how_to_title)).setText(howTos.get(position).getTitle());
            ((TextView)convertView.findViewById(R.id.how_to_content)).setText(howTos.get(position).getContent());

            return convertView;
        }
    }
}
