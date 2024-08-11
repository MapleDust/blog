package xyz.fcidd.blog.service;

public interface ToolsService {
    /**
     * 将从前端传来的文本进行Base64加密处理
     *
     * @param text 文本
     * @return 加密后的文本
     */
    String base64Encryption(String text);

    /**
     * 将从前端传来的Base64编码进行解密处理
     *
     * @param base64 Base64编码
     * @return 解密后的文本
     */
    String base64Decryption(String base64);
}
