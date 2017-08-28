package im.actor.sdk.controllers.docs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import im.actor.core.entity.Peer;
import im.actor.sdk.ActorSDK;
import im.actor.sdk.ActorStyle;
import im.actor.sdk.R;
import im.actor.sdk.controllers.activity.BaseActivity;
import im.actor.sdk.controllers.conversation.ChatFragment;

/**
 * Created by diego on 10/08/17.
 */

public class DocsActivity extends BaseActivity {

    public static final int VIEW_TYPE_PHOTO = 1;
    public static final int VIEW_TYPE_DOCUMENT = 2;
    public static final int VIEW_TYPE_VIDEO = 3;

    public static final String EXTRA_CHAT_PEER = "chat_peer";

    private ViewPager mPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Map<Integer, Fragment> fragments = new HashMap<>();
    private Peer peer;

    public static Intent build(Peer peer, Context context) {
        final Intent intent = new Intent(context, DocsActivity.class);
        intent.putExtra(EXTRA_CHAT_PEER, peer.getUnuqueId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_media);
        setupTabs();
        peer = Peer.fromUniqueId(getIntent().getExtras().getLong(EXTRA_CHAT_PEER));

    }

    private void setupTabs() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(STYLE.getToolBarColor());
        toolbar.setTitleTextColor(STYLE.getTabTextPrimaryColor());

        setSupportActionBar(toolbar);
        setToolbar(R.string.shared_media, true);

        mPager = (ViewPager) findViewById(R.id.pager);
        TabsPageAdapter pagerAdapter = new TabsPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(STYLE.getAccentColor());
        tabLayout.setBackgroundColor(STYLE.getMainColor());
        tabLayout.setTabTextColors(STYLE.getTabTextPrimaryColor(), STYLE.getTabTextPrimaryColor());

        tabLayout.setupWithViewPager(mPager);
    }

    private Fragment getFragmentAtPosition(int position){
        if(fragments.containsKey(position)){
            return fragments.get(position);
        }
        Fragment fragment = PhotoFragment.create(peer);
        fragments.put(position,fragment);
        return  fragment;
    }


    class TabsPageAdapter extends FragmentPagerAdapter {

        private String[] tabText;

        public TabsPageAdapter(FragmentManager fm) {
            super(fm);
            tabText = getResources().getStringArray(R.array.shared_media_tabs);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragmentAtPosition(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getSpanable(position);
        }

        public CharSequence getSpanable(int position) {
            SpannableString spannableString = new SpannableString(tabText[position]);
            spannableString.setSpan(spannableString, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
