package com.neolab.mvvm_architecture.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/6/20.
 */

fun Fragment?.hideKeyboard() {
    (this?.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}
