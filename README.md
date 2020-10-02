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

A sample config file is found [here](config/exchange.json).

There are three ways of providing the exchange config to the tool, described in the order of precedence:

1) The exchange config can be passed as a command line argument:

```
java -jar gfexchange.jar /path/to/exchange.json
```

2) The exchange config can be passed as a system property:

```
java -jar gfexchange.jar -Dexchange = /path/to/exchange.json
```

3) The default config file location is:

```
/opt/gfexchange/exchange.json
```

## Run

```
java -jar gfexchange.jar
```
