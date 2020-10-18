package com.luas.app.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel


abstract class BaseActivity<BINDING : ViewDataBinding, VIEW_MODEL : ViewModel>
constructor(@LayoutRes private val layoutResource: Int) : AppCompatActivity() {

    protected val binding by activityBinding<BINDING>(
        layoutResource
    )
    protected abstract val viewModel: VIEW_MODEL

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
        binding.also {
            it.lifecycleOwner = this
            it.setVariable(BR.viewModel, viewModel)
        }
        setupObservers()
        setupViews()
        onSetupFinished()
    }

    protected open fun setupObservers() {

    }

    protected open fun onSetupFinished(){

    }

    protected open fun initialSetup() {

    }

    protected open fun setupViews() {

    }

}
