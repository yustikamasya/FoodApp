package com.example.foodapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.core.ui.RecipeAdapter
import com.example.foodapp.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getFavoriteData()
    }

    private fun getFavoriteData() {
        val recipeAdapter = RecipeAdapter()
        recipeAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA,selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteRecipe.observe(this, {dataRecipe->
            recipeAdapter.setData(dataRecipe)
            view_empty.visibility = if (dataRecipe.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_recipe){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recipeAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}