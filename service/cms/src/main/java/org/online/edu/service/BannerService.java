package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.Banner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-10
 */
public interface BannerService extends IService<Banner> {

    List<Banner> all();

}
