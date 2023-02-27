package jilnesta.com.testmvvm.utils

import android.app.Activity
import android.app.AlertDialog
import jilnesta.com.testmvvm.R

class ActivityExt {
    companion object {
        var progress: androidx.appcompat.app.AlertDialog? = null

        fun showProgress(activity: Activity) {
            val builder = androidx.appcompat.app.AlertDialog.Builder(activity)
            builder.setView(R.layout.layout_loading_dialog)
            builder.setCancelable(false)
            progress = builder.create()
            progress!!.show()
        }

        fun dismiss() {
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