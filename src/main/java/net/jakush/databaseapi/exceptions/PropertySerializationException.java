package net.jakush.databaseapi.exceptions;

import net.jakush.databaseapi.serializers.DatabasePropertySerializer;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Exception which should be only thrown in class {@link DatabasePropertySerializer}
 */
public class PropertySerializationException extends IllegalStateException {
    public PropertySerializationException(final String s) {
        super(s);
    }
}
