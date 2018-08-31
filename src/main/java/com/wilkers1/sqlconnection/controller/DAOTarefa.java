package com.wilkers1.sqlconnection.controller;

import java.sql.SQLException;

import com.wilkers1.sqlconnection.model.Tarefa;

public class DAOTarefa extends JdbcDAO implements DAOPrepare {

	@Override
	protected void insert(Model mdl) throws SQLException {
		if(!(mdl instanceof Tarefa)) { throw invalidModelExp; }
		Tarefa tar = (Tarefa)mdl;
		if(tar.getId()!=-1) { throw idExp; }
		execute(tarInsert +tar.toString());
	}

	@Override
	protected void delete(Model mdl) throws SQLException, NullPointerException, IllegalAccessException {
		if(!(mdl instanceof Tarefa)) { throw invalidModelExp; }
		Tarefa tar = (Tarefa)mdl;
		if(tar.getId()!=-1) { throw idExp; }
		execute(tarDelete +tar.toString());
	}

}
