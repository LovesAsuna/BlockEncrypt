package com.hyosakura.study.cipher;

import com.hyosakura.study.util.Block;

/**
 * @author LovesAsuna
 **/
public interface Cipher {
    byte[] encrypt(Block block);
}
