package com.jasper.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource=DBContextHolder.getDataSource();
        return datasource;
    }

}