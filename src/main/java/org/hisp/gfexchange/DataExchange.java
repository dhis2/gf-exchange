package org.hisp.gfexchange;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.time.StopWatch;
import org.hisp.dhis.Dhis2;
import org.hisp.dhis.Dhis2Config;
import org.hisp.dhis.query.analytics.AnalyticsQuery;
import org.hisp.dhis.query.analytics.Dimension;
import org.hisp.dhis.response.HttpStatus;
import org.hisp.dhis.response.datavalueset.DataValueSetResponseMessage;
import org.hisp.gfexchange.config.ApiSource;
import org.hisp.gfexchange.config.ConfigManager;
import org.hisp.gfexchange.config.Exchange;
import org.hisp.gfexchange.config.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataExchange
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void run( String path )
        throws IOException
    {
        StopWatch watch = StopWatch.createStarted();

        log.info( "Starting data exchange" );

        Exchange exchange = ConfigManager.getExchange( path );

        File file = File.createTempFile( "gfexchange_", ".json" );

        pull( exchange, file );

        push( exchange, file );

        watch.stop();

        log.info( "Data exchange completed: '{}'", watch.formatTime() );
    }

    public void pull( Exchange exchange, File file )
        throws IOException
    {
        ApiSource api = exchange.getSource();
        Request request = exchange.getRequest();
        Dhis2 dhis2 = new Dhis2( new Dhis2Config( api.getUrl(), api.getUsername(), api.getPassword() ) );

        checkStatus( dhis2 );

        log.info( "Starting pull from: '{}' with user: '{}'", api.getUrl(), api.getUsername() );

        AnalyticsQuery query = AnalyticsQuery.instance()
            .addDimension( new Dimension( Dimension.DIMENSION_DX, request.getDx() ) )
            .addDimension( new Dimension( Dimension.DIMENSION_PE, request.getPe() ) )
            .addDimension( new Dimension( Dimension.DIMENSION_OU, request.getOu() ) );

        log.info( "Source query has {} data item(s), {} period(s) and {} org unit(s)",
            request.getDx().size(), request.getPe().size(), request.getOu().size() );

        dhis2.writeAnalyticsDataValueSet( query, file );

        log.info( "Wrote pull response to file: '{}'", file.getAbsolutePath() );
    }

    public void push( Exchange exchange, File file )
        throws IOException
    {
        ApiSource api = exchange.getTarget();
        Dhis2 dhis2 = new Dhis2( new Dhis2Config( api.getUrl(), api.getUsername(), api.getPassword() ) );

        checkStatus( dhis2 );

        log.info( "Starting push to: '{}' with user: '{}'", api.getUrl(), api.getUsername() );

        DataValueSetResponseMessage response = dhis2.saveDataValueSet( file );

        log.info( "Data value set saved with status: '{}'", response.getStatus() );
        logPrettyJson( response );
    }

    private void checkStatus( Dhis2 dhis2 )
        throws IOException
    {
        HttpStatus status = dhis2.getStatus();

        if ( status != HttpStatus.OK )
        {
            throw new IOException( String.format( "DHIS 2 instance '%s' not available for user '%s' with status: '%s'",
                dhis2.getDhis2Url(), dhis2.getDhis2Username(), status ) );
        }
        else
        {
            log.info( "DHIS 2 instance is available: '{}'", dhis2.getDhis2Url() );
        }
    }

    private void logPrettyJson( Object object )
        throws IOException
    {
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( object );
        log.info( json );
    }
}
