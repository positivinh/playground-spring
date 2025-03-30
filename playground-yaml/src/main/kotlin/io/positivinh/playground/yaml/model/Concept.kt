package io.positivinh.playground.yaml.model

import com.fasterxml.jackson.annotation.JsonProperty

class Concept {

    var name: String? = null

    @JsonProperty("class-name")
    var className: String? = null
    var properties: MutableList<Property> = mutableListOf()
}
