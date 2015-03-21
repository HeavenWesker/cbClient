package com.catchingnow.cbclient;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Heaven on 3/20/15.
 */
public class NetWork {
    public String getData(){
        new DownloadTask().execute("");
        return "SS";
    }
    private class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://www.cnbeta.com/");
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String buffer;
                String result = "";
                while ((buffer = reader.readLine()) != null){
                    result += buffer;
                }
                //Log.d("the result is :", result);
                return result;
            } catch (IOException e) {
                Log.d("ERROR", "ERROR");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Result", s);
        }
    }
}
