package org.quicache.engine.extension

import java.lang.IndexOutOfBoundsException

fun List<String>.getIndexOrElse(index: Int, elseValue: String?): String? {
    return try {
        this[index]
    } catch (exception: IndexOutOfBoundsException) {
        elseValue
    }
}