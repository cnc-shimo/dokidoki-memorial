package com.example.conami.dokimemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by matsushita on 2016/11/08.
 */

public class FrustrationListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public FrustrationListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FrustrationListFragment newInstance(int sectionNumber) {
        FrustrationListFragment fragment = new FrustrationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    ListView listView;
    View rootView;
    FrustrationMessageAdapter adapter;
    ArrayList<FrustrationMessage> messages;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_frustration_list, container, false);

        /**
         * 引っ張って更新するSwipeRefreshLayoutを作成
         */
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        // 色指定
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        messages = new ArrayList<FrustrationMessage>();
        updateFrustrationList();
        if(messages.size() == 0){
            FrustrationMessage frustrationMessage =
                    new FrustrationMessage();
            frustrationMessage.setTitle("メッセージを送ってもらおう！！");
            frustrationMessage.setMessage("下に引っ張るとメッセージを更新！！");
            messages.add(frustrationMessage);
        }
        listView = (ListView) rootView.findViewById(R.id.frustration_list);
        adapter = new FrustrationMessageAdapter(getActivity());
        adapter.setMessages(messages);
        listView.setAdapter(adapter);
        return rootView;
    }

    public void updateFrustrationList(){
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setFunc(new FuncInterface() {
            @Override
            public void func(TextView view, String json) { //引数のtextViewはめんどいけど書いてください．jsonはGETで取得したjsonの文字列．
            messages.clear();
            try{
                Log.d("json",json);
                JSONObject jsonObject  = new JSONObject(json);
                JSONArray frustrations = jsonObject.getJSONArray("frustrations");
                for (int i = 0; i < frustrations.length(); i++) {
                    FrustrationMessage frustrationMessage =
                            new FrustrationMessage();
                    frustrationMessage.setId(i);
                    frustrationMessage.setTitle(frustrations.getJSONObject(i).getString("title"));
                    frustrationMessage.setMessage(frustrations.getJSONObject(i).getString("message"));
                    messages.add(0, frustrationMessage); //先頭についか
                }
            }catch (JSONException e){
                Log.e("JSONExeption",e.toString());
            }
            adapter.notifyDataSetChanged();
            }
        });

        String url = "http://210.140.69.130/api/v1/users/"+ UserModel.getId()+"/frustrations.json";
        httpRequest.get(url);
        //httpRequest.execute("GET","http://www.google.co.jp");
        //textView.setText("aaaa");

    }

    /**
     * 引っ張った時によばれるメソッド
     */
    @Override
    public void onRefresh(){
        updateFrustrationList();
        adapter.notifyDataSetChanged();
        // 更新が終了したらインジケータ非表示
        mSwipeRefreshLayout.setRefreshing(false);
            /*
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 更新が終了したらインジケータ非表示
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            },10000);
            */
    }

    public class FrustrationMessage {
        long id;
        String title;
        String message;

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

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class FrustrationMessageAdapter extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater = null;
        ArrayList<FrustrationMessage> messages;

        public FrustrationMessageAdapter(Context context) {
            this.context = context;
            this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setMessages(ArrayList<FrustrationMessage> messages) {
            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return messages.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.frustration_list_row,parent,false);

            ((TextView)convertView.findViewById(R.id.list_frustration_title)).setText(messages.get(position).getTitle());
            ((TextView)convertView.findViewById(R.id.list_frustration_message)).setText(messages.get(position).getMessage());

            return convertView;
        }
    }


}