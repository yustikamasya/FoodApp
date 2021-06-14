package com.example.foodapp.core.di

import androidx.room.Room
import com.example.foodapp.core.data.RecipeRepository
import com.example.foodapp.core.data.source.local.LocalDataSource
import com.example.foodapp.core.data.source.local.room.RecipeDatabase
import com.example.foodapp.core.data.source.remote.RemoteDataSource
import com.example.foodapp.core.data.source.remote.network.ApiService
import com.example.foodapp.core.domain.repository.IRecipeRepository
import com.example.foodapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RecipeDatabase>().recipeDao() }
    single {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("3700".toCharArray())
            val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java, "recipe.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "sni.cloudflaressl.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/pz7CjjOO6yeiHWrcJ+RWljKC2pBYw+9O7XwRIl7HLn8=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule= module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRecipeRepository>{
        RecipeRepository(
            get(),
            get(),
            get()
        )
    }
}