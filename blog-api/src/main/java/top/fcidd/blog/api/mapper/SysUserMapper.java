package top.fcidd.blog.api.mapper;

import org.apache.ibatis.annotations.Param;
import top.fcidd.blog.api.entity.SysUser;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据用户名查询用户
     */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * 根据uuid查询用户
     */
    SysUser selectByUuid(@Param("uuid") String uuid);

    /**
     * 根据激活token查询用户
     */
    SysUser selectByActivateToken(@Param("activateToken") String activateToken);

    /**
     *
     */
    SysUser selectByEmail(@Param("email") String email);

    /**
     * 新增用户
     */
    int insert(SysUser user);

    /**
     * 修改邮箱激活状态
     */
    int updateActivated(@Param("uuid") String uuid, @Param("activated") Boolean activated);

    /**
     * 修改账户启用状态（管理员启停账户）
     */
    int updateEnabled(@Param("uuid") String uuid, @Param("enabled") Boolean enabled);

    /**
     * 更新激活token+过期时间（重发激活邮件）
     */
    int updateActivateToken(@Param("uuid") String uuid,
                            @Param("activateToken") String activateToken,
                            @Param("tokenExpireTime") java.time.LocalDateTime tokenExpireTime);

    /**
     * 用户列表
     */
    List<SysUser> selectList();
}