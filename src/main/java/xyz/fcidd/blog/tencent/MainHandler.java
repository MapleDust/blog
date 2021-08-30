package xyz.fcidd.blog.tencent;

import com.qcloud.scf.runtime.AbstractSpringHandler;
import xyz.fcidd.blog.BlogApplication;

/**
 * 部署到serverless
 */
public class MainHandler extends AbstractSpringHandler {

    @Override
    public void startApp() {
        BlogApplication.main(new String[]{""});
    }
}
