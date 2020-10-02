package org.hisp.gfexchange.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiSource
{
    @JsonProperty
    private String url;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;
}
