package com.github.parfenovvs.mvpsamplekotlin.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment() {
  protected var presenter: P? = null
    private set

  abstract fun layoutId(): Int

  protected abstract fun providePresenter(): P

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutId(), container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    attachPresenter(this as V)
  }

  private fun attachPresenter(view: V) {
    //TODO
    val presenter = providePresenter()
    presenter.attachView(view)
    this.presenter = presenter
  }

  override fun onDestroyView() {
    presenter?.detachView()
    super.onDestroyView()
  }
}