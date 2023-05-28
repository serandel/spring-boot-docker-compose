package com.bestsecret.springbootdockercompose.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Post(
        @Id
        @GeneratedValue
        val id: Int?,

        val title: String,

        val body: String
)