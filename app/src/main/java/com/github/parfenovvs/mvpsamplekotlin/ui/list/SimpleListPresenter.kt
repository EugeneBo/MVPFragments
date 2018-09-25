package com.github.parfenovvs.mvpsamplekotlin.ui.list

import com.github.parfenovvs.mvpsamplekotlin.entity.User
import com.github.parfenovvs.mvpsamplekotlin.model.ServiceCallback
import com.github.parfenovvs.mvpsamplekotlin.model.ListService.getUsers
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BasePresenter

class SimpleListPresenter : BasePresenter<SimpleListView>() {

    override fun onAttach() {
        view?.setProgressVisible(true)
        getUsers(100, object : ServiceCallback<List<User>> {
            override fun onSuccess(data: List<User>) {
                view?.showData(data)
            }
        })


    }
}