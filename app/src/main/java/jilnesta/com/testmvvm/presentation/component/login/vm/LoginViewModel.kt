package jilnesta.com.testmvvm.presentation.component.login.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jilnesta.com.testmvvm.core.base.BaseViewModel
import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.local.LocalData
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import jilnesta.com.testmvvm.presentation.component.login.remote.repository.LoginRepository
import jilnesta.com.testmvvm.utils.DUPLICATE_EMAIL
import jilnesta.com.testmvvm.utils.LOGIN_FAIL
import jilnesta.com.testmvvm.utils.PASSWORD_ERROR_EMPTY
import jilnesta.com.testmvvm.utils.PASSWORD_ERROR_LENGTH
import jilnesta.com.testmvvm.utils.RegexUtils.isValidEmail
import jilnesta.com.testmvvm.utils.SingleEvent
import jilnesta.com.testmvvm.utils.USERNAME_ERROR
import jilnesta.com.testmvvm.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataRepository: LoginRepository,
    private val localRepository: LocalData
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = loginLiveDataPrivate

    fun login(userName: String, passWord: String, token: String?) {
        if (!isValidEmail(userName)) {
            loginLiveDataPrivate.value = Resource.DataError(USERNAME_ERROR)
            return
        }

        if (passWord.isEmpty()) {
            loginLiveDataPrivate.value = Resource.DataError(PASSWORD_ERROR_EMPTY)
            return
        }

        if (passWord.length < 8) {
            loginLiveDataPrivate.value = Resource.DataError(PASSWORD_ERROR_LENGTH)
            return
        }

        viewModelScope.launch {
            loginLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepository.doLogin(userName, passWord, token).collect {
                    loginLiveDataPrivate.value = it
                    saveUser(it.data)
                }
            }
        }
    }

    private fun saveUser(response: LoginResponse?) {
        localRepository.setUserToken(response?.token ?: "")
        localRepository.setUserCode(response?.code ?: "")
    }

    fun showDialogMessageError(errorCode: String) {
        when (errorCode) {
            USERNAME_ERROR -> {
                showDialogPrivate.value = SingleEvent("メールアドレスの形式が正しくありません。")
            }

            PASSWORD_ERROR_EMPTY -> {
                showDialogPrivate.value = SingleEvent("パスワードを入力してください。")
            }

            PASSWORD_ERROR_LENGTH -> {
                showDialogPrivate.value = SingleEvent("パスワードは8文字以上のみ登録可能です。")
            }

            DUPLICATE_EMAIL -> {
                showDialogPrivate.value =
                    SingleEvent("既に使われているメールアドレスのためログインできません。")
            }

            LOGIN_FAIL -> {
                showDialogPrivate.value =
                    SingleEvent("メールアドレスまたはパスワードが間違っています")
            }

            else -> {
                handleApiError(errorCode)
            }
        }
    }


}