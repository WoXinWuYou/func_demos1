package com.lmj.utils.datautil;

import java.util.UUID;

/**
 * @ProjectName: classifyserver
 * @Package: com.ldst.classifyserver.utils.datautil
 * @ClassName: GUIDUtil
 * @Author: 孙培柱
 * @Description: ${description}
 * @Date: 2019-05-16 16:38
 * @Version: 1.0
 */
public class GUIDUtil {

    public static String createGUID() {
        UUID uid = UUID.randomUUID();
        return uid.toString().replace("-", "");
    }

    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        } for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
