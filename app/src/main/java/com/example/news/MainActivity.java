/*
 * @author: Soumay Agarwal
 * @description: This program is known as the News.
 *               It has the functionality of displaying
 *               a title, an edit text which gets the search term,
 *               a search button that searches various news articles for a particular
 *               topic and a list view that contains the news website name and its author.
 *               If a user clicks on an article he is guided to another view
 *               which displays the article title, a previous of the articletext,
 *               and a button that can be pressed to take user to the webview of that
 *               article for that website.
 */
package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     * This method is the on create method
     * which sets the content view and
     * displays the content. Here we are creating
     * a new ListviewFragment which takes over the
     * parent or view.
     *
     * @param savedInstanceState is a bundle which saves the state of activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListviewFragment frag1 = new ListviewFragment();
        frag1.setContainerActivity(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ll1, frag1);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}