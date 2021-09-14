package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;
import com.hyosakura.study.util.ByteUtil;

import java.util.Arrays;

/**
 * @author LovesAsuna
 **/
public class CFB extends CipherMode {
    public CFB(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        Block[] blocks = getBlocks(bytes);
        int blockLength = blocks.length;
        int discardLength = length / 2;
        ByteUtil.ByteBuilder builder = new ByteUtil.ByteBuilder(bytes.length);
        byte[] initBlock = new byte[length + discardLength];
        random.nextBytes(initBlock);
        byte[] output = cipher.encrypt(new Block(initBlock, initBlock.length));

        byte[] select = Arrays.copyOfRange(output, 0, length);
        byte[] discard = Arrays.copyOfRange(output, length, length + discardLength);
        byte[] xorByte = ByteUtil.xor(blocks[0].getBuffer(), select);
        builder.append(xorByte);

        for (int i = 1; i < blockLength; i++) {
            System.arraycopy(discard, 0, initBlock, 0, discardLength);
            System.arraycopy(select, 0, initBlock, discardLength, length);
            output = cipher.encrypt(new Block(initBlock, initBlock.length));
            select = Arrays.copyOfRange(output, 0, length);
            discard = Arrays.copyOfRange(output, length, length + discardLength);
            builder.append(ByteUtil.xor(blocks[i].getBuffer(), select));
        }

        return builder.getBytes();
    }
}
