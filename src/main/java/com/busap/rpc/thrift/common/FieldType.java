package com.busap.rpc.thrift.common;

import org.apache.thrift.protocol.TType;

public enum FieldType {

	DOUBLE("Double", "double", TType.DOUBLE, "0d"),

	/** The IN t64. */
	INT64("Long", "int64", TType.I64, "0L"),

	/** The IN t32. */
	INT32("Integer", "int32", TType.I32, "0"),

	/** The bool. */
	BOOL("Boolean", "bool", TType.BOOL, "false"),

	/** The string. */
	STRING("String", "string", TType.STRING, "\"\""),

	/** The bytes. */
	BYTE("Byte", "byte", TType.BYTE, "0"),

	/** The object. */
	STRUCT("Struct", "struct", TType.STRUCT, null),

	/** The enum. */
	LIST("List", "list", TType.LIST, null),
	/** The enum. */
	SET("Set", "set", TType.SET, null),
	/** The enum. */
	MAP("Map", "map", TType.MAP, null),

	/** The default. */
	DEFAULT("", "", TType.STRING, null);

	/** java original type. */
	private final String javaType;

	/** thrift type. */
	private final String type;

	/** to primitive type. */
	private final byte toPrimitiveType;

	/** default value. */
	private String defaultValue;

	/**
	 * Constructor method.
	 * 
	 * @param javaType
	 *            java original type
	 * @param type
	 *            thrift type
	 * @param wireFormat
	 *            protobuf wire format type
	 * @param defaultValue
	 *            the default value
	 * @param suffix
	 *            the suffix
	 */

	private FieldType(String javaType, String type, byte toPrimitiveType,
			String defaultValue) {
		this.javaType = javaType;
		this.type = type;
		this.toPrimitiveType = toPrimitiveType;
		this.defaultValue = defaultValue;
	}

	/**
	 * Checks if is primitive.
	 * 
	 * @return true, if is primitive
	 */
	public boolean isPrimitive() {
		if (this == INT32 || this == DOUBLE || this == INT64 || this == BOOL) {
			return true;
		}

		return false;
	}

}
