package net.jakush.databaseapi;

import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseData;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Class for providing database instance
 */
public final class DatabaseAPI {

    public static Database loadInstance(final @Nullable JavaPlugin plugin, final @NotNull DatabaseType type, final @NotNull DatabaseData data) {
        final Class<? extends Database> clazz = type.getClazz();
        Constructor<?> constructor;
        Database database;
        try {
            constructor = clazz.getDeclaredConstructor(DatabaseData.class);
            database = (Database) constructor.newInstance(data);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (plugin != null) {
            final ServicesManager manager = plugin.getServer().getServicesManager();
            manager.register(Database.class, database, plugin, ServicePriority.Normal);
        }
        return database;
    }

}
