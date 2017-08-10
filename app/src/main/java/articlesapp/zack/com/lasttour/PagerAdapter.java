package articlesapp.zack.com.lasttour;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Admin on 8/4/2017.
 */

public class PagerAdapter  extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;

    private String tabTitles[] = new String[]{
            EntertainmentFragment.NAME,
            MallFragment.NAME,

            RestaurantFragment.NAME,

            ExtraFragment.NAME};

    private Context mContext;

    public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 0){
//            return new EntertainmentFragment();
//        } else if (position == 1){
//            return new MallFragment();
//        }else if (position == 2) {
//            return new RestaurantFragment();
//        }else {
//            return new SchoolFragment();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = Fragment.instantiate(mContext, EntertainmentFragment.class.getName());
                break;
            case 1:
                fragment = Fragment.instantiate(mContext, MallFragment.class.getName());
                break;
            case 2:
                fragment = Fragment.instantiate(mContext, RestaurantFragment.class.getName());
                break;
            case 3:
                fragment = Fragment.instantiate(mContext, ExtraFragment.class.getName());
                break;
        }
        return fragment;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
