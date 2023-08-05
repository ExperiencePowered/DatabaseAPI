package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.interfaces.commandtypes.InsertCommand;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link InsertCommand.Builder}
 */
public class InsertCommandImpl implements InsertCommand.Builder {

    private String table;
    private String values;

    @Override
    public InsertCommand.Builder setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public InsertCommand.Builder setValues(final @NotNull List<String> valueList) {
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
    public SnapshotCommand build() {
        Objects.requireNonNull(table, "Table was not set!");

        final String base = "INSERT INTO ";
        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(base)
                .setTable("", table, true, true)
                .setValues(values);
        return new InsertCommand() {
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
