package study.toby_spring.Ch1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	public abstract Connection makeConnection() throws ClassNotFoundException,
			SQLException;

}