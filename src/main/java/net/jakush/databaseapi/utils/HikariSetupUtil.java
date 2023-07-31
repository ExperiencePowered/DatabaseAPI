package net.jakush.databaseapi.utils;

import com.zaxxer.hikari.HikariDataSource;
import net.jakush.databaseapi.interfaces.DatabaseData;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Util which is used to properly setup hikari's data source property
 */
public class HikariSetupUtil {

    /**
     * Method to set up data source property for hikari
     * @param hikari Hikari data source on which will be properties applied
     * @param data Database data which contains all important information like password, host
     */
    public static void setupHikariData(final @NotNull HikariDataSource hikari, final @NotNull DatabaseData data) {
        hikari.addDataSourceProperty("serverName", data.getHost());
        hikari.addDataSourceProperty("port", data.getPort());
        hikari.addDataSourceProperty("databaseName", data.getDatabase());
        hikari.addDataSourceProperty("maximumPoolSize", 4);
        if (data.getUsername().isPresent()) hikari.addDataSourceProperty("user", data.getUsername().get());
        if (data.getPassword().isPresent()) hikari.addDataSourceProperty("password", data.getPassword().get());
    }
}
