package com.krayushkindev.allsounds.core.resource

interface StringResource: BaseInterfaceResource {
    fun getString(stringId: Int): String
}