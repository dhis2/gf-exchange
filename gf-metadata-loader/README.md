# GF Metadata Loader

## Overview

The GF metadata loader is a tool which facilitates loading of metadata like grants and IPs from CSV to a DHIS 2 instance.

## Requirements

This tool requires a Java JDK version 8. OpenJDK is recommended.

## Configure

Add a configuration file to the following location:

```
/opt/gfmetadataloader/gf.conf
```

The following properties are requried:

```
dhis.url = <url>
dhis.username = <username>
dhis.password = <password>
```

## Use

Use the tool with the following command:

```
java -jar gf-metadata-loader.jar -t <type> -f <path-to-csv-file> -c <optional-path-to-config-file> 
```

The `type` value can be `grant` or `ip`.

