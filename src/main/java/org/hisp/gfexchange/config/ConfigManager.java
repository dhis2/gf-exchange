package org.hisp.gfexchange.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;;

@Slf4j
public class ConfigManager
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DEFAULT_CONFIG_PATH = "/opt/gfexchange/exchange.json";

    public static Exchange getExchange( String path )
    {
        String resolvedPath = resolveAndLogPath( path );

        try ( InputStream src = FileUtils.openInputStream( new File( resolvedPath ) ) )
        {
            return objectMapper.readValue( src, Exchange.class );
        }
        catch ( IOException ex )
        {
            log.error( "Could not load exchange config from path: '" + path + "'", ex );
            throw new UncheckedIOException( ex );
        }
    }

    private static String resolveAndLogPath( String path )
    {
        String resolvedPath = null;

        String systemPropPath = System.getProperty( "exchange" );

        if ( StringUtils.isNotEmpty( path ) )
        {
            log.info( "Loading exchange config from command line argument: '{}'", path );
            resolvedPath = path;
        }
        else if ( StringUtils.isNotEmpty( systemPropPath ) )
        {
            log.info( "Loading exchange config from system property: '{}'", systemPropPath );
            resolvedPath = systemPropPath;
        }
        else
        {
            log.info( "Loading exchange config from default location: '{}'", DEFAULT_CONFIG_PATH );
            resolvedPath = DEFAULT_CONFIG_PATH;
        }

        return resolvedPath;
    }
}
