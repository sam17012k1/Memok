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


public class FamilyFragment extends Fragment {
    private MediaPlayer mplayer;
    private MediaPlayer.OnCompletionListener mcompletion = new MediaPlayer.OnCompletionListener() {
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
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mplayer.pause();
                        mplayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mplayer.start();
                    }
                }
            };
    private AudioManager aPlayer;


    public FamilyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_family, container, false);
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);

        ListView listView = (ListView) view.findViewById(R.id.list_family);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word current = words.get(i);
                release_mplayer();
                int result = 0;

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mplayer = MediaPlayer.create(getActivity(), current.getSound_id());
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

    public void onStop() {
        super.onStop();
        release_mplayer();
    }

    private void release_mplayer() {
        if (mplayer != null) {
            mplayer.release();
            mplayer = null;
        }
    }
}