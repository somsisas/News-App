/*
 * @author: Soumay Agarwal
 * @description: This class is known as the WebviewFragment.
 *               It is the fragment which is responsible for
 *               opeining the url for the webarticle as a WebView.
 */
package com.example.news;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;


public class WebviewFragment extends Fragment {
    public View.OnClickListener container = null;

    /*
     * Empty constructor.
     */

    public WebviewFragment() {
    }

    /*
     * This method sets the container activity.
     */

    public void setContainerActivity(View.OnClickListener containerActivity) {
        this.container = containerActivity;
    }

    /*
     * This method is the on create view method
     * which displays the content. Here we are setting
     * the WebView for the news article.
     *
     * @return v is a View object
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_webview, container, false);
        WebView webView = (WebView) v.findViewById(R.id.thewebview);
        Bundle b = getArguments();
        String url = b.getString("goweb");
        webView.loadUrl(url);
        return v;
    }
}