package com.github.parfenovvs.mvpsamplekotlin.model

import java.lang.StringBuilder

object DetailsService{

    fun getDetails (username : String?, callback:ServiceCallback<String>){
        Thread{
            val result = StringBuilder()
            for (i in 0..100)
                result.append("All work and no play makes $username a dull boy. ")
            Thread.sleep(1500)                  //  try/catch?
            callback.onSuccess(result.toString())

        }.start()
    }


}