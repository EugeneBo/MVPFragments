package com.github.parfenovvs.mvpsamplekotlin.ui.details

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.github.parfenovvs.mvpsamplekotlin.R
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BaseFragment
import com.github.parfenovvs.mvpsamplekotlin.ui.list.SimpleListFragment
import kotlinx.android.synthetic.main.fragment_details.*

class SimpleDetailsFragment : BaseFragment<SimpleDetailsView, SimpleDetailsPresenter>(), SimpleDetailsView {

    private var toolbar: Toolbar? = null
    private var handler = Handler()

    override fun layoutId(): Int {
        return R.layout.fragment_details
    }

    override fun providePresenter(): SimpleDetailsPresenter {
        return SimpleDetailsPresenter(InitialDataUser.user)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(SimpleListFragment.NAME_TAG)
        val email = arguments?.getString(SimpleListFragment.EMAIL_TAG)

        Log.d("LogTag", "\nSimpleDetailsFragment RESULT: \n$name \n$email")

        toolbar = view.findViewById(R.id.detailsToolbar)

        setToolbar()

        usernameHeaderTextView?.text = name
        emailHeaderTextView?.text = email
    }


    override fun setProgressVisible(status: Boolean) {
        if (status) {
            scrollView?.visibility = View.GONE
            detailsTextProgressBar?.visibility = View.VISIBLE
        } else {
            detailsTextProgressBar?.visibility = View.GONE
            scrollView?.visibility = View.VISIBLE
        }
    }


    override fun showData(data: String) {
        handler.post {
            detailsTextView?.text = data
            setProgressVisible(false)
        }
    }


    private fun setToolbar() {
        toolbar?.title = "Details"
        toolbar?.setNavigationIcon(R.drawable.ic_clear_24dp)
        toolbar?.inflateMenu(R.menu.menu_submit)
        toolbar?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        toolbar?.setOnMenuItemClickListener {
            //todo
            return@setOnMenuItemClickListener true     ///@setOnMenuItemClickListener???
        }
    }

}