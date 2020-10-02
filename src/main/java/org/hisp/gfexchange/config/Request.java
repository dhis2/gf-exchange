package org.hisp.gfexchange.config;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Request
{
    @JsonProperty
    private List<String> dx = new ArrayList<>();

    @JsonProperty
    private List<String> pe = new ArrayList<>();

    @JsonProperty
    private List<String> ou = new ArrayList<>();
}
