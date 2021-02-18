package org.hisp.gfexchange.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Filter
{
    @JsonProperty
    private String dimension;

    @JsonProperty
    private List<String> items;
}
