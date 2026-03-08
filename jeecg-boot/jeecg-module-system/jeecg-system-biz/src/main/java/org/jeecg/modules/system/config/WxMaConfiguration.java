package org.jeecg.modules.system.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WxMaConfiguration {
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.secret}")
    private String secret;

    private static WxMaService maService;

    @Autowired
    private RedisUtil redisUtil;

    public static WxMaService getMaService() {
        if (maService == null) {
            throw new IllegalArgumentException(String.format("未找到对应微信的配置，请核实！"));
        }

        return maService;
    }

    @PostConstruct
    public void init() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appId);
        redisUtil.set("WX.APPID", appId);
        config.setSecret(secret);
        redisUtil.set("WX.SECRET", secret);

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        maService =  service;
    }
}
