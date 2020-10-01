package org.hisp.gfxchange;

import java.io.File;
import java.io.IOException;

import org.hisp.dhis.Dhis2;
import org.hisp.dhis.Dhis2Config;
import org.hisp.dhis.query.analytics.AnalyticsQuery;
import org.hisp.dhis.query.analytics.Dimension;
import org.hisp.dhis.response.datavalueset.DataValueSetResponseMessage;
import org.hisp.gfxchange.config.ApiSource;
import org.hisp.gfxchange.config.Config;
import org.hisp.gfxchange.config.ConfigManager;
import org.hisp.gfxchange.config.Request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataExchange
{
    public void run()
        throws IOException
    {
        File file = File.createTempFile( "gf-xchange", ".json" );

        pull( file );

        push( file );

        log.info( "Data exchange completed" );
    }

    public void pull( File file )
        throws IOException
    {
        Config config = ConfigManager.getConfig();
        ApiSource api = config.getSource();
        Request request = config.getRequest();
        Dhis2 dhis2 = new Dhis2( new Dhis2Config( api.getUrl(), api.getUsername(), api.getPassword() ) );

        log.info( "Starting pull from: '{}' with user: '{}'", api.getUrl(), api.getUsername() );

        AnalyticsQuery query = AnalyticsQuery.instance()
            .addDimension( new Dimension( Dimension.DIMENSION_DX, request.getDx() ) )
            .addDimension( new Dimension( Dimension.DIMENSION_PE, request.getPe() ) )
            .addDimension( new Dimension( Dimension.DIMENSION_OU, request.getOu() ) );

        log.info( "Source query has {} data items, {} periods and {} org units",
            request.getDx().size(), request.getPe().size(), request.getOu().size() );

        dhis2.writeAnalyticsDataValueSet( query, file );

        log.info( "Wrote pull response to file: '{}'" + file.getAbsolutePath() );
    }

    public void push( File file )
        throws IOException
    {
        Config config = ConfigManager.getConfig();
        ApiSource api = config.getTarget();
        Dhis2 dhis2 = new Dhis2( new Dhis2Config( api.getUrl(), api.getUsername(), api.getPassword() ) );

        log.info( "Starting push to: '{}' with user: '{}'", api.getUrl(), api.getUsername() );

        DataValueSetResponseMessage response = dhis2.saveDataValueSet( file );

        log.info( "Data value set saved with status: '{}'" + response.getStatus() );
        log.info( response.toString() );
    }
}
