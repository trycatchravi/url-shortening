package com.urlshortening.infrastructure.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="url_info")
data class UrlInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val Id: Long ?=null,
    val originalUrl: String ?=null,
    val createdAt: LocalDateTime
)