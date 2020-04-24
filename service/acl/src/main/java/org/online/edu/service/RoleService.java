package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 镜子白
 * @since 2020-01-12
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户获取角色数据
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 根据用户分配角色
     */
    void saveUserRoleRelationShip(String userId, String[] roleId);

    List<Role> selectRoleByUserId(String id);
}
