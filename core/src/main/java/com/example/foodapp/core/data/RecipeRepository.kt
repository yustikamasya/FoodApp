package com.example.foodapp.core.data

import com.example.foodapp.core.data.source.local.LocalDataSource
import com.example.foodapp.core.data.source.remote.RemoteDataSource
import com.example.foodapp.core.data.source.remote.network.ApiResponse
import com.example.foodapp.core.data.source.remote.response.RecipeResponse
import com.example.foodapp.core.domain.model.Recipe
import com.example.foodapp.core.domain.repository.IRecipeRepository
import com.example.foodapp.core.utils.AppExecutors
import com.example.foodapp.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RecipeRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
                       private val appExecutors: AppExecutors) : IRecipeRepository{
    override fun getAllRecipe(): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeResponse>>(){
            override fun loadFromDB(): Flow<List<Recipe>> {
                return localDataSource.getAllRecipe().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }
            override fun shouldFetch(data: List<Recipe>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RecipeResponse>>> =
                remoteDataSource.getAllRecipe() as Flow<ApiResponse<List<RecipeResponse>>>

            override suspend fun saveCallResult(data: List<RecipeResponse>) {
                val recipeList = data.let { DataMapper.mapResponsesToEntities(it) }
                CoroutineScope(IO).launch {
                    localDataSource.insertRecipe(recipeList)
                }
            }

        }.asFlow()

    override fun getFavoriteRecipe(): Flow<List<Recipe>> {
        return localDataSource.getFavoriteRecipe().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        appExecutors.diskIO().execute{localDataSource.setFavoriteRecipe(recipeEntity,state)}
    }

    override fun getDetailRecipe(id: String): Flow<Resource<Recipe>> {
        return object : NetworkBoundResource<Recipe, RecipeResponse>() {
            override fun loadFromDB(): Flow<Recipe> {
                return localDataSource.getDetailRecipe(id).map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: Recipe?): Boolean = data === null || data.meal === null || data.category === null

            override suspend fun createCall(): Flow<ApiResponse<RecipeResponse>> = remoteDataSource.getDetailRecipe(id)

            override suspend fun saveCallResult(data: RecipeResponse) {
                val recipeDetail = DataMapper.mapResponsesDetailToEntities(data)
            }
        }.asFlow()
    }

}