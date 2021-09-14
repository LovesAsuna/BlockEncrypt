package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;
import com.hyosakura.study.util.ByteUtil;

/**
 * @author LovesAsuna
 **/
public class CBC extends CipherMode {
    public CBC(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        Block[] blocks = getBlocks(bytes);
        int blockLength = blocks.length;
        ByteUtil.ByteBuilder builder = new ByteUtil.ByteBuilder(bytes.length);
        byte[] initBlock = new byte[length];
        random.nextBytes(initBlock);
        byte[] output;

        byte[] xorByte = ByteUtil.xor(initBlock, blocks[0].getBuffer());
        Block xorBlock = new Block(xorByte, length);
        output = cipher.encrypt(xorBlock);
        builder.append(output);

        for (int i = 1; i < blockLength; i++) {
            xorByte = ByteUtil.xor(output, blocks[i].getBuffer());
            xorBlock = new Block(xorByte, length);
            output = cipher.encrypt(xorBlock);
            builder.append(output);
        }

        return builder.getBytes();
    }
}
