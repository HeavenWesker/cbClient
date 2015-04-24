package com.catchingnow.evangelion.unit00;

import android.os.AsyncTask;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

/**
 * Created by Heaven on 4/24/15.
 */
public class UpdateAsyncTask extends AsyncTask<URL, Void, ArrayList<RssItem>> {
    @Override
    protected ArrayList<RssItem> doInBackground(URL... params) {
        ArrayList<RssItem> rssItems = new ArrayList<>();
        for (URL url : params) {
//            try {
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setReadTimeout(10000);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line;
//                StringBuilder builder = new StringBuilder();
//                while ((line = reader.readLine()) != null){
//                    builder.append(line);
//                }
//                connection.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                RssFeed feed = RssReader.read(url);
                rssItems.addAll(feed.getRssItems());
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rssItems;
    }
}
