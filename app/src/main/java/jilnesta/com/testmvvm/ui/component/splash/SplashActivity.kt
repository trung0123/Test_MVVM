package jilnesta.com.testmvvm.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.SPLASH_DELAY
import jilnesta.com.testmvvm.databinding.SplashLayoutBinding
import jilnesta.com.testmvvm.ui.base.BaseActivity
import jilnesta.com.testmvvm.ui.component.login.LoginActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: SplashLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {}

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}