package me.mayok.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mayok on 2016/10/27.
 */

public class MainFragment extends Fragment {

    private MainFragment self = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final Button getLetter = (Button) rootView.findViewById(R.id.get_letter);
        final FloatingActionButton composeLetter =
                (FloatingActionButton) rootView.findViewById(R.id.compose_letter);


        // when the getLetter button is clicked
        getLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                HTTPUtils httpUtils = new HTTPUtils(context);
                if (httpUtils.isOnline()) {
                    new DownloadTask().execute(
                            context.getResources().getString(R.string.API_URL)
                    );
                }
            }
        });

        // when the composeLetter button is clicked
        composeLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ComposeLetterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;

    }
}
