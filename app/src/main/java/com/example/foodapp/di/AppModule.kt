package com.example.foodapp.di

import com.example.foodapp.core.domain.usecase.RecipeInteractor
import com.example.foodapp.core.domain.usecase.RecipeUseCase
import com.example.foodapp.detail.DetailViewModel
import com.example.foodapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipeUseCase>{RecipeInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}