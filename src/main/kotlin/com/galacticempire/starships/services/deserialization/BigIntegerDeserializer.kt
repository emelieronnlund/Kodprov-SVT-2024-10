package com.galacticempire.starships.services.deserialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.math.BigInteger

// Catch 'unknown' values and set to zero
class BigIntegerDeserializer: JsonDeserializer<BigInteger?>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): BigInteger? {
        try {
            return p0?.text?.let { BigInteger(it) }
        } catch (ex: NumberFormatException) {
            return BigInteger.ZERO
        }
    }
}