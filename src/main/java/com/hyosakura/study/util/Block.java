package com.hyosakura.study.util;

import java.util.Arrays;

/**
 * 数据块，长度为字节长度的整数倍
 *
 * @author LovesAsuna
 **/
public class Block {
    private final byte[] buffer;

    /**
     * @param buffer 块缓冲
     * @param length 块长度(以字节为单位)
     **/
    public Block(byte[] buffer, int length) {
        int bufferLength = buffer.length;
        if (bufferLength > length) {
            throw new IllegalArgumentException("length of buffer must shorter than " + length);
        } else if (bufferLength < length) {
            this.buffer = new byte[length];
            System.arraycopy(buffer, 0, this.buffer, 0, bufferLength);
            Arrays.fill(this.buffer, bufferLength, length, (byte) (length - bufferLength));
        } else {
            this.buffer = buffer;
        }
    }

    public byte[] getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "Block{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }
}
