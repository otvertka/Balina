package com.example.testbalina;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
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

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.foreground;
import static android.R.attr.fragment;
import static android.R.attr.onClick;
import static android.R.attr.value;
import static com.example.testbalina.R.id.calorie;
import static com.example.testbalina.R.id.carbohydrates;
import static com.example.testbalina.R.id.fats;
import static com.example.testbalina.R.id.proteins;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "myLogs";
    int status = 1; // 1-online, 0 - offline

    FragmentTransaction fTrans;

    public RecyclerView recyclerView;
    public Yml_catalog catalog;

    public Shop getShop() {
        return shop;
    }

    private Shop shop;

    public ListCatalog listCatalog;
    Category categoryMap;
    Map<Integer, String> map;


    public List keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        com.activeandroid.Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("Balina.db").create();
        ActiveAndroid.initialize(dbConfiguration);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loading();

    }

    public void loading(){
        App.getInf().getData("ukAXxeJYZN").enqueue(new Callback<Yml_catalog>() {
            @Override
            public void onResponse(Call<Yml_catalog> call, Response<Yml_catalog> response) {

                if(response.isSuccessful()) {

                    catalog = response.body();
                    shop = catalog.getShop();
                    listCatalog = new ListCatalog();
                    listCatalog.setShopObject(shop);
                    listCatalog.setStatus(1);

                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, listCatalog);
                    fTrans.commit();

                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "statusCode_onResponse: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Yml_catalog> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Нет подключения к интернету" , Toast.LENGTH_SHORT).show();
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
        } else if (id == R.id.action_save){
            if (catalog == null){
                Toast.makeText(MainActivity.this, "Для сохранения подключитесь к интернету" , Toast.LENGTH_SHORT).show();
            } else {
                saveCategories();
                saveOffers();
            }
        } else if (id == R.id.action_refresh){
            loading();
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog) {

            //Log.d(TAG, "ActiveAndroid.getDatabase(): " + ActiveAndroid.getDatabase());
            //Log.d(TAG, "new Select().from(Categories.class).execute(): " + new Select().from(Categories.class).execute());


            if (new Select().from(Categories.class).execute().size() == 0){
                Toast.makeText(MainActivity.this, "Ничего не сохранено" , Toast.LENGTH_SHORT).show();
            } else {
                ListCatalog f = new ListCatalog();
                status = 0;
                f.setStatus(status); //0 - offline, 1 - online

                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.frgmCont, f);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        }  else if (id == R.id.nav_contacts) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void saveOffers() {
        ActiveAndroid.beginTransaction();
        try {
                for (Offer list: shop.getOffers()
                     ) {
                    //Log.d(TAG, "saveOffers: " + list.getParamMap().get("Вес"));
                    String weight = "";
                    String calorie = "";
                    String proteins = "";
                    String fats = "";
                    String carbohydrates = "";

                    if (list.getParamMap() != null) {
                        if (list.getParamMap().get("Вес") != null) {
                            weight = list.getParamMap().get("Вес");
                        }
                        if (list.getParamMap().get("Каллорийность") != null) {
                            calorie = list.getParamMap().get("Каллорийность");
                        }
                        if (list.getParamMap().get("Белки") != null) {
                            proteins = list.getParamMap().get("Белки");
                        }
                        if (list.getParamMap().get("Жиры") != null) {
                            fats = list.getParamMap().get("Жиры");
                        }
                        if (list.getParamMap().get("Углеводы") != null) {
                            carbohydrates = list.getParamMap().get("Углеводы");
                        }
                    }

                    Integer categoryId = list.getCategoryId();
                    String name = list.getName();
                    String description = list.getDescription();
                    String picture = list.getPicture();
                    Double price =  list.getPrice();

                    Offers tableOffers = new Offers(categoryId , name , description,
                            picture,     price ,   weight,
                            calorie, proteins, fats, carbohydrates
                            );
                    tableOffers.save();
                    //Log.d(TAG, "Offers " + tableOffers.getId());

                }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private void saveCategories(){
        ActiveAndroid.beginTransaction();
        try {

            for (Map.Entry<Integer, String> set: shop.getCategoryMap().getMap().entrySet()) {
                Integer index = set.getKey();
                String category = set.getValue();

                Categories tableCategories = new Categories(index, category);
                tableCategories.save();
                //Log.d(TAG, "Categories " + tableCategories.getId());
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void fragmentMethod(int position){

        OfferCatalog listOffer = new OfferCatalog();
        listOffer.setShopObject(shop, position, status);
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgmCont, listOffer);
        fTrans.addToBackStack(null);
        fTrans.commit();
        Log.d(TAG, "fragmentMethod " + position);
    }
}
