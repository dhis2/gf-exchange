# GF Data Exchange Tool

## Overview

The GF data exchange is a tool which facilities data exchance between a source and a target DHIS 2 instance. It retrieves data from the source instance using the raw data value set version of the analytics API endpoint, stores the data temporarily, and pushes data to the target instance using the data value set API endpoint.

## Requirements

This tool requires a Java JDK version 8. OpenJDK is recommended.

## Download

Download the tool from the [release page](https://github.com/dhis2/gf-exchange/releases).

## Configure

A data exchange can be configured with a config file in JSON format. It must be readable by the user running the tool.

There are three ways of providing the exchange config to the tool, described in the order of precedence:

1) The exchange config can be passed as a command line argument (recommended), where any number of exchange config files can be specified:

```
java -jar gf-exchange.jar /path/to/exchange.json [/path/to/exchange2.json]
```

2) The exchange config can be passed as a system property:

```
java -Dexchange=/path/to/exchange.json -jar gf-exchange.jar 
```

3) The default config file location is:

```
/opt/gfexchange/exchange.json
```

### Config file format

The exchange configuration file must be in valid JSON format. A sample config file is found [here](config/localhost-play-exchange.json).

* The `{ID scheme}` values can be `UID`, `CODE` or a custom scheme UID on the format `attribute:{attribute-uid}`.

* For the DHIS 2 base URL values, the protocol like `https://` should be included, and the `/api` part should be excluded.

* The `metadata`, `filters` and various ID scheme elements are optional.

The following properties are supported.

```json
{
  "metadata": {
    "name": "{name}",
    "version": "{version}"
  },
  "source": {
    "api": {
      "url": "{base URL to DHIS 2 instance}",
      "username": "{username}",
      "password": "{password}"
    },
    "request": {
      "dx": [
        "{indicator ID}",
        "{data element ID}"
      ],
      "pe": [
        "{fixed period}",
        "{relative period keyword}"
      ],
      "ou": [
        "{org unit ID}",
        "{org unit level keyword}"
      ],
      "filters": [
        {
          "dimension": "{dimension ID}",
          "items": [
            "{dimension item ID}",
            "{dimension item ID}"
          ]
        }
      ],
      "outputIdScheme": "{ID scheme}",
      "inputIdScheme": "{ID scheme}"
    }
  },
  "target": {
    "api": {
      "url": "{base URL to DHIS 2 instance}",
      "username": "{username}",
      "password": "{password}"
    },
    "request": {
      "idScheme": "{ID scheme}",
      "dataElementIdScheme": "{ID scheme}",
      "orgUnitIdScheme": "{ID scheme}",
      "categoryOptionIdScheme": "{ID scheme}"
    }
  }  
}
```

## Run

```
java -jar gf-exchange.jar /path/to/exchange.json /path/to/exchange2.json
```
