package com.example.ihatefridays.di


import android.app.Application
import com.example.ihatefridays.model.MakeUp
import com.example.ihatefridays.retrofit.MakeupApiInterface
import com.example.ihatefridays.ui.main.MainFragment
import com.google.gson.GsonBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component (modules = [
AppModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication (application: Application):Builder

        fun build():AppComponent
    }

    fun inject (mainFragment: MainFragment)
}


@Module
object AppModule {
  private val  BASEURL = "https://makeup-api.herokuapp.com/api/v1/"
// private val  BASEURL =   "https://api.exchangerate-api.com/v4/latest/"
    val logging =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    val client = OkHttpClient.Builder().addInterceptor(logging).build()

    @Singleton
    @Provides
    @JvmStatic
    fun providesRetrofitInstance() =   Retrofit
        .Builder()
        .baseUrl(BASEURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Singleton
    @Provides
    @JvmStatic
    fun provideMakeupService(retrofit: Retrofit) =
        retrofit.create(MakeupApiInterface::class.java)


}