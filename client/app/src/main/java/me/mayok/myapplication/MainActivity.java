package me.mayok.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DEBUG_TAG = "HttpExample";
    private static String mURL = "https://www.mayok.me";
    private TextView partnersFuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button postLetter = (Button)findViewById(R.id.post_letter);
        final Button getLetter = (Button)findViewById(R.id.get_letter);
        partnersFuman = (TextView)findViewById(R.id.partners_fuman);

        postLetter.setOnClickListener(this);
        getLetter.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        HTTPUtils httpUtils = new HTTPUtils(MainActivity.this);

        switch(v.getId()) {
            case R.id.get_letter:
                if(httpUtils.isOnline()) {
                    new DownloadTask().execute(mURL);
                }
                break;
            case R.id.post_letter:
                break;
        }
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadFrom(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            partnersFuman.setText(result);
        }

        private String downloadFrom(String mURL) throws IOException {
            InputStream is = null;

            int len = 10;

            try {
                URL url = new URL(mURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // Reads an InputStream and converts it to a String.
        private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }
    }
}
