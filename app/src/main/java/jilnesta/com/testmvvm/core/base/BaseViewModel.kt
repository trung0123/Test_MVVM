package jilnesta.com.testmvvm.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jilnesta.com.testmvvm.utils.NETWORK_ERROR
import jilnesta.com.testmvvm.utils.SingleEvent

abstract class BaseViewModel : ViewModel() {

    val showDialogPrivate = MutableLiveData<SingleEvent<Any>>()
    val showDialog: LiveData<SingleEvent<Any>> get() = showDialogPrivate

    fun handleApiError(errorCode: String) {

        when (errorCode) {
            "400", "401" -> {
                /*openActivityOnTokenExpire()*/
            }

            NETWORK_ERROR -> {
                showDialogPrivate.value =
                    SingleEvent("接続に失敗しました。接続状況を確認して再度お試しください。")
            }

            "500" -> {
                showDialogPrivate.value =
                    SingleEvent("内部エラー,サーバー内部で問題が発生したため、処理が続行できませんでした。")
            }

            else -> {
                showDialogPrivate.value =
                    SingleEvent("エラーが発生しました。しばらくしてからもう一度お試しください。")
            }
        }
    }
}