package com.galacticempire.starships.rest

import com.galacticempire.starships.services.SWAPIService
import com.galacticempire.starships.services.SWAPIStarshipEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class StarshipsController {
    @GetMapping("/")
    fun getAllStarships(@Autowired swapiService: SWAPIService): List<SWAPIStarshipEntity> {
        return swapiService.getAllStarships()
            .sortedByDescending { s -> s.cost_in_credits }
            .take(10)
    }
}