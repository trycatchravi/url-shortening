package com.urlshortening.controller

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext

import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UrlShorteningControllerITTest {
    @Autowired
    lateinit var server: ServletWebServerApplicationContext

    @BeforeEach
    fun setUp() {
        RestAssured.port = server.webServer.port
    }

    @Test
    fun `should create short url and get original url`() {
        val originalUrl = "https://www.koolstories.com/blog/best-coding-practices-in-kotlin"
        val shortUrl = RestAssured.given()
            .param("url",originalUrl)
            .`when`()
            .post("/api/save")
            .then()
            .assertThat().statusCode(201)
            .extract().asString()

        RestAssured.given()
            .param("shortUrl", shortUrl)
            .`when`()
            .get("/api/get")
            .then()
            .assertThat().statusCode(200)
    }

    @Test
    fun `should fail for invalid url shorten request`() {
        val invalidUrl = "htpspth"
        val shortUrl = RestAssured.given()
            .param("url",invalidUrl)
            .`when`()
            .post("/api/save")
            .then()
            .assertThat().statusCode(400)
            .extract().asString()

        assertEquals("Invalid URL.", shortUrl)
    }

}