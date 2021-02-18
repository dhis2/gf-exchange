package org.hisp.gfexchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Target
{
    @JsonProperty
    private Api api;

    @JsonProperty
    private TargetRequest request;
}
