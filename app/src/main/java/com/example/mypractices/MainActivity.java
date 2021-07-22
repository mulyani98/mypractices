package com.example.mypractices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Navigation Drawer
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Drawer Layout instance to toggle the menu icon to open
        * Drawer and back button to close drawer */
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.nav_open, R.string.nav_close);

        /*
        * pass the Open and Close toggle for the drawer layout listener
        * to the toggle button */
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*
        * to make the Navigation Drawer icon always appear on the action bar */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        * PRINT PHOTO BUTTON */
        Button printPhotoButton = findViewById(R.id.print_photo_button);
        printPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrintPhotoActivity.class);
                startActivity(intent);
            }
        });

//        /*
//        * BACK BUTTON */
//        if (getSupportActionBar() != null) {
//            ActionBar backButton = getSupportActionBar();
//            backButton.setDisplayHomeAsUpEnabled(true);
//            //getSupportActionBar().setTitle(filteredImages.get(0).getFolderName());
//        }
    }

//    //back button
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    /*
    * override the onOptionItemSelected() function to implement the item click listener callback
    * to open and close the navigation drawer when the icon is clicked */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
//            getSupportActionBar().setTitle(R.string.iprint_info);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);

            return true;
        }
        else{
//            getSupportActionBar().setTitle(R.string.app_name);
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        return super.onOptionsItemSelected(item);
    }
}