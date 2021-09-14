package com.hyosakura.study;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.mode.*;

import java.nio.charset.StandardCharsets;

/**
 * @author LovesAsuna
 **/
public class DefaultEncoder {
    private final CipherMode mode;

    public DefaultEncoder(Cipher cipher, Mode mode) {
        switch (mode) {
            case CBC:
                this.mode = new CBC(cipher, 8);
                break;
            case CFB:
                this.mode = new CFB(cipher, 8);
                break;
            case OFB:
                this.mode = new OFB(cipher, 8);
                break;
            case CTR:
                this.mode = new CTR(cipher, 8);
                break;
            case ECB:
            default:
                this.mode = new ECB(cipher, 8);
                break;
        }
    }

    public byte[] encode(byte[] bytes) {
        return mode.encode(bytes);
    }

    public String encodeToString(byte[] bytes) {
        return new String(encode(bytes), StandardCharsets.UTF_8);
    }

    enum Mode {
        ECB,
        CBC,
        CFB,
        OFB,
        CTR
    }
}
