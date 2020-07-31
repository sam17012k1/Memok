package com.example.memok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {
    private MediaPlayer mplayer;
    private MediaPlayer.OnCompletionListener mcompletion= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            release_mplayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        release_mplayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                        // Pause playback
                        mplayer.pause();
                        mplayer.seekTo(0);
                    }  else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mplayer.start();
                    }
                }
            };
    private AudioManager aPlayer;


    public ColorsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.activity_colors, container, false);
        aPlayer = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> colors_list= new ArrayList<>();
        colors_list.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        colors_list.add(new Word("mustard yellow","chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        colors_list.add(new Word("dusty yellow","topiise",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colors_list.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        colors_list.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        colors_list.add(new Word("black","kulluli",R.drawable.color_black,R.raw.color_black));
        colors_list.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        colors_list.add(new Word("white","kelleli",R.drawable.color_white,R.raw.color_white));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), colors_list,R.color.category_numbers);

        ListView listView = (ListView) view.findViewById(R.id.list_colors);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Word current= colors_list.get(i);
                release_mplayer();

// Request audio focus for playback
                int result = 0;

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mplayer= MediaPlayer.create(getActivity(),current.getSound_id());
                    mplayer.start();
                    mplayer.setOnCompletionListener(mcompletion);
                }
                mplayer= MediaPlayer.create(getActivity(),current.getSound_id());
                mplayer.start();
                mplayer.setOnCompletionListener(mcompletion);

            }
        });
        return view;
    }
    public void onStop() {
        super.onStop();
        release_mplayer();
    }
    private void release_mplayer(){
        if(mplayer!=null){
            mplayer.release();
            mplayer=null;
        }
    }
}