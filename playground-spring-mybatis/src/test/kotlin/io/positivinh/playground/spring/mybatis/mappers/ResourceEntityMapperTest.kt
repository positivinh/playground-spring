package io.positivinh.playground.spring.mybatis.mappers

import io.positivinh.playground.spring.mybatis.test.OracleTestcontainersConfiguration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(OracleTestcontainersConfiguration::class)
class ResourceEntityMapperTest {

    companion object {

        /**
         * moved test containers configuration to [OracleTestcontainersConfiguration]
         */
//        @Container
//        @ServiceConnection
//        var oracleContainer: OracleContainer = OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
//            .withDatabaseName("testDB")
//            .withUsername("schema_user")
//            .withPassword("testPassword")
//            .withInitScript("init-db.sql")
    }

    @Autowired
    private lateinit var resourceEntityMapper: ResourceEntityMapper

    /**
     * Simple CRUD test. Data is initialized in file _classpath:init-db.sql_, as configured in [OracleTestcontainersConfiguration]
     */
    @Test
    fun crud() {

        val retrievedEntity = resourceEntityMapper.getResourceEntityById(1)

        Assertions.assertNotNull(retrievedEntity)
        Assertions.assertEquals("some text", retrievedEntity.property)
    }
}
