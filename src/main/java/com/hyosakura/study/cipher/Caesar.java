package com.hyosakura.study.cipher;

import com.hyosakura.study.util.Block;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author LovesAsuna
 **/
public class Caesar implements Cipher {
    /**
     * 块长度必须是一个字符所占的位数的整数倍
     *
     * @param block 数据块
     * @return {@link java.lang.String} 密文
     **/
    @Override
    public byte[] encrypt(Block block) {
        byte[] buffer = block.getBuffer();
        byte[] bytes = Arrays.copyOf(buffer, buffer.length);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] += 3;
        }
        return bytes;
    }
}
