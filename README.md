# GF Data Exchange Tool

## Requirements

This tools requires Java JDK 8.

## Build

To build from source:

```
mvn clean package
```

## Configure

A data exchange can be configured with a config file called `exchange.json`. It must be readable by the user running this tool.

There are three ways of providing the exchange config to the tool. 

The exchange config can be passed as a system property:

```
java -jar gfexchange.jar -Dexchange = /path/to/exchange.json
```

The exchange config can be passed as a command line argument directly:

```
java -jar gfexchange.jar /path/to/exchange.json
```

The default location which will be searched is:

```
/opt/gfexchange/exchange.json
```

## Run

```
java -jar gfexchange.jar
```
