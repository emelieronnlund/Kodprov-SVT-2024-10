package com.galacticempire.starships

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StarshipsApplication

fun main(args: Array<String>) {
	runApplication<StarshipsApplication>(*args)
}
