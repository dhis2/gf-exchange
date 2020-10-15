package org.hisp.gfexchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * ToDo
 * <p>
 * <ul>
 * <li>Filter support for analytics queries</li>
 * <li>Id scheme support in exchange config file</li>
 * <li>Log to file</li>
 * </ul>
 */
@Slf4j
public class GfExchangeApp
{
    public static void main( String[] args )
        throws Exception
    {
        DataExchange exchange = new DataExchange();

        List<String> arguments = new ArrayList<>( Arrays.asList( args ) );

        if ( arguments.isEmpty() )
        {
            exchange.run();
        }
        else
        {
            for ( String path : arguments )
            {
                log.info( "Detected config path argument: '{}'", path );

                exchange.run( path );
            }
        }
    }
}
