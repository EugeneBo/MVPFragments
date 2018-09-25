package com.github.parfenovvs.mvpsamplekotlin.ui.details

import com.github.parfenovvs.mvpsamplekotlin.model.DetailsService
import com.github.parfenovvs.mvpsamplekotlin.model.ServiceCallback
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BasePresenter

class SimpleDetailsPresenter : BasePresenter<SimpleDetailsView>() {

    var name: String? = null

    override fun onAttach() {
        view?.setProgressVisible(true)

        DetailsService.getDetails(name, object : ServiceCallback<String> {
            override fun onSuccess(data: String) {
                view?.showData(data)
            }
        })
    }

    fun setNameForService(name: String?) {
        this.name = name
    }

}