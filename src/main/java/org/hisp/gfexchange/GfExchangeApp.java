package org.hisp.gfexchange;

public class GfExchangeApp
{
    public static void main( String[] args )
        throws Exception
    {
        String path = args != null && args.length > 0 ? args[0] : null;

        new DataExchange().run( path );
    }
}
