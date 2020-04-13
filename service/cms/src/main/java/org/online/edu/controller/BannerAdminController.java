package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Banner;
import org.online.edu.entity.dto.BannerDto;
import org.online.edu.entity.vo.BannerVo;
import org.online.edu.service.BannerService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("banner-admin")
public class BannerAdminController {

    private BannerService bannerService;

    @GetMapping("{page}/{limit}")
    public R<IPage<BannerDto>> page(@PathVariable Integer page, @PathVariable Integer limit) {
        Page bannerPage = new LambdaQueryChainWrapper<>(bannerService.getBaseMapper()).page(new Page<>(page, limit));
        bannerPage.setRecords((List) bannerPage.getRecords().stream().map(banner -> BeanUtil.toBean(banner, BannerDto.class)).collect(Collectors.toList()));
        return R.ok(bannerPage);
    }

    @PostMapping("save")
    public R save(@RequestBody BannerVo bannerVo) {
        return R.ok(bannerService.save(BeanUtil.toBean(bannerVo, Banner.class)));
    }

    @PutMapping("modify/{id}")
    public R modify(@PathVariable String id, @RequestBody BannerVo bannerVo) {
        Banner banner = BeanUtil.toBean(bannerVo, Banner.class);
        banner.setId(id);
        return R.ok(bannerService.updateById(banner));
    }

    @DeleteMapping("{id}")
    public R remove(@PathVariable String id) {
        return R.ok(bannerService.removeById(id));
    }
}

