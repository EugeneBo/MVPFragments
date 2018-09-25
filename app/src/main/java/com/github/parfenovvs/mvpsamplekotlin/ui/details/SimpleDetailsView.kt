package com.github.parfenovvs.mvpsamplekotlin.ui.details

import com.github.parfenovvs.mvpsamplekotlin.entity.User
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BaseView

interface SimpleDetailsView : BaseView {
    fun showData(data: String)
    fun setProgressVisible(status: Boolean)
}