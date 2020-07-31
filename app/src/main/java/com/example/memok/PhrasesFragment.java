package com.example.memok;

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


public class PhrasesFragment extends Fragment {
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


    public PhrasesFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_phrases,container,false);
        final ArrayList<Word> words= new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,R.color.category_phrases);

        ListView listView = (ListView) view.findViewById(R.id.list_phrases);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word current= words.get(i);
                release_mplayer();
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