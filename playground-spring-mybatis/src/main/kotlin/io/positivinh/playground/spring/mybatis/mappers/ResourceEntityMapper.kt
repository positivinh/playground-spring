package io.positivinh.playground.spring.mybatis.mappers

import io.positivinh.playground.spring.mybatis.model.ResourceEntity
import org.apache.ibatis.annotations.*

/**
 * MyBatis entity mapper for [ResourceEntity].
 */
interface ResourceEntityMapper {

    /**
     * Retrieve a [ResourceEntity] by its id.
     */
    @Select("SELECT * FROM SCHEMA_USER.RESOURCES WHERE id = #{id}")
    @ResultMap("resourceMapping")
    fun getResourceEntityById(@Param("id") id: Long?): ResourceEntity

    /**
     * Dummy query used to declared mutualized mapping _resourceMapping_ for [ResourceEntity].
     */
    @Select("SELECT * FROM DUAL")
    @Results(
        id = "resourceMapping",
        value = [
            Result(column = "ID", property = "id", id = true, javaType = Long::class),
            Result(column = "COL_1", property = "property")
        ]
    )
    fun defaultMapping(): ResourceEntity
}
