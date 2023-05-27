package com.example.pk_device_android.presentation

import android.content.Context

interface StringResourceProvider {
    fun getString(resourceId: Int): String
    fun getString(resourceId: Int, vararg formatArgs: Any): String
}

class StringResourceProviderImpl(private val context: Context) : StringResourceProvider {
    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getString(resourceId: Int, vararg formatArgs: Any): String {
        return context.getString(resourceId, *formatArgs)
    }
}