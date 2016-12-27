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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.zip.DataFormatException;

public class FrustrationListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public FrustrationListFragment() {}

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_frustration_list, container, false);

        /**
         * 引っ張って更新するSwipeRefreshLayoutを作成
         */
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        messages = new ArrayList<FrustrationMessage>();
        updateFrustrationList();

        if (messages.size() == 0) {
            FrustrationMessage frustrationMessage = new FrustrationMessage();
            frustrationMessage.setTitle("メッセージを送ってもらおう！！");
            frustrationMessage.setMessage("下に引っ張るとメッセージを更新！！");

            DateFormat df =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.XXX", Locale.JAPAN);
            java.util.Date d = new java.util.Date();
            frustrationMessage.setDate(df.format(d));

            messages.add(frustrationMessage);
        }

        listView = (ListView)rootView.findViewById(R.id.frustration_list);
        adapter = new FrustrationMessageAdapter(getActivity());
        adapter.setMessages(messages);
        listView.setAdapter(adapter);
        return rootView;
    }

    public void updateFrustrationList() {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setFunc(new FuncInterface() {
            @Override
            public void func(TextView view, String json) {
                messages.clear();

                try {
                    Log.d("json",json);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray frustrations = jsonObject.getJSONArray("frustrations");
                    for (int i = 0; i < frustrations.length(); i++) {
                        FrustrationMessage frustrationMessage = new FrustrationMessage();
                        frustrationMessage.setId(i);
                        frustrationMessage.setTitle(frustrations.getJSONObject(i).getString("title"));
                        frustrationMessage.setMessage(frustrations.getJSONObject(i).getString("message"));
                        frustrationMessage.setDate(frustrations.getJSONObject(i).getString("created_at"));
                        frustrationMessage.setValue(frustrations.getJSONObject(i).getInt("value"));
                        messages.add(0, frustrationMessage);
                    }
                } catch(JSONException e) {
                    Log.e("JSONExeption", e.toString());
                }

                adapter.notifyDataSetChanged();
            }
        });

        String url = "http://210.140.70.106/api/v1/users/" + UserModel.getId() + "/frustrations.json";
        httpRequest.get(url);
    }

    @Override
    public void onRefresh() {
        updateFrustrationList();
        adapter.notifyDataSetChanged();

        // 更新が終了したらインジケータ非表示
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public class FrustrationMessage {
        long id;
        String title;
        String message;
        String date;
        int value;

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

        public String getDate() { return date; };

        public void setDate(String date) {
            this.date = date;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
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


            DateFormat srcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.XXX", Locale.JAPAN);
            DateFormat destFormat = new SimpleDateFormat("yyyy'/'MM'/'dd", Locale.JAPAN);
            try {
                Date res = srcFormat.parse(messages.get(position).getDate());
                ((TextView) convertView.findViewById(R.id.list_frustration_date)).setText(destFormat.format(res));
            } catch (ParseException e) {
                Log.d("DateFormat", e.toString());
            }


            Integer value = messages.get(position).getValue();
            ImageView icon1 =  (ImageView)convertView.findViewById(R.id.list_frustration_icon);
            ImageView icon2 =  (ImageView)convertView.findViewById(R.id.list_frustration_icon2);
            ImageView icon3 =  (ImageView)convertView.findViewById(R.id.list_frustration_icon3);

            if(value > 0) {
                if (value > 7) {
                    icon3.setImageResource(R.drawable.ic_bomb_icon);
                }
                if(value > 4) {
                    icon2.setImageResource(R.drawable.ic_bomb_icon);
                }
                icon1.setImageResource(R.drawable.ic_bomb_icon);
            } else {
                if(value < -7) {
                    icon3.setImageResource(R.drawable.ic_heart);
                }
                if(value < -4) {
                    icon2.setImageResource(R.drawable.ic_heart);
                }
                icon1.setImageResource(R.drawable.ic_heart);
            }

            return convertView;
        }
    }
}