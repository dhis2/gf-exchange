package org.hisp.gfexchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Exchange
{
    @JsonProperty
    private Metadata metadata;

    @JsonProperty
    private Source source;

    @JsonProperty
    private Target target;
}
