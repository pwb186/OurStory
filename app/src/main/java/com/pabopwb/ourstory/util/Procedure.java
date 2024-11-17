package com.pabopwb.ourstory.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Procedure {
    public static String getTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
        return now.format(formatter);
    }
}
