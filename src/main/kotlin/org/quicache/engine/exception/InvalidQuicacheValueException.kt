package org.quicache.engine.exception

import java.lang.Exception

data class InvalidQuicacheValueException(val description: String): Exception(description)
