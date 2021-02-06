package br.eng.r2a.quicache.engine.exception

import java.lang.Exception

data class InvalidQuicacheValueException(val description: String): Exception(description)
