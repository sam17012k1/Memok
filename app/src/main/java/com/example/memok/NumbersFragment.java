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


public class NumbersFragment extends Fragment {
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
    public NumbersFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_numbers, container, false);

        final ArrayList<Word> number_list= new ArrayList<>();
        aPlayer = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        number_list.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        number_list.add(new Word("two","otiiku",R.drawable.number_two,R.raw.number_two));
        number_list.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        number_list.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        number_list.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        number_list.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        number_list.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        number_list.add(new Word("eight","kaninta",R.drawable.number_eight,R.raw.number_eight));
        number_list.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        number_list.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));



        WordAdapter itemsAdapter = new WordAdapter(getActivity(), number_list,R.color.category_numbers);

        ListView listView = (ListView) view.findViewById(R.id.list_numbers);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word current= number_list.get(i);

                release_mplayer();
                int result = 0;

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mplayer= MediaPlayer.create(getActivity(),current.getSound_id());
                    mplayer.start();
                    mplayer.setOnCompletionListener(mcompletion);
                }
                mplayer = MediaPlayer.create(getActivity(), current.getSound_id());
                mplayer.start();

                mplayer.setOnCompletionListener(mcompletion);

            }

        });
        return view;
    }
    @Override
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