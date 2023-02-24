package jilnesta.com.testmvvm.ui.component.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.facebook.*
import com.facebook.CallbackManager.Factory.create
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import dagger.hilt.android.AndroidEntryPoint
import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.databinding.ActivityLoginBinding
import jilnesta.com.testmvvm.ui.base.BaseActivity
import jilnesta.com.testmvvm.ui.component.recipes.RecipesListActivity
import jilnesta.com.testmvvm.utils.*
import org.json.JSONObject
import timber.log.Timber

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
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeSnackBarMessages(loginViewModel.showSnackBar)
        observeToast(loginViewModel.showToast)
    }

    private fun getTokenFCM() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task: Task<InstanceIdResult> ->
                if (!task.isSuccessful) {
                    return@addOnCompleteListener
                }
                token = task.result?.token
                Timber.tag("FCM_Token").d(token)
            }
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
        binding.btnLoginFacebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                val accessToken = result.accessToken
                val tokenFb = accessToken.token
                val request = GraphRequest.newMeRequest(result.accessToken)
                { me: JSONObject?, response: GraphResponse? ->
                    response?.let {
                        if(it.error == null) {
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
//                showErrorMessage("アカウントの証認に失敗しました")
            }

        })
    }

    private fun doLogin() {
//        loginViewModel.doLogin(
//            binding.username.text.trim().toString(),
//            binding.password.text.toString()
//        )
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
//            is Resource.Loading -> binding.loaderView.toVisible()
//            is Resource.Success -> status.data?.let {
//                binding.loaderView.toGone()
//                navigateToMainScreen()
//            }
//            is Resource.DataError -> {
//                binding.loaderView.toGone()
//                status.errorCode?.let { loginViewModel.showToastMessage(it) }
//            }
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun navigateToMainScreen() {
        val nextScreenIntent = Intent(this, RecipesListActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    override fun onClick(p0: View?) {

    }


}