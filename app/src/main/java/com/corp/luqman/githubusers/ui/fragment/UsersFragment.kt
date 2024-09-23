package com.corp.luqman.githubusers.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.luqman.githubusers.R
import com.corp.luqman.githubusers.databinding.FragmentUsersBinding
import com.corp.luqman.githubusers.utils.custom.CustomProgressDialog

class UsersFragment : Fragment() {

    private lateinit var progressDialog : CustomProgressDialog

    private lateinit var binding: FragmentUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater)
        setHasOptionsMenu(true)
        progressDialog = CustomProgressDialog(binding.root.context, getString(R.string.loading))
        return inflater.inflate(R.layout.fragment_users, container, false)
    }
}