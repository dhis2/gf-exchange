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
public class GfGrantsLoader
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

        Attribute geoId = new Attribute();
        geoId.setId( "TMgcURK4VeS" );

        Attribute geoIso3 = new Attribute();
        geoIso3.setId( "jXVKhlJmUKz" );

        Attribute component = new Attribute();
        component.setId( "O9tEEjetJHV" );

        Attribute status = new Attribute();
        status.setId( "PVIWD7lBIIc" );

        Attribute recipient = new Attribute();
        recipient.setId( "cV1DG96AnLV" );

        Attribute recipientCode = new Attribute();
        recipientCode.setId( "WYgxNy6vh23" );

        List<CategoryOption> options = new ArrayList<>();

        for ( CSVRecord record : records )
        {
            CategoryOption co = new CategoryOption();

            co.setCode( record.get( "GrantID" ) );
            co.setName( "GRT_" + record.get( "GrantName" ) );
            co.setShortName( record.get( "GrantName" ) );

            co.addAttributeValue( new AttributeValue( geoId, record.get( "GeoID" ) ) );
            co.addAttributeValue( new AttributeValue( geoIso3, record.get( "GeoISO3" ) ) );
            co.addAttributeValue( new AttributeValue( component, record.get( "Component" ) ) );
            co.addAttributeValue( new AttributeValue( status, record.get( "Status" ) ) );
            co.addAttributeValue( new AttributeValue( recipient, record.get( "Recipient" ) ) );
            co.addAttributeValue( new AttributeValue( recipientCode, record.get( "RecipientCode" ) ) );

            options.add( co );
        }

        log.info( "Created category options: {}", options.size() );

        return options;
    }
}
