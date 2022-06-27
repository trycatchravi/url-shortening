package com.urlshortening.infrastructure.repository

import com.urlshortening.infrastructure.model.UrlInfo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlInfoRepository: CrudRepository<UrlInfo, Long> {
    fun findByOriginalUrl(originalUrl: String): UrlInfo?

    fun  findByShortUrl(shortUrl: String): UrlInfo?

}