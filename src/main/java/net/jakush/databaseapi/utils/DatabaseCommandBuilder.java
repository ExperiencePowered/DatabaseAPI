package net.jakush.databaseapi.utils;

import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.serializers.DatabasePropertySerializer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Util which is used to be builder to build command <br>
 * Shouldn't be used in public API as this is order-sensitive
 */
public class DatabaseCommandBuilder {

    private final StringBuilder builder;

    public DatabaseCommandBuilder() {
        this.builder = new StringBuilder();
    }

    public DatabaseCommandBuilder setBase(final @NotNull CommandType type) {
        builder.append(type.name()
                .replace("_", " "))
                .append(" ");
        return this;
    }

    public DatabaseCommandBuilder setBase(final @NotNull String base) {
        builder.append(base).append(" ");
        return this;
    }

    public DatabaseCommandBuilder setTable(final @Nullable String type, final @NotNull String table, final @NotNull List<DatabaseProperty> propertyList) {
        if (type != null && !type.isEmpty()) builder.append(" ").append(type);
        builder.append(" ").append(table);
        builder.append(" ").append(DatabasePropertySerializer.deserialize(propertyList, true));
        return this;
    }

    public DatabaseCommandBuilder setTable(final @Nullable String type, final @NotNull String table, final boolean properties) {
        if (type != null && !type.isEmpty()) builder.append(" ").append(type);
        builder.append(" ").append(table);
        if (properties) builder.append(" ").append(DatabasePropertySerializer.deserializeToColumns(Database.getProperties(table)));
        return this;
    }

    public DatabaseCommandBuilder setTable(final @Nullable String type, final @NotNull String table, final boolean properties, final @Nullable List<DatabaseProperty> propertyList) {
        final var finalPropertyList = propertyList == null ? Database.getProperties(table) : propertyList;
        if (type != null && !type.isEmpty()) builder.append(" ").append(type);
        builder.append(" ").append(table);
        if (properties) builder.append(" ").append(DatabasePropertySerializer.deserializeToColumnsWithBraces(finalPropertyList));
        return this;
    }


    public DatabaseCommandBuilder setValues(final @NotNull String values) {
        builder.append(" ").append(values);
        return this;
    }

    public DatabaseCommandBuilder setCondition(final @NotNull SnapshotCondition condition) {
        builder.append(" ").append(condition);
        return this;
    }

    public DatabaseCommandBuilder setWhich(final @NotNull String which) {
        builder.append(which);
        return this;
    }

    public DatabaseCommandBuilder setUpdateSet(final @NotNull SnapshotCondition updateSet) {
        builder.append(" ").append(updateSet);
        return this;
    }

    @Override
    public String toString() {
        String result = builder.append(";")
                .toString();
        result = result.trim()
                .replaceAll("( +)", " ");

        return result;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull DatabaseCommandBuilder getInstance() {
        return new DatabaseCommandBuilder();
    }
}
