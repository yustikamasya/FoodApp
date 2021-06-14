package com.example.foodapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.core.data.Resource
import com.example.foodapp.core.domain.model.Recipe
import com.example.foodapp.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailRecipe = intent.getParcelableExtra<Recipe>(EXTRA_DATA)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (detailRecipe != null){
            supportActionBar?.title = detailRecipe.meal
            getDetailRecipe(detailRecipe.idMeal)
        }
    }

    private fun getDetailRecipe(idMeal: String) {
        detailViewModel.getDetailRecipe(idMeal).observe(this,{recipe ->
            when(recipe){
                is Resource.Loading ->{
                    progress_bar.visibility = View.VISIBLE
                    container_detail.visibility = View.GONE
                }
                is Resource.Success ->{
                    showDetailRecipe(recipe.data)
                    progress_bar.visibility = View.GONE
                    container_detail.visibility = View.VISIBLE
                }
                is Resource.Error ->{
                    progress_bar.visibility = View.GONE
                    view_error.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showDetailRecipe(detailRecipe: Recipe?) {
        detailRecipe?.let {
            binding.tvFood.text = detailRecipe.meal
            binding.tvCategory.text = detailRecipe.category
            binding.tvInstruction.text = detailRecipe.instructions
            binding.tvIngredients1.text = "1. ${detailRecipe.ingredient1}"
            binding.tvIngredients2.text = "2. ${detailRecipe.ingredient2}"
            binding.tvIngredients3.text = "3. ${detailRecipe.ingredient3}"
            binding.tvIngredients4.text = "4. ${detailRecipe.ingredient4}"
            binding.tvIngredients5.text = "5. ${detailRecipe.ingredient5}"
            binding.tvMeasure1.text = "1. ${detailRecipe.measure1}"
            binding.tvMeasure2.text = "2. ${detailRecipe.measure2}"
            binding.tvMeasure3.text = "3. ${detailRecipe.measure3}"
            binding.tvMeasure4.text = "4. ${detailRecipe.measure4}"
            binding.tvMeasure5.text = "5. ${detailRecipe.measure5}"

            Glide.with(this@DetailActivity)
                .load(detailRecipe.image)
                .into(binding.imgFood)

            var statusFavorite = detailRecipe.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteRecipe(detailRecipe, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fav_red
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fav_line
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}