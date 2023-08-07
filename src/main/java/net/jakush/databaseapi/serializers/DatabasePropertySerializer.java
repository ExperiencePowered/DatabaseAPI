package net.jakush.databaseapi.serializers;

import net.jakush.databaseapi.enums.DataType;
import net.jakush.databaseapi.exceptions.PropertySerializationException;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Serializer (& deserializer) class for list of {@link DatabaseProperty}
 */
public class DatabasePropertySerializer {

    /**
     * Converts a property list to String (just columns)
     * @param propertyList list with properties
     * @return deserialized string
     */
    public static @NotNull String deserializeToColumns(final @NotNull List<DatabaseProperty> propertyList) {
        final StringBuilder builder = new StringBuilder();
        final int expectedSize = propertyList.size();

        IntStream.range(0, expectedSize).forEach(i -> {
            final DatabaseProperty property = propertyList.get(i);
            builder.append(property.name())
                    .append(" ");
        });

        return builder.toString();
    }

    /**
     * Converts a property list to String
     * @param propertyList list with properties
     * @param braces if on start and on end should be braces - ()
     * @return deserialized string
     */
    public static @NotNull String deserialize(final @NotNull List<DatabaseProperty> propertyList, final boolean braces) {
        return deserialize(propertyList, braces, false);
    }

    /**
     * Converts a property list to String
     * @param propertyList list with properties
     * @param braces if on start and on end should be braces - ()
     * @param addSize when adding, for example - CHAR, addSize=true will add to that its size, so it will be like this - CHAR(255)
     * @return deserialized string
     */
    public static @NotNull String deserialize(final @NotNull List<DatabaseProperty> propertyList, final boolean braces, final boolean addSize) {
        final StringBuilder builder = new StringBuilder();
        final int expectedSize = propertyList.size();

        if (braces) builder.append("(");
        IntStream.range(0, expectedSize).forEach(i -> {
            final DatabaseProperty property = propertyList.get(i);
            builder.append(property.name())
                    .append(" ")
                    .append(property
                            .type()
                            .name()
                            .replace("_", "")
                    );
            if (addSize && property.type().getSize() != -1) builder.append("(")
                    .append(property
                            .type()
                            .getSize())
                    .append(")");
            if (i != expectedSize - 1) {
                builder.append(", ");
            }
        });
        if (braces) builder.append(")");

        return builder.toString();
    }

    /**
     * Converts a list to a property list
     * @param list list which should be converted
     * @return serialized list
     */
    public static @NotNull List<DatabaseProperty> serialize(final @NotNull List<String> list) throws PropertySerializationException {
        final List<DatabaseProperty> propertyList = new ArrayList<>();

        list.forEach(string -> {
            String[] array = string.split(" ");
            if (array.length > 2) {
                throw new PropertySerializationException("Invalid property.");
            }
            String name = array[0];
            DataType type = DataType.valueOf(array[1].toUpperCase());

            propertyList.add(new DatabaseProperty(name, type));
        });

        return propertyList;
    }
}
