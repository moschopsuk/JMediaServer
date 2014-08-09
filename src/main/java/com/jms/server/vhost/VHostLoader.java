package com.jms.server.vhost;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luke on 01/07/2014.
 */
public class VHostLoader {

    private static Logger log = LogManager.getLogger();

    public static ArrayList<VHost> loadVHosts(String configFile) {
        try
        {
            XMLConfiguration config = new XMLConfiguration(configFile);
            // do something with config
        }
        catch(ConfigurationException e)
        {
            log.error("VHostLoader.loadVHosts: ConfigurationException - ", e.getMessage());
        }

        return null;
    }

    private String getEvironmentalVars(String configElement) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher match = pattern.matcher(configElement);

        while (match.find())
        {
            String str = match.group().substring(2, match.group().length() - 1);
            configElement = configElement.substring(0, match.start()) + System.getProperty(str) + configElement.substring(match.end());
        }
        return configElement;
    }
}
