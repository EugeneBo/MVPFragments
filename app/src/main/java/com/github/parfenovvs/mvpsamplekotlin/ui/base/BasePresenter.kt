package com.github.parfenovvs.mvpsamplekotlin.ui.base

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {
  private var viewRef: WeakReference<V>? = null

  val view: V?
    get() = viewRef?.get()

  internal fun attachView(view: V) {
    viewRef = WeakReference(view)
    onAttach()
  }

  internal fun detachView() {
    onDetach()
    viewRef?.clear()
  }

  protected open fun onAttach() {}

  protected open fun onDetach() {}
}
