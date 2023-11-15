package com.example.mylibrary.repository

import com.example.mylibrary.models.LivroEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface LivroRepository : JpaRepository<LivroEntity, Long>