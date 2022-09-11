package com.dicoding.submission2.ui.main.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dicoding.submission2.R
import com.dicoding.submission2.databinding.FragmentFollowBinding

class FollowingFragment: Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
    }
}