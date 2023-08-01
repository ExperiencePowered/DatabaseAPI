package net.jakush.databaseapi.interfaces;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for providing all data which will be used later for connection
 */
public interface DatabaseCredentials {

    /**
     * Gets host name
     * @return host name
     */
    String getHost();

    /**
     * Gets port
     * @return port
     */
    int getPort();

    /**
     * Gets database name
     * @return database name
     */
    String getDatabase();

    /**
     * Gets username, may be null, so it's recommended to always check if value is present via {@link Optional#isPresent()} before accessing value
     * @return optional of username
     */
    Optional<String> getUsername();
    /**
     * Gets password, may be null, so it's recommended to always check if value is present via {@link Optional#isPresent()} before accessing value
     * @return optional of password
     */
    Optional<String> getPassword();

    /**
     * Method for creating instance without having to create messy code, important to say, values which are put in method won't be able to be changed as its final and implemented
     * @param host host name
     * @param port port name
     * @param database database name
     * @param username username (may be null)
     * @param password password (may be null)
     * @return new instance of {@link DatabaseCredentials}
     */
    @Contract(value = "_, _, _, _, _ -> new", pure = true)
    static @NotNull DatabaseCredentials getInstance(final @NotNull String host, final int port, final @NotNull String database, final @Nullable String username, final @Nullable String password) {
        return new DatabaseCredentials() {
            @Override
            public String getHost() {
                return host;
            }

            @Override
            public int getPort() {
                return 3306;
            }

            @Override
            public String getDatabase() {
                return database;
            }

            @Override
            public Optional<String> getUsername() {
                return Optional.ofNullable(username);
            }

            @Override
            public Optional<String> getPassword() {
                return Optional.ofNullable(password);
            }
        };
    }

    class Builder {

        private String host;
        private Integer port;
        private String database;
        private String username;
        private String password;

        // Setters

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setDatabase(String database) {
            this.database = database;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DatabaseCredentials buildCredentials() {
            if (host == null || port == null || database == null) {
                throw new NullPointerException("Couldn't build credentials as host, port or database is null.");
            }
            return DatabaseCredentials.getInstance(host, port, database, username, password);
        }

        /**
         * Makes new instance of {@link DatabaseCredentials.Builder}
         * @return builder
         */
        @Contract(value = " -> new", pure = true)
        public static @NotNull Builder getInstance() {
            return new Builder();
        }
    }
}
