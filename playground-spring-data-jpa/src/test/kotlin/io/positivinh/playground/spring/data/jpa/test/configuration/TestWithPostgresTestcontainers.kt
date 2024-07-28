package io.positivinh.playground.spring.data.jpa.test.configuration

import io.positivinh.playground.spring.data.jpa.configuration.SpringDataJpaConfiguration
import org.springframework.boot.testcontainers.context.ImportTestcontainers
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("testcontainers-test")
@Import(SpringDataJpaConfiguration::class)
@ImportTestcontainers(value = [PostgresTestcontainersTestConfiguration::class])
annotation class TestWithPostgresTestcontainers
