package com.bestsecret.springbootdockercompose.controller

import com.bestsecret.springbootdockercompose.model.Post
import org.json.JSONException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {
    companion object {
        @Container
        @ServiceConnection
        val postgres = PostgreSQLContainer("postgres:latest")
    }

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `a post added is read back from the collection`() {
        val post = Post(null, "test title", "test body")
        val url = "http://localhost:$port/api/posts"

        val savedPost = restTemplate.postForObject<Post>(url, HttpEntity(post)) ?: throw RuntimeException("No saved post")

        // Cannot use List<Post> because of Java type erasure
        val response = restTemplate.getForObject<Array<Post>>(url)

        assertEquals(1, response?.size)
        val retrievedPost = response!![0]

        // Data classes are discouraged to be used as JPA entities
        assertEquals(savedPost.id, retrievedPost.id)
        assertEquals(savedPost.title, retrievedPost.title)
        assertEquals(savedPost.body, retrievedPost.body)
    }
}