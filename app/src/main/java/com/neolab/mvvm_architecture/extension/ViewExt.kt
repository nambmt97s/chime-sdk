package com.neolab.mvvm_architecture.extension

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.neolab.mvvm_architecture.utils.test.EspressoIdlingResource

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/6/20.
 */

fun View.show(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun viewGones(vararg views: View) {
    for (view in views) {
        view.gone()
    }
}

fun viewVisibles(vararg views: View) {
    for (view in views) {
        view.show()
    }
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (this.requestFocus()) {
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun ImageView.loadImageUrl(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .into(this)
}

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 * Using EspressoIdlingResource to wait animation end when ui test running
 */
inline fun View.showSnackbar(snackbarText: String, timeLength: Int, moreSetup: (Snackbar) -> Unit) {
    val snackbar = Snackbar.make(this, snackbarText, timeLength)
    moreSetup(snackbar)
    EspressoIdlingResource.increment()
    snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar?>() {
        override fun onShown(transientBottomBar: Snackbar?) {
            EspressoIdlingResource.decrement()
            snackbar.removeCallback(this)
        }
    })
    snackbar.show()
}

/**
 * Set an onclick listener
 */
inline fun View.debounceClick(timePrevent: Int = 1000, crossinline block: (View) -> Unit) {
    var lastTimeClicked: Long = 0

    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < timePrevent) {
            return@setOnClickListener
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        block(it)
    }
}
