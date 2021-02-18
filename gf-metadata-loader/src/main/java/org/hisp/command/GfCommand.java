package org.hisp.command;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.Validate;
import org.hisp.loader.GfGrantsLoader;
import org.hisp.loader.GfIpLoader;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Command( name = "gf-load", description = "Loads grants and IPs" )
public class GfCommand
    implements Callable<Integer>
{
    private static final String DEFAULT_CONFIG_PATH = "/opt/gfmetadataloader/gf.conf";

    @Option(required = true, names =  {"-t", "--type"}, description = "Object type")
    private String type;

    @Option(required = true, names =  {"-f", "--file"}, description = "CSV file")
    private String file;

    @Option(required = false, names =  {"-c", "--config"}, description = "Config file", defaultValue = DEFAULT_CONFIG_PATH)
    private String config;

    /**
     * Main method.
     */
    public static void main( String... args )
    {
        int exitCode = new CommandLine( new GfCommand() ).execute( args );
        System.exit( exitCode );
    }


    @Override
    public Integer call()
        throws Exception
    {
        log.info( "Loading type: '{}' with file: '{}'", type, file );

        Validate.notNull( type );
        Validate.notNull( file );

        if ( "grant".equals( type ) )
        {
            new GfGrantsLoader().load( file );
        }
        else if ( "ip".equals( type ) )
        {
            new GfIpLoader().load( file );
        }

        return 0;
    }
}
