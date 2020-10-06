package org.hisp.gfexchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Metadata
{
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String version;
}
