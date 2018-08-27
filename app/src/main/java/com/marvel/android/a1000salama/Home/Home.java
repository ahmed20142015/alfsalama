package com.marvel.android.a1000salama.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.marvel.android.a1000salama.AboutUs.AboutUsFragment;
import com.marvel.android.a1000salama.AddServiceProvider.AddServiceProviderFragment;
import com.marvel.android.a1000salama.BookingFragment.BookingFragment;
import com.marvel.android.a1000salama.BookingHistory.BookingHistoryFragment;
import com.marvel.android.a1000salama.ContactUs.ContactUsFragment;
import com.marvel.android.a1000salama.EditAccount.EditUserAccount;
import com.marvel.android.a1000salama.FireBase.MyFirebaseMessagingService;
import com.marvel.android.a1000salama.Login.Login;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Rating.RatingFragment;
import com.marvel.android.a1000salama.ServiceDetials.DetailsFragment;
import com.marvel.android.a1000salama.ServiceSuppliers.SuppliersFragment;
import com.marvel.android.a1000salama.ServicesProviderInfo.ServiceProviderInfo;
import com.marvel.android.a1000salama.ServicsDetails.BServiceDetailsFragment;
import com.marvel.android.a1000salama.Ticks.TicksFragment;
import com.marvel.android.a1000salama.Utils;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        AddServiceProviderFragment.OnFragmentInteractionListener ,
        HomeFragment.OnFragmentInteractionListener ,
        DetailsFragment.OnFragmentInteractionListener ,
        BServiceDetailsFragment.OnFragmentInteractionListener,
        RatingFragment.OnFragmentInteractionListener,
        ServiceProviderInfo.OnFragmentInteractionListener ,
        BookingFragment.OnFragmentInteractionListener ,
        BookingHistoryFragment.OnFragmentInteractionListener ,
        AboutUsFragment.OnFragmentInteractionListener,
        ContactUsFragment.OnFragmentInteractionListener ,
        TicksFragment.OnFragmentInteractionListener,
        EditUserAccount.OnFragmentInteractionListener,
        SuppliersFragment.OnFragmentInteractionListener{


    private FragmentManager manager;
    private FragmentTransaction transaction;
    public static TextView ToolBarTitle ;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        profileImage = header.findViewById(R.id.profile_image);

        Drawable color = new ColorDrawable(getResources().getColor(R.color.Withe));
        Drawable image = getResources().getDrawable(R.drawable.final_logo);

        LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});
        profileImage.setImageDrawable(ld);


        Fragment fragment = new HomeFragment();



        try {
    Bundle extras = getIntent().getExtras();
//
//    String BookeID = extras.getString("BOOK_ID");
//    String PatintID = extras.getString("PATIENT_ID");
//    String SC_BRANCH_ID = extras.getString("SC_BRANCH_ID");
    Bundle bundle = new Bundle();
    bundle.putString("BookeID", MyFirebaseMessagingService.BookeID);
    bundle.putString("PatintID", MyFirebaseMessagingService.PatintID);
    bundle.putString("SC_BRANCH_ID", MyFirebaseMessagingService.BranchID);
    fragment.setArguments(bundle);
}
catch (Exception e)
{

}





        ToolBarTitle = findViewById(R.id.maintitle);
        ToolBarTitle.setText(getString(R.string.Home));



        manager = getSupportFragmentManager();
        if(manager == null)
            manager  =  getSupportFragmentManager();
        transaction = manager.beginTransaction();
        ToolBarTitle.setText(getString(R.string.Home));
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.content_home, fragment, "home");
        // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
           transaction.addToBackStack(null)
                .commit();
//           // transaction.add(fragment,"home");
//             transaction.addToBackStack(null);
//              transaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {



            try {
                HomeFragment myFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home");
                if (myFragment != null && myFragment.isVisible()) {
                    // add your code here
                    finish();
                }
            }
            catch (Exception e )
            {

            }

          //   super.onBackPressed();
        }

//
//        android.app.FragmentManager fm = getFragmentManager();
//        if (fm.getBackStackEntryCount() > 0) {
//            Log.i("MainActivity", "popping backstack");
//            fm.popBackStack();
//        } else {
//            Log.i("MainActivity", "nothing on backstack, calling super");
//            super.onBackPressed();
//        }
//        List fragmentList = getSupportFragmentManager().getFragments();
//
//       boolean handled = false;
//        for(Object f : fragmentList) {
//            if(f instanceof BaseFragment) {
//                handled = ((BaseFragment)f).onBackPressed();
//
//                if(handled) {
//                    break;
//                }
//            }
//        }
//
//      if(!handled) {
//           super.onBackPressed();
//       }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            ToolBarTitle.setText(getString(R.string.Home));
            Fragment fragment = new HomeFragment();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
            ToolBarTitle.setText(getString(R.string.Home));
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.content_home, fragment, "home");
            // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
            transaction.addToBackStack(null)
                    .commit();
        } else if (id == R.id.oldRequests) {
            ToolBarTitle.setText(getString(R.string.MyRequests));
            Fragment fragment = new BookingHistoryFragment();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.content_home, fragment, "MyRequests");
            // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
            transaction.addToBackStack(null)
                    .commit();

        }

        else if (id == R.id.editProfile){
            ToolBarTitle.setText(getString(R.string.edit_profile));
            Fragment fragment = new EditUserAccount();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.content_home, fragment, "EditAccount");
            transaction.addToBackStack(null)
                    .commit();
        }

        else if (id == R.id.serviceProvidor){
            ToolBarTitle.setText("مزودين الخدمة");
            Fragment fragment = new SuppliersFragment();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.content_home, fragment, "ServiceSuppliers");
            transaction.addToBackStack(null)
                    .commit();
        }

        else if( id == R.id.aboutUs)
        {

            ToolBarTitle.setText(getString(R.string.AboutUs));
            Fragment fragment = new AboutUsFragment();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.content_home, fragment, "AboutUs");
            // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
            transaction.addToBackStack(null)
                    .commit();
        }
        else if(id == R.id.logout)
        {

            Utils.RemoveUserID(getApplicationContext());
            Intent i;
            i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
            finish();
        }

        else if(id == R.id.share)
        {


            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "تطبيق ألف سلامة للعروض الطبية علي الرابط التالي " +
                    "https://play.google.com/store/apps/details?id=com.marvel.android.a1000salama");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        else if(id == R.id.contactud){
            ToolBarTitle.setText(getString(R.string.ContactUs));
            Fragment fragment = new ContactUsFragment();
            manager = getSupportFragmentManager();
            if(manager == null)
                manager  =  getSupportFragmentManager();
            transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.content_home, fragment, "ContactUs");
            // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
            transaction.addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
