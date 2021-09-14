package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;

import java.util.Arrays;
import java.util.Random;

/**
 * @author LovesAsuna
 **/
public abstract class CipherMode {
    protected final Cipher cipher;
    protected final int length;
    protected Block[] blocks;
    protected final Random random;

    /**
     * @param cipher 加密方法
     * @param length 分块长度(以字节为单位)
     */
    public CipherMode(Cipher cipher, int length) {
        this.cipher = cipher;
        this.length = length;
        random = new Random();
    }

    protected Block[] getBlocks(byte[] bytes) {
        int blockLength = bytes.length / length + bytes.length % length;
        blocks = new Block[blockLength];
        int point = 0;
        for (int i = 0; i < blockLength; i++) {
            blocks[i] = new Block(Arrays.copyOfRange(bytes, point, point + length), length);
            point += length;
        }
        return blocks;
    }

    public abstract byte[] encode(byte[] bytes);
}
