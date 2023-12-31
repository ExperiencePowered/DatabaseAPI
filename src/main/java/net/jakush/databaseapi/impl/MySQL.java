package net.jakush.databaseapi.impl;

import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.enums.TableFlags;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseCredentials;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.utils.HikariSetupUtil;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

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

    @Override
    public boolean createTable(final String table, final @NotNull List<TableFlags> flags, final @NotNull List<DatabaseProperty> properties) {
        StringBuilder flagString = new StringBuilder();
        flags.forEach(flag -> flagString
                .append(flag
                        .name()
                        .replace("_", " ")
                )
                .append(" "));

        StringBuilder propertiesString = new StringBuilder();
        int expectedSize = properties.size();

        propertiesString.append(" (");
        IntStream.range(0, expectedSize).forEach(i -> {
            DatabaseProperty property = properties.get(i);
            propertiesString
                    .append(property.name())
                    .append(" ")
                    .append(property
                            .type()
                            .name()
                            .replace("_", "")
                    );
                    if (property.type().getSize() != -1) propertiesString
                            .append("(")
                            .append(property
                                    .type()
                                    .getSize())
                            .append(")");
            if (i != expectedSize - 1) {
                propertiesString
                        .append(", ");
            }
        });
        propertiesString.append(")");

        String create = "CREATE TABLE " + flagString + table + propertiesString;
        Bukkit.getLogger().info(create);
        return true;
    }
}