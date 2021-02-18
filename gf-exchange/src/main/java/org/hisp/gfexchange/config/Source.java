package org.hisp.gfexchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Source
{
    @JsonProperty
    private Api api;

    @JsonProperty
    private SourceRequest request;
}
