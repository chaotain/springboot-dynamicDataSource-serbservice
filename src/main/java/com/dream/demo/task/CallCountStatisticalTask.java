package com.dream.demo.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口调用次数统计
 */
@Component
public class CallCountStatisticalTask {

    @Autowired
    ConcurrentHashMap countMap;

    @Value("${file.path}")
    private String filePath;

    @Scheduled(cron = "${task.cron}")
    public void test() {
        File file = new File(filePath + File.separator  + DateUtil.format(DateUtil.date(),"yyyy-MM-dd") + "apiCount.txt");
        FileUtil.writeUtf8Map(countMap, file, null, true);
        countMap.clear();
    }
}
