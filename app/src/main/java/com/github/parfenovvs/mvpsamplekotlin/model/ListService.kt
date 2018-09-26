package com.github.parfenovvs.mvpsamplekotlin.model

import com.github.parfenovvs.mvpsamplekotlin.entity.User

object ListService {

    fun getUsers(usersListSize: Int, callback: ServiceCallback<List<User>>) {
        Thread {
            val users = ArrayList<User>()
            for (i in 0 until usersListSize)
                users.add(User("username_$i", "username_$i@gmail.com"))
            Thread.sleep(1000)              //  try/catch?
            callback.onSuccess(users)

        }.start()

    }

}
