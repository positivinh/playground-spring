package io.positivinh.playground.spring.webmvc.repository

import org.springframework.data.repository.Repository;

interface InMemoryRepository<T, ID>: Repository<T, ID>
