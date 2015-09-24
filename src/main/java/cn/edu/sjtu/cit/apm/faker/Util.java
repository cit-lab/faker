package cn.edu.sjtu.cit.apm.faker;

import org.apache.commons.configuration.HierarchicalConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by gpl on 15/9/24.
 */
public class Util {
    public static List<String> readStrings(List<HierarchicalConfiguration> configurations) {
        List<String> s = new ArrayList<String>();
        for (Iterator it = configurations.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration configuration = (HierarchicalConfiguration) it.next();
//            s.add(configuration.getString("."));
//            System.out.println(configuration.getString("."));
            s.add(configuration.getString("."));
        }
        return s;
    }

    public static String randomString(List<String> strings){
        return strings.get(ThreadLocalRandom.current().nextInt(strings.size()));
    }
}
