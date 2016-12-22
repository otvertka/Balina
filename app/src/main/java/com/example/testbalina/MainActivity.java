package com.example.testbalina;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.fragment;
import static android.R.attr.value;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "myLogs";

    public RecyclerView recyclerView;
    public Yml_catalog catalog;

    public Shop getShop() {
        return shop;
    }

    private Shop shop;

    //public ListCatalog listCatalog;
    Category categoryMap;
    Map<Integer, String> map;


    public List keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           /* recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
         LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*listCatalog = new ListCatalog();
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, listCatalog);
        fTrans.commit();*/
        /*try {
            Log.d(TAG, "RESPONSE");
            Response response = App.getInf().getData("ukAXxeJYZN").execute();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        App.getInf().getData("ukAXxeJYZN").enqueue(new Callback<Yml_catalog>() {
            @Override
            public void onResponse(Call<Yml_catalog> call, Response<Yml_catalog> response) {

                if(response.isSuccessful()) {

                    catalog = response.body();
                    shop = catalog.getShop();
                    //categoryMap = shop.getCategoryMap();
                    //map = categoryMap.getMap();

                    //keyList = new ArrayList(map.keySet());

                    /*PostsAdapter adapter = new PostsAdapter(keyList);
                    recyclerView.setAdapter(adapter);*/
                    ListCatalog listCatalog = new ListCatalog();
                    listCatalog.setShopObject(shop);


                    /*listCatalog = new ListCatalog();

                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("lol", (ArrayList<Integer>) keyList);
                    listCatalog.setArguments(bundle);*/

                    FragmentTransaction fTrans = getFragmentManager().beginTransaction();
                    fTrans.add(R.id.frgmCont, listCatalog);
                    fTrans.commit();
                    Log.d(TAG, "ListCatalog...");

                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "statusCode_onResponse: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Yml_catalog> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking" , Toast.LENGTH_SHORT).show();
                Log.d(TAG, "statusCode_onFailure: " + t.getMessage());
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog) {
            //
        }  else if (id == R.id.nav_contacts) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
