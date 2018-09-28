package com.github.parfenovvs.mvpsamplekotlin.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.parfenovvs.mvpsamplekotlin.ui.PresenterHolder

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment() {
    protected var presenter: P? = null
        private set

    private lateinit var key: String

    abstract fun provideLayoutId(): Int

    protected abstract fun providePresenter(): P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideLayoutId(), container, false)
    }


    override fun onStart() {
        super.onStart()
        attachPresenter(this as V)
    }

    private fun attachPresenter(view: V) {
        val presenter: P
        key = view::class.java.simpleName
        if (PresenterHolder.holder.containsKey(key))
            presenter = PresenterHolder.holder[key] as P
        else {
            presenter = providePresenter()
            PresenterHolder.holder[key] = presenter
        }

        presenter.attachView(view)
        this.presenter = presenter
    }


    override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    override fun onDestroyView() {
        PresenterHolder.holder.remove(key)
        super.onDestroyView()
    }
}