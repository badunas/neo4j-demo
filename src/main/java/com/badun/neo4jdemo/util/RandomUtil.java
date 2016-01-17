package com.badun.neo4jdemo.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Artsiom Badun.
 */
public class RandomUtil {
    public static final String VIDEO_PREFIX = "usid:cms:video:";

    public static String getRandomVideoUsid() {
        int i = ThreadLocalRandom.current().nextInt(100000, 999999);
        return VIDEO_PREFIX + i;
    }
}
