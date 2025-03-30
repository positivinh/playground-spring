package io.positivinh.playground.yaml.model

import com.fasterxml.jackson.annotation.JsonProperty

class Mapping {
    @JsonProperty("file-type")
    var fileType: String? = null

    @JsonProperty("mapping-rules")
    var mappingRules = mutableListOf<Concept>()
}
