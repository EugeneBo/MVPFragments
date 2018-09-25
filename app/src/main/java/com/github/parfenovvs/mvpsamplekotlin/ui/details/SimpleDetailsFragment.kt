package com.github.parfenovvs.mvpsamplekotlin.ui.details

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.github.parfenovvs.mvpsamplekotlin.R
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BaseFragment
import com.github.parfenovvs.mvpsamplekotlin.ui.list.SimpleListFragment

class SimpleDetailsFragment : BaseFragment<SimpleDetailsView, SimpleDetailsPresenter>(), SimpleDetailsView {

    private var usernameHeader: TextView? = null
    private var emailHeader: TextView? = null
    private var detailsText: TextView? = null
    private var toolbar: Toolbar? = null
    private var progressBar: ProgressBar? = null
    private var scrollView: ScrollView? = null
    private var handler = Handler()

    override fun layoutId(): Int {
        return R.layout.fragment_details
    }

    override fun providePresenter(): SimpleDetailsPresenter {
        return SimpleDetailsPresenter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(SimpleListFragment.NAME_TAG)
        val email = arguments?.getString(SimpleListFragment.EMAIL_TAG)

        Log.d("LogTag", "SimpleDetailsFragment RESULT: $name - $email")

        usernameHeader = view.findViewById(R.id.usernameHeader)
        emailHeader = view.findViewById(R.id.emailHeader)
        detailsText = view.findViewById(R.id.detailsTextView)
        toolbar = view.findViewById(R.id.toolbar)
        progressBar = view.findViewById(R.id.detailsTextProgressBar)
        scrollView = view.findViewById(R.id.scrollView)

        setToolbar()

        presenter?.setNameForService(name) //тут косяк! вызывается уже после того как DetailsService отработал

        usernameHeader?.text = name
        emailHeader?.text = email
    }


    override fun setProgressVisible(status: Boolean) {
        if (status) {
            scrollView?.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
            scrollView?.visibility = View.VISIBLE
        }
    }


    override fun showData(data: String) {
        handler.post {
            detailsText?.text = data
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