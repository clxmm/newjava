package org.clxmm.service.cms.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.cms.entity.Ad;
import org.clxmm.service.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/30 9:23 下午
 */
@CrossOrigin //解决跨域问题
@Api(description = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
public class ApiController {


    @Autowired
    private AdService adService;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位id", required = true) @PathVariable String adTypeId) {
        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items", ads);
    }



    @PostMapping("save-test")
    public R saveAd(@RequestBody Ad ad) {
//        redisTemplate.opsForValue().set("ad1",ad);
        redisTemplate.opsForValue().set("index::ad", ad);

        return R.ok().data("ad",ad);
    }


    @GetMapping("get-test/{key}")
    public R getAd(@PathVariable String key) {
        Ad ad = (Ad)redisTemplate.opsForValue().get(key);
        return R.ok().data("ad",ad);
    }

    @DeleteMapping("remove-test/{key}")
    public R removeAd(@PathVariable String key){
        Boolean delete = redisTemplate.delete(key);
        System.out.println(delete);//是否删除成功
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println(hasKey);//key是否存在
        return R.ok();
    }









}
