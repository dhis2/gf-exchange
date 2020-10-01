package org.hisp.gfxchange.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;;

public class ConfigManager
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Config config;

    static
    {
        try ( InputStream src = FileUtils.openInputStream( new File( "/opt/gfxchange/config.json" ) ) )
        {
            config = objectMapper.readValue( src, Config.class );
        }
        catch ( IOException ex )
        {
            throw new UncheckedIOException( ex );
        }
    }

    public static Config getConfig()
    {
        return config;
    }
}
