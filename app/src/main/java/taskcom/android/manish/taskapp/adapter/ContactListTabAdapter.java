package taskcom.android.manish.taskapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ContactListTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> tabArrayList = new ArrayList<>();
    private ArrayList<String> tabTitleArrayList = new ArrayList<>();

    public ContactListTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addTab(Fragment fragment, String title){
        tabArrayList.add(fragment);
        tabTitleArrayList.add(title);
    }


    @Override
    public Fragment getItem(int i) {
        return tabArrayList.get(i);
    }

    @Override
    public int getCount() {
        return tabArrayList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleArrayList.get(position);
    }
}
