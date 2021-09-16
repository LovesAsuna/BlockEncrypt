package com.hyosakura.study.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LovesAsuna
 **/
@Slf4j
public class ByteUtil {
    public static byte[] xor(byte[] a, byte[] b) {
        int aLength = a.length;
        int bLength = b.length;
        byte[] bytes = new byte[aLength];
        if (aLength != bLength) {
            throw new IllegalArgumentException("The length of the two byte arrays must be equal");
        }
        for (int i = 0; i < aLength; i++) {
            bytes[i] = (byte) (Byte.toUnsignedInt(a[i]) ^ Byte.toUnsignedInt(b[i]));
        }
        return bytes;
    }

    public static class ByteBuilder {
        private final byte[] bytes;
        private int index = 0;

        public ByteBuilder(int length) {
            this.bytes = new byte[length];
        }

        public ByteBuilder append(byte[] bytes) {
            int length = bytes.length;
            if (length + index > this.bytes.length) {
                throw new IllegalArgumentException("Buffer overflow");
            }
            System.arraycopy(bytes, 0, this.bytes, index, length);
            index += length;
            return this;
        }

        public byte[] getBytes() {
            if (index < bytes.length) {
                log.debug("Return early when the buffer is not full");
            }
            return bytes;
        }
    }
}
