package net.jakush.databaseapi.interfaces;

import net.jakush.databaseapi.databasetype.mysql.Condition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Snapshot of condition for commands
 */
public interface SnapshotCondition {

    /**
     * Adds condition to builder
     * @param columnLabel label
     * @param value value
     * @return condition (to make value-chain possible)
     */
    Condition addCondition(final @NotNull String columnLabel, final @NotNull String value);
    /**
     * Adds condition to builder
     * @param columnLabel label
     * @param operator operator is '=' and more, may be null (more operators on this <a href="https://www.w3schools.com/mysql/mysql_operators.asp">page</a>)
     * @param equalsTo equals to value
     * @return condition (to make value-chain possible)
     */
    Condition addCondition(final @NotNull String columnLabel, final @Nullable String operator, final @NotNull String equalsTo);

    String toString();

    @Contract("_, _ -> new")
    static @NotNull Condition getInstance(final @NotNull String columnLabel, final @NotNull String equalsTo) {
        return new Condition(columnLabel, null, equalsTo);
    }

    @Contract("_, _, _ -> new")
    static @NotNull Condition getInstance(final @NotNull String columnLabel, final @Nullable String operator, final @NotNull String equalsTo) {
        return new Condition(columnLabel, operator, equalsTo);
    }
}
