package com.example.foodapp.core.data.source.remote

import android.util.Log
import com.example.foodapp.core.data.source.remote.network.ApiResponse
import com.example.foodapp.core.data.source.remote.network.ApiService
import com.example.foodapp.core.data.source.remote.response.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllRecipe(): Flow<ApiResponse<List<RecipeResponse>?>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.meals ?: ArrayList()
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.meals))
                } else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailRecipe(id: String): Flow<ApiResponse<RecipeResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Something went wrong.\nReconnect to the Internet and Try again"))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}