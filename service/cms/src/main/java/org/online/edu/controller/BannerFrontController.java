package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Banner;
import org.online.edu.entity.dto.BannerDto;
import org.online.edu.service.BannerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-10
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("banner-front")
public class BannerFrontController {

    private BannerService bannerService;

    @GetMapping("all")
    public R all() {
        List<Banner> allBanner = bannerService.all();
        return R.ok(allBanner.stream().map(banner -> BeanUtil.toBean(banner, BannerDto.class)).collect(Collectors.toList()));
    }
}

