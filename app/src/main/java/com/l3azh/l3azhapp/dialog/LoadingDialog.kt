package com.l3azh.l3azhapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.l3azh.l3azhapp.databinding.DialogLoadingBinding
import kotlinx.coroutines.*

class LoadingDialog : DialogFragment() {

    lateinit var binding: DialogLoadingBinding

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        var layout = WindowManager.LayoutParams()
        layout.copyFrom(dialog!!.window!!.attributes)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireContext().display!!.getRealMetrics(displayMetrics)
        }
        layout.width = displayMetrics.widthPixels * 4 / 6
        layout.height = WindowManager.LayoutParams.WRAP_CONTENT
        //dialog!!.window!!.attributes = layout
        dialog!!.window!!.setLayout(layout.width, layout.height)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = activity?.let {
            val builder = AlertDialog.Builder(it)

            binding = DialogLoadingBinding.inflate(layoutInflater, null, false)

            builder.setView(binding.root)
            this.isCancelable = false
            builder.create()

        } ?: throw IllegalArgumentException("Acitivity must not be null")

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    fun cancelDialog() {
        if (dialog != null) {
            dialog?.cancel()
        } else {
            Log.e(TAG, "cancelDialog: Dialog must not be null to be cancel")
        }
    }

    companion object {

        var instance: LoadingDialog? = null
        val TAG = LoadingDialog::class.simpleName

        fun show(activity: FragmentActivity) {
            if (instance == null) {
                instance = LoadingDialog()
            }
            instance!!.show(activity.supportFragmentManager, TAG)
        }

        suspend fun close() = CoroutineScope(Dispatchers.Default).launch {
            if (instance == null) {
                delay(1000)
            }
            if(instance!!.dialog == null){
                /**
                 * Tai sao l???i nh?? v???y
                 * V?? khi instance dc kh???i t???o => UI b???t ?????u build v?? khi set cancel dialog li???n m??
                 * ko check dialog th?? s??? x???y ra l???i dialog c???a instance == null
                 * instance c?? th??? != null m?? dialog c???a instance c?? th??? == null
                 * ph???i ?????i h??m onCreateDialog tr??? v??? dialog th?? m???i h???t null
                 */
                while(instance!!.dialog == null){
                    delay(1000)
                }
            }
            instance?.cancelDialog()
            instance = null
        }
    }


}