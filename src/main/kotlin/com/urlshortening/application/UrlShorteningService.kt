package com.urlshortening.application

import com.urlshortening.infrastructure.model.UrlInfo
import com.urlshortening.infrastructure.repository.UrlRepository
import org.apache.commons.validator.routines.UrlValidator
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UrlShorteningService(val urlRepository: UrlRepository, val baseConversion: BaseConversion) {

    fun saveUrl(url: String): String {
        val urlValidator = UrlValidator(arrayOf("http", "https"))
        if (!urlValidator.isValid(url)) {
            throw RuntimeException("Invalid URL.");
        }

        var urlInfo:UrlInfo = urlRepository.findByOriginalUrl(url)
        return if (Objects.isNull(urlInfo)) {
            urlInfo = UrlInfo(null, url!!, LocalDateTime.now())
            urlRepository.save(urlInfo)
            baseConversion.encode(urlInfo.Id!!)!!
        } else {
            baseConversion.encode(urlInfo.Id!!)!!
        }
    }

    fun getUrl(shortUrl: String): String {
        val id = baseConversion.decode(shortUrl)
        val urlInfo: Optional<UrlInfo> = urlRepository.findById(id)
        if (urlInfo.isEmpty) {
            throw RuntimeException("No such key exists");
        }
        return urlInfo.get().originalUrl!!
    }

}