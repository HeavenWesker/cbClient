package com.catchingnow.evangelion.unit00;

import android.os.AsyncTask;
import android.util.Log;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;


import java.util.List;

/**
 * Created by Heaven on 3/24/15.
 */
public class Tumblr {
    public void init(){
        new NetworkTask().execute();
    }

    private class NetworkTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("NetworkTask ", "started");
            JumblrClient client = new JumblrClient("ZbdKV2V9Yhh9FswZ3FGE8BwbtY87o1nC8v5hiyGBGLYZfuZJJY",
                    "MqRXtzN1JwOM0XtYCWxTB2MCNEV5F0VaMVoeop7gEq8gyymGra");
            client.setToken("yQbI0L5QQPxSBaoiOKHYFc1AOjsH8WQsSv8NLoU7CC5BUcNYwK",
                    "krW6sB0oeqt9N1Be6Q7laAwxxsox1f5t4Qo7kJ6wnVINoEUWAs");
            User user = client.user();
            Log.d("User Name: ", user.getName());
            List<Post> posts = client.userDashboard();
            Log.d("Posts count = ", ""+posts.size());
            return null;
        }
    }

}
