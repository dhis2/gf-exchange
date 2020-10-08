package org.hisp.gfexchange.config;

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
public class TargetRequest
{
    @JsonProperty
    private IdScheme dataElementIdScheme;

    @JsonProperty
    private IdScheme orgUnitIdScheme;

    @JsonProperty
    private IdScheme categoryOptionComboIdScheme;

    @JsonProperty
    private IdScheme idScheme;
}
