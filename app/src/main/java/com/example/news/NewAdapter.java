/*
 * @author: Soumay Agarwal
 * @description: This class is known as the NewAdapter.
 *               It is enables us to use a custom adapter
 *               to set content for the listview in the
 *               ListviewFragment.
 */

package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NewAdapter extends ArrayAdapter<String> {

    private ArrayList<String> items;
    Context c;

    /*
     * This is the constructor which is responsible for setting the
     * providided items.
     */
    public NewAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
        super(context, textViewResourceId, items);
        c = context;
        this.items = items;
    }

    /*
     * This method is responsible for setting the provided website name
     * and author to the textview of the news_row which is used in the
     * listview of ListViewFragment.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.news_row, null);
        }
        String id = items.get(position);
        String[] sep = id.split(":");
        TextView iv = (TextView) v.findViewById(R.id.news_per_row);
        TextView iv2 = (TextView) v.findViewById(R.id.news_per_row2);
        if (iv != null) {
            iv.setText(sep[0]);
            iv2.setText(sep[1]);
        }
        return v;
    }
}
