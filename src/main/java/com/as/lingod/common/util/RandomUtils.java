package com.as.lingod.common.util;

import java.util.Random;

/**
 * 随机数工具
 *
 * @author wangban
 */
public final class RandomUtils {

    /**
     * 定义所有的字符组成的串
     */
    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 定义所有的小写字符组成的串（不包括数字）
     */
    private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 定义所有的数字字符组成的串
     */
    private static final String NUMBER_CHAR = "0123456789";


    /**
     * 在设定范围内生成随机整数数(生成短信验证码使用)
     *
     * @param max 随机范围允许出现的最大值
     * @param min 随机范围允许出现的最小值
     * @return
     */
    public static String getRandom(Integer max, Integer min) {
        Random random = new Random();
        return String.valueOf(random.nextInt(max) % (max - min + 1) + min);
    }

    /**
     * 产生长度为length的随机字符串（包括字母和数字）
     *
     * @param length
     * @return
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 产生长度为length的随机字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 产生长度为length的随机字符串（不包括字母，包括数字）
     *
     * @param length
     * @return
     */
    public static String generateMixNum(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
        }
        return sb.toString();
    }


    /**
     * 产生长度为length的随机小写字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 产生长度为length的随机大写字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

}
