package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.enums.CommandType;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.interfaces.commandtypes.SelectCommand;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.interfaces.commandtypes.UpdateCommand;
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
 * Impl for {@link SelectCommand.Builder}
 */
public class SelectCommandImpl implements SelectCommand.Builder {

    private String table;
    private String which;
    private SnapshotCondition where;

    @Override
    public SnapshotCommand.Builder setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public SelectCommand.Builder setWhich(final @NotNull List<DatabaseProperty> tableProperties) {
        this.which = DatabasePropertySerializer.deserialize(tableProperties, false);
        return this;
    }

    @Override
    public SelectCommand.Builder setCondition(final @NotNull SnapshotCondition condition) {
        this.where = condition;
        return this;
    }

    @Override
    public SnapshotCommand build() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.SELECT)
                .setWhich(which)
                .setTable("FROM", table, false, false)
                .setCondition(where);

        return new SelectCommand() {
            @Override
            public Query getQuery() {
                return new QueryImpl();
            }

            @Override
            public String getCommand() {
                return commandBuilder.toString();
            }

            @Override
            public String getTable() {
                return table;
            }
        };
    }
}
