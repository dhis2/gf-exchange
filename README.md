# GF Data Exchange Tool

## Requirements

This tools requires Java JDK 8.

## Build

To build from source:

```
mvn clean package
```

## Configure

A data exchange can be configured with a config file called `exchange.json`. It must be readable by the user running the tool.

A sample config file is found [here](config/localhost-play-exchange.json).

There are three ways of providing the exchange config to the tool, described in the order of precedence:

1) The exchange config can be passed as a command line argument:

```
java -jar gf-exchange.jar /path/to/exchange.json
```

2) The exchange config can be passed as a system property:

```
java -Dexchange=/path/to/exchange.json -jar gf-exchange.jar 
```

3) The default config file location is:

```
/opt/gfexchange/exchange.json
```

The exchange configuration file must be in valid JSON format, and supports the following properties.

The `{ID scheme}` values can be `UID`, `CODE` or a custom scheme UID.

For the DHIS 2 base URL values, the protocol like `https://` should be include, and the `/api` part should _not_ be included.

The `metadata` and ID scheme elements are optional.

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
      "idScheme": "{id scheme}",
      "dataElementIdScheme": "{id scheme}",
      "orgUnitIdScheme": "{id scheme}",
      "categoryOptionIdScheme": "{id scheme}"
    }
  }  
}
```

## Run

```
java -jar gf-exchange.jar
```
