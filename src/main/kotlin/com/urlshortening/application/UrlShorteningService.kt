package com.urlshortening.application

import com.google.common.hash.Hashing
import com.urlshortening.infrastructure.model.UrlInfo
import com.urlshortening.infrastructure.repository.UrlInfoRepository
import org.apache.commons.validator.routines.UrlValidator
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.util.*


private const val HOSTNAME_PREFIX = "http://rkb/"

@Service
class UrlShorteningService(val urlInfoRepository: UrlInfoRepository) {

    fun saveUrl(url: String): String {
        val urlValidator = UrlValidator(arrayOf("http", "https"))
        if (!urlValidator.isValid(url)) {
            throw RuntimeException("Invalid URL.");
        }

        val urlInfo: UrlInfo? = urlInfoRepository.findByOriginalUrl(url)
        return if (Objects.isNull(urlInfo)) {
            val urlInfo = UrlInfo(null, url, encode(url), LocalDateTime.now())
            urlInfoRepository.save(urlInfo)
            HOSTNAME_PREFIX+urlInfo.shortUrl!!
        } else {
           HOSTNAME_PREFIX+urlInfo!!.shortUrl!!
        }
    }

    fun getUrl(shortUrl: String): String {
        val urlInfo: UrlInfo? = urlInfoRepository.findByShortUrl(shortUrl.replace(HOSTNAME_PREFIX,""))
        if (Objects.isNull(urlInfo)){
            throw RuntimeException("No Url found for $shortUrl");
        }
        return urlInfo!!.originalUrl!!
    }

    fun encode(url: String): String? {
        return Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString()
    }

}