package com.urlshortening.infrastructure.repository

import com.urlshortening.infrastructure.model.UrlInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository: JpaRepository<UrlInfo, Long> {
    fun findByOriginalUrl(originalUrl: String): UrlInfo
}