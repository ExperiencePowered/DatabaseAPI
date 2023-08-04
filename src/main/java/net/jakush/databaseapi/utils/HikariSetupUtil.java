package net.jakush.databaseapi.utils;

import com.zaxxer.hikari.HikariDataSource;
import net.jakush.databaseapi.interfaces.DatabaseCredentials;
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
     * @param hikari Hikari credentials source on which will be properties applied
     * @param credentials Database credentials which contain all important information like password, host
     */
    public static void setupHikariData(final @NotNull HikariDataSource hikari, final @NotNull DatabaseCredentials credentials) {
        hikari.addDataSourceProperty("serverName", credentials.getHost());
        hikari.addDataSourceProperty("port", credentials.getPort());
        hikari.addDataSourceProperty("databaseName", credentials.getDatabase());
        hikari.addDataSourceProperty("maximumPoolSize", 4);
        if (credentials.getUsername().isPresent()) hikari.addDataSourceProperty("user", credentials.getUsername().get());
        if (credentials.getPassword().isPresent()) hikari.addDataSourceProperty("password", credentials.getPassword().get());
    }
}
