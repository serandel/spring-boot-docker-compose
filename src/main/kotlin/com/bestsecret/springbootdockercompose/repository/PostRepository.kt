package com.bestsecret.springbootdockercompose.repository

import com.bestsecret.springbootdockercompose.model.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Int>