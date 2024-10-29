package com.galacticempire.starships.services

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.galacticempire.starships.services.deserialization.BigIntegerDeserializer
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.math.BigInteger

data class SWAPIStarshipResponseEntity(
    val count: Int,
    val results: List<SWAPIStarshipEntity>,
    val next: String?,
    val previous: String?,
    // TODO: Add all other fields
)

data class SWAPIStarshipEntity(
    val name: String,
    @JsonDeserialize(using = BigIntegerDeserializer::class) val cost_in_credits: BigInteger?,
    // TODO: Add all other fields
)

class SWAPIService {

    private val restClient = RestClient.create()

    fun getAllStarships(): List<SWAPIStarshipEntity> {
        val listOfSpaceships = mutableListOf<SWAPIStarshipEntity>()
        var nextUri: String? = "https://swapi.dev/api/starships"

        do {
            val response = nextUri?.let {
                restClient.get()
                    .uri(it)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body<SWAPIStarshipResponseEntity>()
            }

            if (response != null) {
                listOfSpaceships.addAll(response.results)
                nextUri = response.next
            }
        } while (response?.next != null)

        return listOfSpaceships
    }
}