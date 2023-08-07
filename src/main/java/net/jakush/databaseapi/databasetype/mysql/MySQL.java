package net.jakush.databaseapi.databasetype.mysql;

import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.enums.TableFlags;
import net.jakush.databaseapi.exceptions.QueryInitializationException;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseCredentials;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.commandtypes.QueryCommand;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.interfaces.query.Query;
import net.jakush.databaseapi.interfaces.query.QueryMetaData;
import net.jakush.databaseapi.interfaces.query.impl.QueryImpl;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import net.jakush.databaseapi.utils.HikariSetupUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link Database} abstract class<br>
 * If you want to get database instance of MySQL, use {@link DatabaseType#MySQL}
 */
public final class MySQL extends Database {

    public MySQL(final @NotNull DatabaseCredentials credentials) {
        HikariSetupUtil.setupHikariData(hikari, credentials);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
        hikari.setJdbcUrl("jdbc:mysql://" + credentials.getHost() + ":" + credentials.getPort() + "/" + credentials.getDatabase() + "?useSSL=false");
        if (credentials.getPassword().isPresent()) hikari.setPassword(credentials.getPassword().get());
        if (credentials.getUsername().isPresent()) hikari.setUsername(credentials.getUsername().get());
    }

    @Override
    public boolean createTable(final @NotNull String table, final @NotNull List<TableFlags> flags, final @NotNull List<DatabaseProperty> propertyList) {
        StringBuilder flagString = new StringBuilder();
        flags.forEach(flag -> flagString
                .append(flag
                        .name()
                        .replace("_", " ")
                ));

        Database.propertyList.put(table, propertyList);
        DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase("CREATE TABLE " + flagString)
                .setTable(null, table, propertyList);
        SnapshotCommand createTable = new SnapshotCommand() {
            @Contract(pure = true)
            @Override
            public @NotNull String toString() {
                return commandBuilder.toString();
            }

            @Override
            public @NotNull String getTable() {
                return table;
            }
        };
        return runStatement(createTable);
    }

    @Contract(pure = true)
    @Override
    public SnapshotCommand createStatement(final @NotNull CommandType type, final @NotNull Consumer<SnapshotCommand> consumer) {
        final Constructor<? extends SnapshotCommand> constructor;
        final SnapshotCommand command;
        try {
            constructor = type.getClazz().getDeclaredConstructor();
            command = constructor.newInstance();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        consumer.accept(command);
        return command;
    }

    @Override
    public boolean runStatement(final @NotNull SnapshotCommand statement) {
        if (statement instanceof QueryCommand) {
            throw new QueryInitializationException("Cannot execute query on `runStatement` method, use runQuery instead.");
        }
        try (final Connection connection = hikari.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(statement.toString())) {
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean runQuery(final @NotNull QueryCommand statement, final @NotNull Consumer<Query> consumer) {
        try (final Connection connection = hikari.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(statement.toString())) {
            final ResultSet rs = preparedStatement.executeQuery();
            final Query query = statement.getQuery();
            final QueryMetaData meta = query.getMetaData();

            while (rs.next()) {
                final Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String columnName = meta.getColumnName(i);
                    Object value = query.getObject(i);

                    row.put(columnName, value);
                }
                ((QueryImpl) query).addRow(row);
            }

            consumer.accept(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}