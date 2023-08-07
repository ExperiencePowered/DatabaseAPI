package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.interfaces.commandtypes.SelectCommand;
import net.jakush.databaseapi.interfaces.query.Query;
import net.jakush.databaseapi.interfaces.query.impl.QueryImpl;
import net.jakush.databaseapi.serializers.DatabasePropertySerializer;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link SelectCommand}
 */
public class SelectCommandImpl implements SelectCommand {

    private final Query query;
    private String table;
    private String properties;
    private SnapshotCondition where;

    public SelectCommandImpl() {
        this.query = new QueryImpl();
    }

    @Override
    public String getTable() {
        Objects.requireNonNull(table, "Table name was null when accessing it.");
        return table;
    }

    @Override
    public SelectCommand setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public SelectCommand setProperties(final @NotNull List<DatabaseProperty> tableProperties) {
        this.properties = DatabasePropertySerializer.deserializeToColumns(tableProperties);
        return this;
    }

    @Override
    public SelectCommand setCondition(final @NotNull SnapshotCondition condition) {
        this.where = condition;
        return this;
    }

    @Override
    public Query getQuery() {
        return query;
    }

    @Override
    public String toString() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.SELECT)
                .setWhich(properties)
                .setTable("FROM", table, false)
                .setCondition(where);

        return commandBuilder.toString();
    }
}
