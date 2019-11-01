package com.example.ihatefridays.di


import com.example.ihatefridays.ui.main.MainFragment
import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Component
interface AppComponent {
    fun inject (mainFragment: MainFragment)
}


@Module
object AppModule {
    private val  BASEURL = "http://makeup-api.herokuapp.com/api/v1/"

    @Singleton
    @Provides
    @JvmStatic
    fun providesRetrofitInstance() =   Retrofit
        .Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()



}