package jilnesta.com.testmvvm.presentation.component.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.facebook.CallbackManager
import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import jilnesta.com.testmvvm.databinding.ActivityLoginBinding
import jilnesta.com.testmvvm.core.base.BaseActivity
import jilnesta.com.testmvvm.presentation.component.login.vm.LoginViewModel
import jilnesta.com.testmvvm.presentation.component.main.view.MainActivity
import jilnesta.com.testmvvm.core.extension.DialogUtil
import jilnesta.com.testmvvm.utils.SingleEvent
import jilnesta.com.testmvvm.core.extension.observe
import jilnesta.com.testmvvm.core.extension.showMessage
import org.json.JSONObject

@AndroidEntryPoint
class LoginActivity : BaseActivity(), OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private var token: String? = null

    private lateinit var callbackManager: CallbackManager

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getTokenFCM()

        setEvents()

        setPermissionFacebook()
    }

    override fun initViewBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeDialog(loginViewModel.showDialog)
    }

    private fun getTokenFCM() {
//        FirebaseInstanceId.getInstance().instanceId
//            .addOnCompleteListener { task: Task<InstanceIdResult> ->
//                if (!task.isSuccessful) {
//                    return@addOnCompleteListener
//                }
//                token = task.result?.token
//                Timber.tag("FCM_Token").d(token)
//            }
    }

    private fun setEvents() {
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.btnLoginFacebook.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    private fun setPermissionFacebook() {
        callbackManager = create()
        binding.btnLoginFacebook.setPermissions(listOf("email"))
        binding.btnLoginFacebook.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult) {
                    val accessToken = result.accessToken
                    val tokenFb = accessToken.token
                    val request = GraphRequest.newMeRequest(result.accessToken)
                    { me: JSONObject?, response: GraphResponse? ->
                        response?.let {
                            if (it.error == null) {
                                val userFullName = me?.optString("name")
                                val email = response.getJSONObject()?.optString("email")
                                val id = me?.optString("id")
//                            loginPresenter.facebookLoginCallback(
//                                email,
//                                userFullName,
//                                id,
//                                token,
//                                tokenFb
//                            )
                            } else {
                                val isLoggedIn = !accessToken.isExpired
                                if (isLoggedIn) {
                                    LoginManager.getInstance().logOut()
                                }
                            }
                            LoginManager.getInstance().logOut()
                        }
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "email,name,id")
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {}
                override fun onError(error: FacebookException) {
                    showMessage("アカウントの証認に失敗しました")
                }

            })
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> DialogUtil.showLoading(this)
            is Resource.Success -> status.data?.let {
                DialogUtil.hideLoading()
                navigateToMainScreen()
            }

            is Resource.DataError -> {
                DialogUtil.hideLoading()
                status.errorCode?.let {
                    loginViewModel.showDialogMessageError(it)
                }
            }
        }
    }

    private fun observeDialog(event: LiveData<SingleEvent<Any>>) {
        binding.root.showMessage(this, event)
    }

    private fun navigateToMainScreen() {
        val nextScreenIntent = Intent(this, MainActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> login()
        }
    }

    private fun login() {
        loginViewModel.login(
            binding.edtLoginEmail.text.trim().toString(),
            binding.edtLoginPassword.text.toString(),
            token
        )
    }


}