package org.hisp.loader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hisp.config.ConfigManager;
import org.hisp.dhis.Dhis2;
import org.hisp.dhis.Dhis2Config;
import org.hisp.dhis.model.CategoryOption;
import org.hisp.dhis.response.Status;
import org.hisp.dhis.response.metadata.MetadataResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractGfLoader
{
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "MM/dd/yyyy" );

    protected Dhis2 getDhis2( String path )
    {
        Properties config = ConfigManager.getConfig( path );

        String url = config.getProperty( "dhis.url" );
        String username = config.getProperty( "dhis.username" );
        String password = config.getProperty( "dhis.password" );

        return new Dhis2( new Dhis2Config( url, username, password ) );
    }

    protected void importData( List<CategoryOption> options, Dhis2 dhis2 )
    {
        int success = 0;
        int error = 0;

        for ( CategoryOption option : options )
        {
            MetadataResponseMessage response = dhis2.saveCategoryOption( option );

            if ( response.getStatus() == Status.OK )
            {
                log.debug( response.toString() );
                success++;
            }
            else
            {
                log.warn( response.toString() );
                error++;
            }
        }

        log.info( "Imported category options, success: {}, errors: {}", success, error );
    }

    /**
     * Returns a date from the <code>MM/dd/yyyy<code> format.
     *
     * @param input the date string.
     * @return a date.
     */
    protected Date getDate( String input )
    {
        if ( input == null )
        {
            return null;
        }

        try
        {
            return DATE_FORMAT.parse( input );
        }
        catch ( ParseException ex )
        {
            log.warn( "Could not parse date string: '{}'" );
            return null;
        }
    }
}
