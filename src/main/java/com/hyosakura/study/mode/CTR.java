package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;

/**
 * @author LovesAsuna
 **/
public class CTR extends CipherMode {
    public CTR(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        return null;
    }
}
