package top.fcidd.blog.api.Service;

import top.fcidd.blog.api.dto.LoginDTO;
import top.fcidd.blog.api.dto.RegisterDTO;
import top.fcidd.blog.api.entity.SysUser;
import top.fcidd.blog.api.vo.TokenVO;

import java.util.List;

public interface UserService {

    /**
     * 注册用户 + 生成激活token + 发送邮箱激活链接
     */
    void registerAndSendEmail(RegisterDTO dto);

    /**
     * 邮箱激活
     */
    void activateUser(String token);

    /**
     * 管理员修改账户启用状态
     */
    void changeUserEnabled(String userUuid, Boolean enabled);

    /**
     * 根据用户名查询
     */
    SysUser getByUsername(String username);

    /**
     * 用户列表
     */
    List<SysUser> getUserList();
    /**
     * 账号密码登录
     */
    TokenVO login(LoginDTO loginDTO);

    /**
     * 账号登出
     */
    void logout(String token);
}