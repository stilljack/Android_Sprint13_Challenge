package com.example.ihatefridays.ui.main

import com.example.ihatefridays.model.MakeUp
import com.example.ihatefridays.retrofit.MakeupApiInterface
import io.reactivex.Single
import javax.inject.Inject

class Repo @Inject constructor(private val makeupApiInterface: MakeupApiInterface):MakeupApiInterface{
    override fun searchMakeup(query: String): Single<List<MakeUp>> {

        return makeupApiInterface.searchMakeup(query)
    }
}