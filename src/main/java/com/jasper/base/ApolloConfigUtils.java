package com.jasper.base;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

import java.util.Set;


public class ApolloConfigUtils {

    public static Config datasourceConfig = null;

    public static Boolean getDataSourceSwitch = false;

    public static void init() {
        ConfigService.setMetaDomain("http://127.0.0.1:10080");
        ConfigService.setAppId("BaseDataChange");
        datasourceConfig = ConfigService.getConfig("config");
        getAndListenDatasourceChange();
    }

    public static void getAndListenDatasourceChange() {
        getDataSourceSwitch = ApolloConfigUtils.datasourceConfig.getBooleanProperty("data_source_switch",false);
        datasourceConfig.addChangeListener(changeEvent -> datasourcelistenChange(changeEvent, "data_source_switch"));
    }

    private static void datasourcelistenChange(ConfigChangeEvent changeEvent, String key) {
        Set<String> keySet = changeEvent.changedKeys();
        if (keySet.contains(key)) {
            ConfigChange change = changeEvent.getChange(key);
            getDataSourceSwitch = Boolean.valueOf(change.getNewValue());
        }
    }

}
