package com.bella.week4.ui.core

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel
}