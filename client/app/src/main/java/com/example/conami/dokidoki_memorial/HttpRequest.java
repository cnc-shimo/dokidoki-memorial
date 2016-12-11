package com.example.conami.dokidoki_memorial;

/**
 * Created by matsushita on 2016/11/01.
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.util.Log;
import android.os.AsyncTask;
import android.widget.TextView;

public class HttpRequest extends AsyncTask<String, Void, String> {
    private static FuncInterface func = null;
    private static TextView textView = null;

    HttpRequest(){
        this.setFunc(null);
        this.setTextView(null);
    }

    public void setTextView(TextView textView){
        this.textView = textView;
    }

    public void setFunc(FuncInterface func){
        this.func = func;
    }

    public static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String lines = "";
        String line = "";
        while (line != null){
            line = bufferedReader.readLine();
            lines += line;
        }
        return lines;
    }

    public void get(String url){
        this.execute("GET",url);
    }

    public void post(String url, String json){
        this.execute("POST",url, json);
    }

    @Override
    protected String doInBackground(String... param) {  //method, url, json を引数に指定
        if(param[0] == "GET"){
            Log.d("LOG","ONGET");
            String url = param[1];
            return HttpRequest._get(url);
        }else if(param[0] == "POST"){
            String url  = param[1];
            String json = param[2];
            if(HttpRequest._post(url,json)){
                return "";
            }
            return "";
        }
        return "HttpResuest:ERROR!";
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        if(this.func != null)
            this.func.func(this.textView,result);
    }

    private static String _get(String surl){
        InputStream is = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL(surl);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000 /* milliseconds */);
            con.setConnectTimeout(15000 /* milliseconds */);
            con.setRequestMethod("GET");
            con.setDoInput(true);

            // Starts the query
            con.connect();
            int response = con.getResponseCode();
            Log.d("HTTP GET REQ", "The response is: " + response);
            is = con.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            con.disconnect();
            return contentAsString;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e){
                }
            }
            if(con != null){
                con.disconnect();
            }
        }
        return "ERROR!";
    }

    // 動作未確認
    private static boolean _post(String surl, String json) {
        OutputStream os = null;
        PrintStream ps = null;
        HttpURLConnection con = null;
        try {
            String buffer = "";
            URL url = new URL(surl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Accept-Language", "jp");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            os = con.getOutputStream();
            ps = new PrintStream(os);
            ps.print(json);
            ps.close();

            con.connect();
            Log.d("HTTP POST REQ","The response is:"+con.getResponseCode());
            Log.d("JSON",json);

            /*
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            buffer = reader.readLine();

            JSONArray jsonArray = new JSONArray(buffer);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("HTTP REQ", jsonObject.getString("title"));
            }
            */
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }else if(os != null) {
                try {
                    os.close();
                } catch (IOException e){
                }
            }
            if(con != null){
                con.disconnect();
            }
        }
        return false;
    }
}
