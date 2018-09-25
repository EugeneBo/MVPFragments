package com.github.parfenovvs.mvpsamplekotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.parfenovvs.mvpsamplekotlin.R
import com.github.parfenovvs.mvpsamplekotlin.ui.list.SimpleListFragment


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .add(R.id.container, SimpleListFragment())
          .commitAllowingStateLoss()
    }
  }

}