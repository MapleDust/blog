package xyz.fcidd.blog.service.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import xyz.fcidd.blog.exception.Base64NullTextException;
import xyz.fcidd.blog.exception.ErrorBase64Exception;
import xyz.fcidd.blog.exception.NullBase64Exception;
import xyz.fcidd.blog.service.Base64Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class Base64ServiceImpl implements Base64Service {
    /**
     * 进行Base64加密
     *
     * @param text 文本
     * @return 加密过后的文本
     */
    @SneakyThrows
    @Override
    public String base64Encryption(String text) {
        //检查用户输入的文本是否为空
        if (text == null || text.isEmpty()) {
            throw new Base64NullTextException("请输入需要加密为Base64的文本");
        }
        //返回加密过后的Base64编码
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 进行Base64解密
     *
     * @param base64 Base64编码
     * @return 经过解密的文本
     */
    @SneakyThrows
    @Override
    public String base64Decryption(String base64) {
        //检查用户输入Base64是否为空
        if (base64 == null || base64.isEmpty()) {
            throw new NullBase64Exception("请输入需要解密的Base64编码");
        }
        //去除用户输入base64的前后空格
        String base64Trim = base64.trim();
        //加号传递过来变成空格，需要将空格替换成加号
        String base64replace = base64Trim.replaceAll(" +", "+");
        //检查是否为Base64编码
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        boolean isBase64 = Pattern.matches(base64Pattern, base64replace);
        if (!isBase64) {
            throw new ErrorBase64Exception("请输入有效的Base64编码");
        }
        //将解码的文本发返回给前端
        byte[] decodeByte = Base64.getDecoder().decode(base64replace.getBytes());
        return new String(decodeByte, StandardCharsets.UTF_8);
    }
}
