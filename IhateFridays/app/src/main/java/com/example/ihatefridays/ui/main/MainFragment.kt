package com.example.ihatefridays.ui.main

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ihatefridays.R
import com.example.ihatefridays.di.App
import com.example.ihatefridays.di.DaggerAppComponent
import com.example.ihatefridays.model.MakeUp
import com.example.ihatefridays.retrofit.MakeupApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainFragment : Fragment() {
    val compositeDisposable = CompositeDisposable()
   @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var makeupAPIInterface:MakeupApiInterface


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        (activity!!.application as App).appComponent.build().inject(this)
        makeupAPIInterface
        retrofit
        val newDisposable = makeupAPIInterface.searchMakeup("maybeline").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(10)
            .subscribeBy(
                onSuccess = { list ->
                    message.text = list[0].name.toString()
                    Log.i("success",list.toString())},
                onError =  { it.printStackTrace() }
            )

        compositeDisposable.add(newDisposable)
    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
