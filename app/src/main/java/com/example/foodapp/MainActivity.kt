package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navCtrl = this.findNavController(R.id.nav_host_fragment_container)
        NavigationUI.setupActionBarWithNavController(this, navCtrl)

        fab_favorite.setOnClickListener {
            toFavorite()
        }

    }

    private fun toFavorite() {
        try {
            val intent = Class.forName("com.example.foodapp.favorite.FavoriteActivity")
            startActivity(Intent(this, intent))
        } catch (e: Exception){
            Toast.makeText(this, "Favorite Not Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navCtrl = findNavController(R.id.nav_host_fragment_container)
        return navCtrl.navigateUp()
    }
}