package org.hisp.loader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hisp.dhis.Dhis2;
import org.hisp.dhis.model.Attribute;
import org.hisp.dhis.model.AttributeValue;
import org.hisp.dhis.model.CategoryOption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GfIpLoader
    extends AbstractGfLoader
{
    public void load( String file, String config )
    {
        try
        {
            Dhis2 dhis2 = getDhis2( config );

            importData( getOptions( file ), dhis2 );
        }
        catch ( IOException ex )
        {
            throw new UncheckedIOException( ex );
        }
    }

    private List<CategoryOption> getOptions( String file )
        throws IOException
    {
        Reader in = new FileReader( file );

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
            .withHeader()
            .withFirstRecordAsHeader()
            .parse( in );

        Attribute grantId = new Attribute();
        grantId.setId( "itzf8J8mvx4" );

        List<CategoryOption> options = new ArrayList<>();

        for ( CSVRecord record : records )
        {
            CategoryOption co = new CategoryOption();

            co.setCode( record.get( "IPID" ) );
            co.setName( "IP_" + record.get( "IPName" ) );
            co.setShortName( record.get( "IPName" ) );

            co.addAttributeValue( new AttributeValue( grantId, record.get( "GrantID" ) ) );

            options.add( co );
        }

        log.info( "Created category options: {}", options.size() );

        return options;
    }
}
