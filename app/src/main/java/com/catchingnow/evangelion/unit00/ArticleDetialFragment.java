package com.catchingnow.evangelion.unit00;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catchingnow.cbclient.R;

/**
 * Created by Heaven on 4/27/15.
 */
public class ArticleDetialFragment extends Fragment {
    TextView titleTextView;
    TextView contentTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        titleTextView = (TextView) view.findViewById(R.id.title);
        contentTextView = (TextView) view.findViewById(R.id.content);
        return view;
    }

    public void setTitle(String title){
        titleTextView.setText(title);
    }

    public void setContent(String content){
        contentTextView.setText(content);
    }

}
