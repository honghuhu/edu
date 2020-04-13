package org.online.edu.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.online.edu.entity.Banner;
import org.online.edu.mapper.BannerMapper;
import org.online.edu.service.BannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-10
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    @Cacheable(value = "banner", key = "'allBanner'")
    public List<Banner> all() {
        return new LambdaQueryChainWrapper<>(baseMapper).orderByDesc(Banner::getGmtCreate).last("limit 3").list();
    }
}
