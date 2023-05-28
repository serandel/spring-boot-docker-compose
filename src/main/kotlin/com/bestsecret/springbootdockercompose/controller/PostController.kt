package com.bestsecret.springbootdockercompose.controller

import com.bestsecret.springbootdockercompose.model.Post
import com.bestsecret.springbootdockercompose.repository.PostRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController (private val repository: PostRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun save(@RequestBody post: Post) : Post = repository.save(post)
}