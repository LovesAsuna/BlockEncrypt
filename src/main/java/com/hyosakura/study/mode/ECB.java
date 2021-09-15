package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;
import com.hyosakura.study.util.ByteUtil;

/**
 * @author LovesAsuna
 **/
public class ECB extends CipherMode {
    /**
     * @param cipher 加密方法
     * @param length 分块长度(以字节为单位)
     */
    public ECB(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        Block[] blocks = getBlocks(bytes);
        ByteUtil.ByteBuilder builder = new ByteUtil.ByteBuilder(bytes.length);
        for (Block block : blocks) {
            builder.append(cipher.encrypt(block));
        }
        return builder.getBytes();
    }
}
