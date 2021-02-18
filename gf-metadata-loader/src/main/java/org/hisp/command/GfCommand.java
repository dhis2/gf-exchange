package org.hisp.command;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.Validate;
import org.hisp.loader.GfGrantsLoader;
import org.hisp.loader.GfIpLoader;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GfCommand
    implements Callable<Integer>
{
    @Parameter(required = true, names =  {"-t", "--type"}, description = "Object type")
    private String type;

    @Parameter(required = true, names =  {"-f", "--file"}, description = "CSV file")
    private String file;

    @Parameter(required = false, names =  {"-c", "--config"}, description = "Config file")
    private String config;

    /**
     * Main method.
     */
    public static void main( String... args )
    {
        GfCommand command = new GfCommand();

        JCommander.newBuilder()
            .addObject( command )
            .build()
            .parse( args );

        command.call();
    }

    @Override
    public Integer call()
    {
        log.info( "Loading type: '{}' with file: '{}'", type, file );

        Validate.notNull( type );
        Validate.notNull( file );

        if ( "grant".equals( type ) )
        {
            new GfGrantsLoader().load( file, config );
        }
        else if ( "ip".equals( type ) )
        {
            new GfIpLoader().load( file, config );
        }

        return 0;
    }
}
