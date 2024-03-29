package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.AboutFragment;
import infobite.must.eat.ui.fragment.AccountFragment;
import infobite.must.eat.ui.fragment.FeedbackFragment;
import infobite.must.eat.ui.fragment.HistoryFragment;
import infobite.must.eat.ui.fragment.HomeFragment;
import infobite.must.eat.ui.fragment.NotificationFragment;
import infobite.must.eat.R;
import infobite.must.eat.Utils;
import infobite.must.eat.adapter.DrawerListAdapter;
import infobite.must.eat.menu.DrawerAdapter;
import infobite.must.eat.menu.DrawerItem;
import infobite.must.eat.menu.SimpleItem;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseActivity;


public class HomeActivity extends BaseActivity implements DrawerAdapter.OnItemSelectedListener {

    private FloatingActionMenu fam;
    private FloatingActionButton fabuser, fabcart, faboffer, fabhome;
    private static FragmentManager fragmentManager;
    private SlidingRootNav slidingRootNav;
    private static final int NAV1 = 0;
    private static final int NAV2 = 1;
    private static final int NAV3 = 2;
    private static final int NAV4 = 3;
    private static final int NAV5 = 4;
    private static final int NAV6 = 5;
    private static final int NAV7 = 6;
    private static final int EXIT = 7;
    private TextView filter1, filter2, filter3, filter4;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    public static TextView tooltext;
    private ArrayList<String> navigation_filter;
    private ArrayList<String> navigation_filter1;
    private DrawerListAdapter drawerListAdapter;
    private ListView lv_drawer1, lv_drawer2;
    public static int[] drawer_icons = {R.drawable.homeicon6, R.drawable.homeicon6, R.drawable.homeicon6, R.drawable.homeicon6,
            R.drawable.homeicon6, R.drawable.homeicon6, R.drawable.homeicon6, R.drawable.homeicon6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tooltext = findViewById(R.id.tooltext);
        fragmentManager = getSupportFragmentManager();
        fabhome = findViewById(R.id.home_btn1);
        faboffer = findViewById(R.id.offer_btn1);
        fabcart = findViewById(R.id.cart_btn1);
        fabuser = findViewById(R.id.account_btn1);
        fam = findViewById(R.id.fab_menu1);
        filter1 = findViewById(R.id.filter1);
        filter2 = findViewById(R.id.filter2);
        filter3 = findViewById(R.id.filter3);
        filter4 = findViewById(R.id.filter4);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        init();


        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_drawer1.setVisibility(View.VISIBLE);
                lv_drawer2.setVisibility(View.GONE);
                filter3.setText("-");
                filter4.setText("+");

            }
        });
        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_drawer1.setVisibility(View.GONE);
                lv_drawer2.setVisibility(View.VISIBLE);
                filter3.setText("+");
                filter4.setText("-");
            }
        });
        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    Alerts.show(mContext,"Menu is opened");
                    fabhome.setVisibility(View.VISIBLE);
                    faboffer.setVisibility(View.VISIBLE);
                    fabcart.setVisibility(View.VISIBLE);
                    fabuser.setVisibility(View.VISIBLE);
                } else {
                    Alerts.show(mContext,"Menu is closed");
                    fabhome.setVisibility(View.GONE);
                    faboffer.setVisibility(View.GONE);
                    fabcart.setVisibility(View.GONE);
                    fabuser.setVisibility(View.GONE);
                }
            }
        });
        //handling each floating action button clicked
        fabuser.setOnClickListener(onButtonClick());
        fabcart.setOnClickListener(onButtonClick());
        faboffer.setOnClickListener(onButtonClick());
        fabhome.setOnClickListener(onButtonClick());

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });


        DrawerAdapter drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(NAV1).setChecked(true),
                createItemFor(NAV2),
                createItemFor(NAV3),
                createItemFor(NAV4),
                createItemFor(NAV5),
                createItemFor(NAV6),
                createItemFor(NAV7),
                createItemFor(EXIT)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);
        drawadapter.setSelected(NAV1);

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new HomeFragment(),
                            Constant.Home_Fragment).commit();
        }

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView menuRight = findViewById(R.id.menuRight1);
        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                    SetDrawer1();
                } else {
                    SetDrawer1();
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

    }

    @Override
    public void onItemSelected(int position) {

        if (position == EXIT) {
            finish();
        }
        if (position == NAV1) {
            // fragment = new AllFragment();
            tooltext.setText("Home");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new HomeFragment(),
                            Constant.Home_Fragment).commit();
        }
        if (position == NAV2) {
            tooltext.setText("Order History");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new HistoryFragment(),
                            Constant.HistoryFragment).commit();
        }

        if (position == NAV3) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new NotificationFragment(),
                            Constant.NotificationFragment).commit();
            tooltext.setText("Notificatons ");
        }

        if (position == NAV4) {
            tooltext.setText("Offers");
        }
        if (position == NAV5) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new FeedbackFragment(),
                            Constant.FeedbackFragment).commit();
            tooltext.setText("Feedback");
        }
        if (position == NAV6) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.home_frame, new AboutFragment(),
                            Constant.FeedbackFragment).commit();
            tooltext.setText("About");
        }
        if (position == NAV7) {
            tooltext.setText("My Account");
        }
        slidingRootNav.closeMenu();
    }


    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenTitles[position])
                .withIconTint(color(R.color.colorWhite))
                .withTextTint(color(R.color.colorWhite))
                .withSelectedIconTint(color(R.color.colorBlack))
                .withSelectedTextTint(color(R.color.colorBlack));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        tooltext.setText("Home");
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.home_frame, new HomeFragment(),
                        Constant.Home_Fragment).commit();

    }

    @Override
    public void onBackPressed() {

        Fragment HistoryFragment = fragmentManager.findFragmentByTag(Constant.HistoryFragment);
        Fragment NotificationFragment = fragmentManager.findFragmentByTag(Constant.NotificationFragment);
        Fragment AccountFragment = fragmentManager.findFragmentByTag(Constant.AccountFragment);

        if (HistoryFragment != null)
            replaceLoginFragment();
        else if (NotificationFragment != null)
            replaceLoginFragment();
        else if (AccountFragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

    private void init() {
        navigation_filter = new ArrayList<>();
        navigation_filter.add("Best Match");
        navigation_filter.add("Distance");
        navigation_filter.add("New");
        navigation_filter.add("Avg. rating");
        navigation_filter.add("A-Z");
        navigation_filter1 = new ArrayList<>();
        navigation_filter1.add("Amerikansk Pizza");
        navigation_filter1.add("Asiatisk mat");
        navigation_filter1.add("Baguetter");
        navigation_filter1.add("Dessert");
        navigation_filter1.add("Hamburger");
        lv_drawer1 = findViewById(R.id.lv_drawer1);
        lv_drawer2 = findViewById(R.id.lv_drawer2);
    }

    private void SetDrawer1() {

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawerListAdapter = new DrawerListAdapter(HomeActivity.this, navigation_filter, drawer_icons);
        lv_drawer1.setAdapter(drawerListAdapter);
        lv_drawer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (navigation_filter.get(position).equalsIgnoreCase("Any")) {

                } else if (navigation_filter.get(position).equalsIgnoreCase("Rs 10,000 to 20,000")) {

                } else if (navigation_filter.get(position).equalsIgnoreCase("Rs 20,000 to 30,000")) {

                }
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        drawerListAdapter = new DrawerListAdapter(HomeActivity.this, navigation_filter1, drawer_icons);
        lv_drawer2.setAdapter(drawerListAdapter);

        lv_drawer2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (navigation_filter1.get(position).equalsIgnoreCase("Any")) {

                } else if (navigation_filter1.get(position).equalsIgnoreCase("2 Nights")) {

                } else if (navigation_filter1.get(position).equalsIgnoreCase("3 Nights")) {

                }
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabhome) {
                    Alerts.show(mContext,"Button home clicked");
                } else if (view == fabcart) {
                    Alerts.show(mContext,"Button Cart clicked");
                    Intent intent = new Intent(HomeActivity.this, AddtoCartActivity.class);
                    startActivity(intent);
                } else if (view == faboffer) {
                    Intent intent = new Intent(HomeActivity.this, OffersActivity.class);
                    startActivity(intent);
                } else {
                    Alerts.show(mContext,"Button Account clicked");
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.home_frame, new AccountFragment(),
                                    Constant.AccountFragment).commit();
                }
                fam.close(true);
            }
        };
    }
}
