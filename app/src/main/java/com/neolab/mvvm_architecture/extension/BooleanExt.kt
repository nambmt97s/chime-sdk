package com.neolab.mvvm_architecture.extension

/**
 * Created by ThuanPx on 3/15/20.
 */

fun Boolean?.isTrue() = this == true

fun Boolean?.isNotTrue() = !this.isTrue()
