package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.commandtypes.InsertCommand;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link InsertCommand}
 */
public class InsertCommandImpl implements InsertCommand {

    private String table;
    private String values;
    private List<DatabaseProperty> tableProperties;

    @Override
    public String getTable() {
        Objects.requireNonNull(table, "Table name was null when accessing it.");
        return table;
    }

    @Override
    public InsertCommand setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public InsertCommand setProperties(final @NotNull List<DatabaseProperty> tableProperties) {
        this.tableProperties = tableProperties;
        return this;
    }

    @Override
    public InsertCommand setValues(final @NotNull List<String> valueList) {
        final StringBuilder valueString = new StringBuilder();
        final int expectedSize = valueList.size();
        valueString.append("(");
        IntStream.range(0, expectedSize).forEach(i -> {
            valueString.append("'").append(valueList.get(i)).append("'");
            if (i != expectedSize - 1) {
                valueString.append(", ");
            }
        });
        valueString.append(")");
        values = valueString.toString();
        return this;
    }

    @Override
    public String toString() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.INSERT_INTO)
                .setTable(null, table, true, tableProperties)
                .setValues("VALUES " + values);

        return commandBuilder.toString();
    }
}
