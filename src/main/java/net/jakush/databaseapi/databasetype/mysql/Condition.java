package net.jakush.databaseapi.databasetype.mysql;

import net.jakush.databaseapi.interfaces.SnapshotCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Condition class which creates Strings as <br>
 * WHERE player = 'Jakush' <br>
 * or Strings as <br>
 * WHERE coins > '50'
 */
public class Condition implements SnapshotCondition {

    private final StringBuilder builder;

    public Condition(final @NotNull ConditionType type, final @NotNull String columnLabel, final @Nullable String operator, final @NotNull String equalsTo) {
        this.builder = new StringBuilder(type.name() + " ");
        addCondition(builder, columnLabel, operator, equalsTo);
    }

    @Override
    public Condition addCondition(final @NotNull String columnLabel, final @NotNull String value) {
        return addCondition(columnLabel, null, value);
    }

    @Override
    public Condition addCondition(final @NotNull String columnLabel, final @Nullable String operator, final @NotNull String equalsTo) {
        builder.append(", ");
        addCondition(builder, columnLabel, operator, equalsTo);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    private static void addCondition(final @NotNull StringBuilder builder, final @NotNull String columnLabel, final @Nullable String operator, final @NotNull String equalsTo) {
        final String finalOperator = operator == null ? "=" : operator;
        builder.append(columnLabel).append(" ").append(finalOperator).append(" '").append(equalsTo).append("'");
    }
}
