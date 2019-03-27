package com.yanziting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(String key) {
        try {
            byte[] input = key.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //使用指定字节数组更新摘要
            messageDigest.update(input);
            //完成哈希计算得到密文
            byte[] output = messageDigest.digest();

            //对密文进行再次操作
            int outputLength = output.length;
            char[] finalOutput = new char[outputLength * 2];
            int finalOutputIndex = 0;
            for (int i = 0; i < outputLength; ++i) {
                finalOutput[finalOutputIndex++] = HEX_DIGITS[output[i] >>> 4 & 0xf];
                finalOutput[finalOutputIndex++] = HEX_DIGITS[output[i] & 0xf];
            }
            return new String(finalOutput);
        } catch (NoSuchAlgorithmException e) {
            logger.error("不支持的加密方式:MD5");
            return "";
        }
    }
}
