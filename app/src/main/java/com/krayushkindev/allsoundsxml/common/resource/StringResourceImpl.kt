package com.krayushkindev.allsounds.common.resource

import android.content.Context
import androidx.annotation.StringRes
import com.krayushkindev.allsounds.core.resource.StringResource
import java.lang.ref.WeakReference

class StringResourceImpl : StringResource {

    private var contextRef: WeakReference<Context?> = WeakReference(null)
    private val context: Context get() = checkNotNull(contextRef.get())

    // Секция утилит
    fun setupContext(context: Context): StringResourceImpl {
        contextRef.clear()
        contextRef = WeakReference(context)
        return this
    }

    override fun getString(stringId: Int): String {
        return string(stringId)
    }

    private fun string(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}