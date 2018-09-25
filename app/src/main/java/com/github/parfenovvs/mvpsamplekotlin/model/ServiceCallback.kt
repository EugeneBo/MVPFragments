package com.github.parfenovvs.mvpsamplekotlin.model

interface ServiceCallback <T> {
    fun onSuccess(data : T)
}