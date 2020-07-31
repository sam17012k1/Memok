package com.example.memok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int color_id=0;
    public WordAdapter( Activity context, ArrayList<Word> all_words,int color_id) {
        super(context,0, all_words);
    this.color_id=color_id;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Word currentword=getItem(position);
        View final_view = convertView;
        if(final_view==null){
           final_view= LayoutInflater.from(getContext()).inflate(
                    R.layout.user_layout, parent, false);
        }
        TextView english= (TextView) final_view.findViewById(R.id.english_word);
        english.setText(currentword.getDefaults());
        TextView memok=(TextView) final_view.findViewById((R.id.memok_translation));
        memok.setText(currentword.getMemok());
        if(currentword.getI()!=0) {


            ImageView images = (ImageView) final_view.findViewById(R.id.image_view_images);
            images.setImageResource(currentword.getImage_id());
        }else{
            ImageView images = (ImageView) final_view.findViewById(R.id.image_view_images);
            images.setVisibility(View.GONE);
        }
        int color = ContextCompat.getColor(getContext(), color_id);
        LinearLayout linearLayout = (LinearLayout) final_view.findViewById(R.id.text_container);
        linearLayout.setBackgroundColor(color);
        return final_view;
    }
}
