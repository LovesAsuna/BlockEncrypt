package com.hyosakura.study.mode;

import com.hyosakura.study.cipher.Cipher;
import com.hyosakura.study.util.Block;
import com.hyosakura.study.util.ByteUtil;

/**
 * @author LovesAsuna
 **/
public class CTR extends CipherMode {
    /**
     * @param cipher 加密方法
     * @param length 分块长度(以字节为单位)
     */
    public CTR(Cipher cipher, int length) {
        super(cipher, length);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        Block[] blocks = getBlocks(bytes);
        int bufferLength = blocks[0].getBuffer().length;
        ByteUtil.ByteBuilder builder = new ByteUtil.ByteBuilder(bytes.length);
        int nonceLength = bufferLength / 2;
        byte[] nonce = new byte[nonceLength];
        int counterLength = bufferLength - nonceLength;
        byte[] counter = new byte[counterLength];
        random.nextBytes(nonce);
        byte[] output;
        System.arraycopy(nonce, 0, counter, 0, nonceLength);
        for (Block block : blocks) {
            output = cipher.encrypt(new Block(counter, counter.length));
            builder.append(ByteUtil.xor(output, block.getBuffer()));
            increaseCounter(counter, counterLength);
        }
        return builder.getBytes();
    }

    public void increaseCounter(byte[] counter, int counterLength) {
        // 从最低位开始进位
        if (Byte.toUnsignedInt(counter[counter.length - 1]) == 0b11111111) {
            counter[counter.length - 1] = 0;
            for (int i = counter.length - 2; i > counter.length - counterLength - 1; i--) {
                if (Byte.toUnsignedInt(counter[i]) == 0b11111111) {
                    counter[i] = 0;
                } else {
                    counter[i] += 1;
                    break;
                }
            }
        } else {
            counter[counter.length - 1] += 1;
        }
    }
}
