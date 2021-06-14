package com.example.foodapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.core.data.Resource
import com.example.foodapp.core.ui.RecipeAdapter
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val recipeAdapter = RecipeAdapter()
            recipeAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity,DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA,selectedData)
                startActivity(intent)
            }

            homeViewModel.recipe.observe(viewLifecycleOwner, {recipe->
                if (recipe != null) {
                    when (recipe) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            recipeAdapter.setData(recipe.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            title_error.text = recipe.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            with(binding.rvRecipe){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = recipeAdapter
            }
        }
    }

}