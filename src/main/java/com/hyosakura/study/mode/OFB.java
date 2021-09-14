package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;
import com.hyosakura.study.util.ByteUtil;

/**
 * @author LovesAsuna
 **/
public class OFB extends CipherMode {
    public OFB(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        Block[] blocks = getBlocks(bytes);
        int blockLength = blocks.length;
        ByteUtil.ByteBuilder builder = new ByteUtil.ByteBuilder(bytes.length);
        byte[] initBlock = new byte[length];
        random.nextBytes(initBlock);
        byte[] output = cipher.encrypt(new Block(initBlock, initBlock.length));
        byte[] cipherByte = ByteUtil.xor(output, blocks[0].getBuffer());
        builder.append(cipherByte);

        for (int i = 1; i < blockLength; i++) {
            output = cipher.encrypt(new Block(output, output.length));
            cipherByte = ByteUtil.xor(output, blocks[i].getBuffer());
            builder.append(cipherByte);
        }
        return builder.getBytes();
    }
}
