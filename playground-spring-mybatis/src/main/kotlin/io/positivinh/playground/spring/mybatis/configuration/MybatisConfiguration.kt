package io.positivinh.playground.spring.mybatis.configuration

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * Configuration for MyBatis integration.
 *
 * see [mybatis-spring documentation](https://mybatis.org/spring/)
 */
@Configuration
@MapperScan(basePackages = ["io.positivinh.playground.spring.mybatis.mappers"], sqlSessionFactoryRef="sqlSessionFactory")
class MybatisConfiguration {

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {

        val factoryBean = SqlSessionFactoryBean()
        factoryBean.setDataSource(dataSource)
        return factoryBean.getObject()
    }
}
