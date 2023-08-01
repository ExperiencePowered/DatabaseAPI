package net.jakush.databaseapi.impl;

import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseCredentials;
import net.jakush.databaseapi.utils.HikariSetupUtil;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link Database} abstract class<br>
 * If you want to get database instance of MySQL, use {@link DatabaseType#MySQL}
 */
public final class MySQL extends Database {

    public MySQL(final @NotNull DatabaseCredentials data) {
        HikariSetupUtil.setupHikariData(hikari, data);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
        hikari.setJdbcUrl("jdbc:mysql://" + data.getHost() + ":" + data.getPort() + "/" + data.getDatabase() + "?useSSL=false");
        if (data.getPassword().isPresent()) hikari.setPassword(data.getPassword().get());
        if (data.getUsername().isPresent()) hikari.setUsername(data.getUsername().get());
    }
}