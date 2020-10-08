package org.hisp.gfexchange.config;

import java.util.ArrayList;
import java.util.List;

import org.hisp.dhis.model.IdScheme;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SourceRequest
{
    @JsonProperty
    private List<String> dx = new ArrayList<>();

    @JsonProperty
    private List<String> pe = new ArrayList<>();

    @JsonProperty
    private List<String> ou = new ArrayList<>();

    @JsonProperty
    private IdScheme outputIdScheme;

    @JsonProperty
    private IdScheme inputIdScheme;
}
