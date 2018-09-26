package com.github.parfenovvs.mvpsamplekotlin.ui.details

import com.github.parfenovvs.mvpsamplekotlin.entity.User
import com.github.parfenovvs.mvpsamplekotlin.model.DetailsService
import com.github.parfenovvs.mvpsamplekotlin.model.ServiceCallback
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BasePresenter

class SimpleDetailsPresenter(var user: User?) : BasePresenter<SimpleDetailsView>() {

    override fun onAttach() {
        view?.setProgressVisible(true)

        DetailsService.getDetails(user?.name, object : ServiceCallback<String> {
            override fun onSuccess(data: String) {
                view?.showData(data)
            }
        })
    }
}