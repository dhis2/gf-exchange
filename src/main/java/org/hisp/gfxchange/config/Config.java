package org.hisp.gfxchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Config
{
    @JsonProperty
    private ApiSource source;

    @JsonProperty
    private ApiSource target;

    @JsonProperty
    private Request request;
}
