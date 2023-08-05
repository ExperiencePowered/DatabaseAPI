package net.jakush.databaseapi.utils;

import net.jakush.databaseapi.enums.CommandType;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.serializers.DatabasePropertySerializer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
                .replace("_", " "));
        return this;
    }

    public DatabaseCommandBuilder setBase(final @NotNull String base) {
        builder.append(base);
        return this;
    }

    public DatabaseCommandBuilder setTable(final @NotNull String type, final @NotNull String table, final @NotNull List<DatabaseProperty> properties) {
        builder.append(type).append(" ").append(table);
        builder.append(" ").append(DatabasePropertySerializer.deserialize(properties, true));
        return this;
    }

    public DatabaseCommandBuilder setTable(final @NotNull String type, final @NotNull String table, final boolean properties, final boolean braces) {
        builder.append(type).append(" ").append(table);
        if (properties) builder.append(" ").append(DatabasePropertySerializer.deserialize(Database.getProperties(table), braces));
        return this;
    }

    public DatabaseCommandBuilder setValues(final @NotNull String values) {
        builder.append(" ").append(values);
        return this;
    }

    public DatabaseCommandBuilder setCondition(final @NotNull SnapshotCondition condition) {
        builder.append("WHERE ").append(condition);
        return this;
    }

    public DatabaseCommandBuilder setWhich(final @NotNull String which) {
        builder.append(which);
        return this;
    }

    public DatabaseCommandBuilder setUpdate(final @NotNull SnapshotCondition updateSet) {
        builder.append("SET ").append(updateSet);
        return this;
    }

    @Override
    public String toString() {
        return builder.append(";").toString();
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull DatabaseCommandBuilder getInstance() {
        return new DatabaseCommandBuilder();
    }
}
