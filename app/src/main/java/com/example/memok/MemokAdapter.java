package com.example.memok;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MemokAdapter extends FragmentPagerAdapter {
    int totalTabs;
    public MemokAdapter(FragmentManager fb,int nOT){
        super(fb);
        this.totalTabs=nOT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                NumbersFragment nf= new NumbersFragment();
                return nf;
            case 1:
                ColorsFragment cf= new ColorsFragment();
                return cf;
            case 2:
                FamilyFragment ff= new FamilyFragment();
                return ff;
            case 3:
                PhrasesFragment pf= new PhrasesFragment();
                return pf;
            default:
                return null;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Numbers";
        } else if (position == 1) {
            return "Colors";
        } else if (position == 2) {
            return "Family";
        } else {
            return "Phrases";
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
