package org.online.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.online.edu.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author 镜子白
 * @since 2020-01-12
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
