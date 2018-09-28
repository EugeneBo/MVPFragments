package com.github.parfenovvs.mvpsamplekotlin.ui.list

import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.github.parfenovvs.mvpsamplekotlin.R
import com.github.parfenovvs.mvpsamplekotlin.entity.User
import com.github.parfenovvs.mvpsamplekotlin.ui.base.BaseFragment
import android.util.Log
import com.github.parfenovvs.mvpsamplekotlin.ui.details.InitialDataUser
import com.github.parfenovvs.mvpsamplekotlin.ui.details.OnItemClickListener
import com.github.parfenovvs.mvpsamplekotlin.ui.details.SimpleDetailsFragment


class SimpleListFragment : BaseFragment<SimpleListView, SimpleListPresenter>(), SimpleListView {

    companion object {
        const val NAME_TAG = "com.github.parfenovvs.mvpsamplekotlin.ui.list_NAME"
        const val EMAIL_TAG = "com.github.parfenovvs.mvpsamplekotlin.ui.list_EMAIL"
        const val DETAILS_FRAGMENT_TAG = "com.github.parfenovvs.mvpsamplekotlin.ui.list_DETAILED_FRAGMENT_TAG"
    }

    private val adapter: ListAdapter = ListAdapter()
    private val handler = Handler()

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var toolbar: Toolbar? = null

    override fun provideLayoutId(): Int {
        return R.layout.fragment_simple_list
    }


    override fun providePresenter(): SimpleListPresenter {
        return SimpleListPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)

        adapter.setListener(object : OnItemClickListener {
            override fun onClick(user: User?) {
                val bundle = Bundle()

                InitialDataUser.user=user

                bundle.putString(NAME_TAG, user?.name)
                bundle.putString(EMAIL_TAG, user?.email)

                Log.d("LogTag", "SimpleListFragment RESULT: ${user?.name} - ${user?.email}")

                val detailsFragment = SimpleDetailsFragment()
                detailsFragment.arguments = bundle


                activity?.supportFragmentManager?.
                        beginTransaction()?.
                        replace(R.id.container, detailsFragment)?.
                        addToBackStack(null)?.
                        commitAllowingStateLoss()
            }
        })

        recyclerView?.adapter = adapter
        progressBar = view.findViewById(R.id.listProgressBar)

        setToolbar()

    }

    override fun setProgressVisible(status: Boolean) {
        if (status) {
            recyclerView?.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
            recyclerView?.visibility = View.VISIBLE
        }
    }


    override fun showData(items: List<User>) {
        handler.post {
            adapter.setItems(items)
            setProgressVisible(false)
        }
    }


    private fun setToolbar() {
        toolbar = view?.findViewById(R.id.listToolbar)
        toolbar?.title = "List"
    }

    class VH(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var userItem: ConstraintLayout? = null
        private var nameTextView: TextView? = null
        private var emailTextView: TextView? = null

        init {
            nameTextView = itemView?.findViewById(R.id.nameTextView)
            emailTextView = itemView?.findViewById(R.id.emailTextView)
            userItem = itemView?.findViewById(R.id.userItem)

        }

        fun bindData(user: User?) {
            nameTextView?.text = user?.name
            emailTextView?.text = user?.email
        }
    }


    class ListAdapter : RecyclerView.Adapter<VH>() {

        private var users: List<User>? = null
        private var listener: OnItemClickListener? = null

        fun setItems(items: List<User>) {
            users = items               //correct?
            notifyDataSetChanged()
        }

        fun setListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false))
        }

        override fun getItemCount(): Int {
            return users?.size ?: 0
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bindData(users?.get(position))
            holder.userItem?.setOnClickListener {
                listener?.onClick(users?.get(position))
            }

        }

    }


}


