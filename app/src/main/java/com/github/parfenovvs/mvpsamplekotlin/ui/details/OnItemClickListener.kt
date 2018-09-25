package com.github.parfenovvs.mvpsamplekotlin.ui.details

import com.github.parfenovvs.mvpsamplekotlin.entity.User

interface OnItemClickListener {
    fun onClick(user: User?)
}