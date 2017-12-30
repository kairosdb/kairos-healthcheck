# kairos-healthcheck
Health check plugin to fix querying getMetricNames in kairosdb 1.1.3.  It may work
with older versions but I have not tried it.

This plugin will query the metric that is specified in kairos-healthcheck.properties in order
to do a backend check.

Installation
------------

1 Build the plugin with >mvn package

2 copy the jar file into /opt/kairosdb/lib

3 copy kairos-healthcheck.properties into /opt/kairosdb/conf

4 restart kairosdb

