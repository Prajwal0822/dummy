package com.gvnc.sample.web.util;

import java.sql.Date;

public class DatabaseUtil {

    public static Date getNow(){
        return new Date(System.currentTimeMillis());
    }
}
