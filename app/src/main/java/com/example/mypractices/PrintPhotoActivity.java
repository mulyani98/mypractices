package com.example.mypractices;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PrintPhotoActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TabLayout tabLayout;

    final Context context = this; //for custom dialog
//    SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabview_printphotoscreen);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        TabLayout.Tab allPhotoTab = tabLayout.newTab();
        allPhotoTab.setText("All Photo");
        // allPhotoTab.setIcon(R.drawable.survey_black);
        tabLayout.addTab(allPhotoTab);

        TabLayout.Tab albumTab = tabLayout.newTab();
        albumTab.setText("Album");
//        albumTab.setIcon(R.drawable.survey_white);
        tabLayout.addTab(albumTab);

        /*
        * Perform setOnTabSelectedListener event on TabLayout */
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new AllPhotoFragment();
                        break;
                    case 1:
                        fragment = new AlbumFragment();
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*
         * BACK BUTTON */
        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setTitle(filteredImages.get(0).getFolderName());
        }
    }
    //back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Crete Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.context_menu, menu);


        /* SEARCH another version */
//        final MenuItem searchItem = menu.findItem(R.id.searchButton);
//        if (searchItem != null) {
//            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//                @Override
//                public boolean onClose() {
//                    //some operation
//                    return true;
//                }
//            });
//            searchView.setOnSearchClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //some operation
//                    return;
//                }
//            });
//            //EDIT TEXT SEARCH
////            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
////            searchPlate.setHint("Search");
////            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
////            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
//            // use this method for search process
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    // use this method when query submitted
//                    Toast.makeText(context, query, Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    // use this method for auto complete search process
//                    return false;
//                }
//            });
//            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//        }
//        return super.onCreateOptionsMenu(menu);

        /* Search */
        //retrieve the searchview and plug it into SearchManager
//        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchButton));
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchButton));
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        //search logic(?)
//        searchManager.getSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;

    }

//    //visibility search etc?
//    @SuppressLint("ResourceType")
//    @Override
//    public void onBackPressed() {
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            findViewById(R.string.app_name).setVisibility(View.VISIBLE);
//        } else {
//            super.onBackPressed();
//        }
//    }


    //Clicked option menu Viewby and Sortby
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =  item.getItemId();
        CheckBox checkBoxGrid, checkBoxList, checkBoxName, checkBoxDate;

        checkBoxGrid = (CheckBox)findViewById(R.id.checkboxGrid);
        checkBoxList = (CheckBox)findViewById(R.id.checkboxList);
        checkBoxName = (CheckBox)findViewById(R.id.checkboxName);
        checkBoxDate = (CheckBox)findViewById(R.id.checkboxDate);

        switch (id){
            case R.id.option_Viewby:
                //code
                final Dialog dialog_Viewby = new Dialog(context);
                dialog_Viewby.setContentView(R.layout.option_view_by);
                //define button done viewby
                Button buttonViewby = (Button) dialog_Viewby.findViewById(R.id.buttonDoneViewBy);
                //close dialog, when button is clicked
                buttonViewby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_Viewby.dismiss();
                    }
                });
                //show dialog
                dialog_Viewby.show();

                return true;

            case R.id.option_Sortby:
                //code
                final Dialog dialog_Sortby = new Dialog(context);
                dialog_Sortby.setContentView(R.layout.option_sort_by);
                //define button done sortby
                Button buttonSortby = (Button) dialog_Sortby.findViewById(R.id.buttonDoneSortBy);
                //close dialog when button is clicked
                buttonSortby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_Sortby.dismiss();
                    }
                });
                //show dialog
                dialog_Sortby.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
