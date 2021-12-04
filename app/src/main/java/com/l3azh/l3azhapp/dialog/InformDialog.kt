package com.l3azh.l3azhapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.l3azh.l3azhapp.databinding.ErrorInformDialogLayoutBinding
import com.l3azh.l3azhapp.databinding.SuccessInformDialogLayoutBinding
import java.lang.IllegalStateException

enum class InformType{
    SUCCESS,FAIL
}

class InformDialog(val type: InformType, val message: String) : DialogFragment() {
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
        val dialog =  activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            if (type == InformType.SUCCESS) {
                val binding: SuccessInformDialogLayoutBinding =
                    SuccessInformDialogLayoutBinding.inflate(inflater, null, false)
                binding.textInformSuccess.text = message
                builder.setView(binding.root)
            } else {
                val binding: ErrorInformDialogLayoutBinding =
                    ErrorInformDialogLayoutBinding.inflate(inflater, null, false)
                binding.textInformFail.text = message
                builder.setView(binding.root)
            }
            builder.create()
        } ?: throw IllegalStateException("Activity must not empty")
        /**
         * Phần này dể hiệu chỉnh nếu như m muốn dialog có dạng round connonr không thì thôi
         * nhớ chỉnh lại layout xml nữa
         */
        /*dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));*/
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE);
        /*val displayMetrics = DisplayMetrics()
        var layout = WindowManager.LayoutParams()
        layout.copyFrom(dialog.window!!.attributes)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireContext().display!!.getRealMetrics(displayMetrics)
        }
        layout.width = 300
        layout.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layout*/
        return dialog
    }

}