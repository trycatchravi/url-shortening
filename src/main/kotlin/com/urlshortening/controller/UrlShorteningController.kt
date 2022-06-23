package com.urlshortening.controller

import com.urlshortening.application.UrlShorteningService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.nio.charset.StandardCharsets


@RestController
@RequestMapping(("/api"))
class UrlShorteningController(val urlShorteningService: UrlShorteningService) {

    @PostMapping("/save")
    fun create(@RequestParam url: String): ResponseEntity<*>? {
        return try {
            val shortUrl = urlShorteningService.saveUrl(url!!)
            ResponseEntity.status(HttpStatus.CREATED).body(shortUrl)
        } catch (ex: RuntimeException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
        }
    }

    @GetMapping("/get")
    fun getUrl(@RequestParam shortUrl: String?): ResponseEntity<*>?  {
        return try {
            val url = urlShorteningService.getUrl(shortUrl!!)
            ResponseEntity.status(HttpStatus.OK).body(url)
        }
        catch (ex: RuntimeException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @GetMapping("/getAndRedirect")
    fun getUrlAndRedirect(@RequestParam shortUrl: String?) {
         try {
            val url = urlShorteningService.getUrl(shortUrl!!)
            val uri: URI = URI.create(url)
            ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(uri)
        }
        catch (ex: RuntimeException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

}