package com.helon.oauth2.stat;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DruidStatController
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/4/28 3:53 PM
 * version: v1.0
 */
@RestController
public class DruidStatController {

    @GetMapping("/druid/stat")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList
        // 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，
        // 你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}