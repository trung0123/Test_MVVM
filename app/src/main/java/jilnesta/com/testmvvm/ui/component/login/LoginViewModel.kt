package jilnesta.com.testmvvm.ui.component.login

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jilnesta.com.testmvvm.data.DataRepository
import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.error.*
import jilnesta.com.testmvvm.ui.base.BaseViewModel
import jilnesta.com.testmvvm.utils.RegexUtils.isValidEmail
import jilnesta.com.testmvvm.utils.SingleEvent
import jilnesta.com.testmvvm.utils.showMessage
import jilnesta.com.testmvvm.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = loginLiveDataPrivate

    fun login(userName: String, passWord: String, token: String?) {
        if (!isValidEmail(userName)) {
            loginLiveDataPrivate.value = Resource.DataError(USER_NAME_ERROR)
            return
        }

        if (passWord.isEmpty()) {
            loginLiveDataPrivate.value = Resource.DataError(PASS_WORD_ERROR_EMPTY)
            return
        }

        if (passWord.length < 8) {
            loginLiveDataPrivate.value = Resource.DataError(PASS_WORD_ERROR_LENGTH)
            return
        }

        viewModelScope.launch {
            loginLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepository.doLogin(userName, passWord, token).collect {
                    loginLiveDataPrivate.value = it
                }
            }
        }

    }

    fun showDialogMessageError(errorCode: String) {
        when (errorCode) {
            USER_NAME_ERROR -> {
                showDialogPrivate.value = SingleEvent("メールアドレスの形式が正しくありません。")
            }
            PASS_WORD_ERROR_EMPTY -> {
                showDialogPrivate.value = SingleEvent("パスワードを入力してください。")
            }
            PASS_WORD_ERROR_LENGTH -> {
                showDialogPrivate.value = SingleEvent("パスワードは8文字以上のみ登録可能です。")
            }
            DUPLICATE_EMAIL -> {
                showDialogPrivate.value = SingleEvent("既に使われているメールアドレスのためログインできません。")
            }
            LOGIN_FAIL -> {
                showDialogPrivate.value = SingleEvent("メールアドレスまたはパスワードが間違っています")
            }
            else -> {
                handleApiError(errorCode)
            }
        }
    }


}