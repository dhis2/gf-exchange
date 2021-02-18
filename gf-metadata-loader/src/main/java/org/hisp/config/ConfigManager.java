package org.hisp.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigManager
{
    public static Properties getConfig( String path )
    {
        try ( InputStream in = FileUtils.openInputStream( new File( path ) ) )
        {
            Properties props = new Properties();
            props.load( in );
            return props;
        }
        catch ( IOException ex )
        {
            log.error( "Could not load config from path: '{}'", path, ex );
            throw new UncheckedIOException( ex );
        }
    }
}
