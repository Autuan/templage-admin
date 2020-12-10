package com.autuan.common.utils.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/28 14:03
 * @company : 上海奥若拉信息科技集团有限公司
 */
public class LocalDateTimeUtil {
    public static final LocalDateTime of(Date date) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDateTime;
    }

    public static final LocalDateTime sub(String dateStr) {
        return null;
    }
}
