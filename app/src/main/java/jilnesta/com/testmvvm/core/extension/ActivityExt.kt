package jilnesta.com.testmvvm.core.extension

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import jilnesta.com.testmvvm.R

internal class DialogUtil {
    companion object {
        private var progress: AlertDialog? = null

        fun showLoading(activity: Activity) {
            val builder = AlertDialog.Builder(activity)
            builder.setView(R.layout.layout_loading_dialog)
            builder.setCancelable(false)
            progress = builder.create()
            progress?.show()
        }

        fun hideLoading() {
            progress?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        }
    }
}


fun Activity.showMessage(message: String) {
    val builder = AlertDialog.Builder(this)
    with(builder) {
        setTitle("")
        setMessage(message)
        setCancelable(false)
        setPositiveButton("閉じる") { dialog, _ -> dialog.cancel() }
        if (!isFinishing) {
            show()
        }
    }
}

fun Activity.showMessageWithTitle(title: String, message: String) {
    val builder = AlertDialog.Builder(this)
    with(builder) {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton("閉じる") { dialog, _ -> dialog.cancel() }
        if (!isFinishing) {
            show()
        }
    }
}