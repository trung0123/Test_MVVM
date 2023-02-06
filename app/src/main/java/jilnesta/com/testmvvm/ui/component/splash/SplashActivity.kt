package jilnesta.com.testmvvm.ui.component.splash

import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.databinding.SplashLayoutBinding
import jilnesta.com.testmvvm.ui.base.BaseActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {}
}