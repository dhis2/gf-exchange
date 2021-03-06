package org.hisp.gfexchange.config;

import java.util.ArrayList;
import java.util.List;

import org.hisp.dhis.model.IdScheme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SourceRequest
{
    @JsonProperty
    private List<String> dx = new ArrayList<>();

    @JsonProperty
    private List<String> pe = new ArrayList<>();

    @JsonProperty
    private List<String> ou = new ArrayList<>();

    @JsonProperty
    private List<Filter> filters = new ArrayList<>();

    @JsonProperty
    private IdScheme outputIdScheme;

    @JsonProperty
    private IdScheme inputIdScheme;

    @JsonIgnore
    public boolean hasFilters()
    {
        return filters != null && !filters.isEmpty();
    }
}
