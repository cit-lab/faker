package cn.edu.sjtu.cit.apm.faker;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;


/**
 * Created by gpl on 15/9/24.
 */
public class Faker {
    private static final Logger LOGGER = LoggerFactory.getLogger(Faker.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Faker started");
        LOGGER.debug("Debug");
        LOGGER.warn("Warn");
        LOGGER.error("Error", new Exception("Test exception"));

        // generate fake rtm log
        XMLConfiguration configuration = new XMLConfiguration("etc/faker.xml");
        List<HierarchicalConfiguration> fakersConfig = configuration.configurationsAt("fakers.faker");
        for (Iterator it = fakersConfig.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration config = (HierarchicalConfiguration) it.next();
            new Log(config);
        }
    }
}
