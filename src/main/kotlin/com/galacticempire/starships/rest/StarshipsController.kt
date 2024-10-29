package com.galacticempire.starships.rest

import com.galacticempire.starships.services.SWAPIService
import com.galacticempire.starships.services.SWAPIStarshipEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class StarshipsController {

    @Operation(summary = "Fetch starships", description= "Fetch 10 starships and sort by cost")

    @GetMapping("/spaceships")
    fun getStarshipsByCost(@Autowired @Parameter(hidden = true) swapiService: SWAPIService): List<SWAPIStarshipEntity> {
        return swapiService.getAllStarships()
            .sortedByDescending { s -> s.cost_in_credits }
            .take(10)
    }
}