/*
 * @author: Soumay Agarwal
 * @description: This class is known as the PreviewFragment.
 *               It is the fragment which sets a title, the content and
 *               a button to move to the webview.
 */
package com.example.news;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class PreviewFragment extends Fragment {
    public AdapterView.OnItemClickListener container = null;

    /*
     * Empty constructor.
     */

    public PreviewFragment() {
    }

    /*
     * This method sets the container activity.
     */

    public void setContainerActivity(AdapterView.OnItemClickListener containerActivity) {
        this.container = containerActivity;
    }

    /*
     * This method is the on create view method
     * which displays the content. Here we are setting
     * the onclick of the button to open webview and
     * setting the UI elements with their appropriate
     * values
     *
     * @return v is a View object
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_preview, container, false);
        Bundle b = getArguments();
        String[] name = b.getStringArray("the_list");
        TextView textView = (TextView) v.findViewById(R.id.prev_tv1);
        TextView textView2 = (TextView) v.findViewById(R.id.prev_tv2);
        textView.setText(name[0]);
        textView2.setText(name[1]);
        Button button = (Button) v.findViewById(R.id.prev_but);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                WebviewFragment frag = new WebviewFragment();
                frag.setContainerActivity(this);
                Bundle bundle = new Bundle();
                bundle.putString("goweb", name[2]);
                frag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.ll1, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }

}