package org.hisp.loader;

import java.util.List;

import org.hisp.dhis.Dhis2;
import org.hisp.dhis.Dhis2Config;
import org.hisp.dhis.model.CategoryOption;
import org.hisp.dhis.response.Status;
import org.hisp.dhis.response.metadata.MetadataResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractGfLoader
{
    protected Dhis2 getDhis2()
    {
        return new Dhis2( new Dhis2Config(
            "http://localhost/dhis/",
            "lars@dhis2.org",
            "KJh6gtasd-GSDF322fa" ) );
    }

    protected void importData( List<CategoryOption> options )
    {
        Dhis2 dhis2 = getDhis2();

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
}
