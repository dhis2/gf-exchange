package org.hisp.gfexchange.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;;

@Slf4j
public class ConfigManager
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DEFAULT_CONFIG_PATH = "/opt/gfexchange/exchange.json";

    public static Exchange getExchange( String path )
    {
        String systemPropPath = System.getProperty( "exchange" );

        path = ObjectUtils.firstNonNull( path, systemPropPath, DEFAULT_CONFIG_PATH );

        log.info( "Loading exchange from path: '{}'", path );

        try ( InputStream src = FileUtils.openInputStream( new File( path ) ) )
        {
            return objectMapper.readValue( src, Exchange.class );
        }
        catch ( IOException ex )
        {
            log.error( "Could not load exchange from path: '" + path + "'", ex );
            throw new UncheckedIOException( ex );
        }
    }
}
