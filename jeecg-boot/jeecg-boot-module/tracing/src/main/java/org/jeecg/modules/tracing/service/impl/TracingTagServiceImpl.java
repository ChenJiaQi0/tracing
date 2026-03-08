package org.jeecg.modules.tracing.service.impl;

import org.jeecg.modules.tracing.entity.TracingTag;
import org.jeecg.modules.tracing.mapper.TracingTagMapper;
import org.jeecg.modules.tracing.service.ITracingTagService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 寻迹-标签分类表
 * @Author: jeecg-boot
 * @Date:   2026-02-16
 * @Version: V1.0
 */
@Service
public class TracingTagServiceImpl extends ServiceImpl<TracingTagMapper, TracingTag> implements ITracingTagService {

}
