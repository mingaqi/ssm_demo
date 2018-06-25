package com.domi.common.utils;

import java.util.UUID;

public class SqlUtil {

	public static String getUUIDByPk() {
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString();
		return key.substring(0, 8) + key.substring(9, 13)
				+ key.substring(14, 18) + key.substring(19, 23)
				+ key.substring(24);
	}
}
