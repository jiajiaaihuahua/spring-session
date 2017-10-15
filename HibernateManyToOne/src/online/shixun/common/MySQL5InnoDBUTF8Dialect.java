package online.shixun.common;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 引擎 InnoDB
 * 编码 UTF-8
 */
public class MySQL5InnoDBUTF8Dialect extends MySQL5Dialect {

	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=UTF8";
	}

}
