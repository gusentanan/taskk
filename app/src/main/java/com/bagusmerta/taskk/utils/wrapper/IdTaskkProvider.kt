package com.bagusmerta.taskk.utils.wrapper

import java.util.UUID

interface IdTaskkProvider {
    fun generateId(): String
}

class IdTaskkProviderImpl: IdTaskkProvider {
    override fun generateId(): String {
        return UUID.randomUUID().toString()
    }

}