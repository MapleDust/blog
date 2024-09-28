package xyz.fcidd.blog.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fcidd.blog.dto.R;
import xyz.fcidd.blog.service.Base64Service;


@RestController
public class ToolsController {
    @Resource
    private Base64Service base64Service;

    /**
     * 将文本加密成Base64编码
     *
     * @param text 文本
     * @return 加密过后的文本
     */
    @RequestMapping("/base64Encryption")
    public R<?> base64Encryption(String text) {
        String base64 = base64Service.base64Encryption(text);
        return R.ok(base64);
    }

    /**
     * 将Base64解密为文本
     *
     * @param base64 Base64编码
     * @return 解密过后的文本
     */
    @RequestMapping("/base64Decryption")
    public R<?> base64Decryption(String base64) {
        String text = base64Service.base64Decryption(base64);
        return R.ok(text);
    }
}
