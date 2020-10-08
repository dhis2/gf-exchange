package org.hisp.gfexchange;

/**
 * ToDo
 * <p>
 * <ul>
 * <li>Filter support for analytics queries</li>
 * <li>Id scheme support in exchange config file</li>
 * <li>Log to file</li>
 * </ul>
 */
public class GfExchangeApp
{
    public static void main( String[] args )
        throws Exception
    {
        String path = ( args != null && args.length > 0 ) ? args[0] : null;

        new DataExchange().run( path );
    }
}
