package dev.service.cloud;

import dev.service.cloud.global.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }
}