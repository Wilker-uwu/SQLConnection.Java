package com.wilkers1.sqlconnection.controller;

import java.sql.SQLException;

import com.wilkers1.sqlconnection.model.Model;
import com.wilkers1.sqlconnection.model.RefParticipante;

class DAORefPar extends JdbcDAO implements DAOPrepare{
	
	@Override
	public void insert(Model mdl) throws SQLException {
		if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
		RefParticipante ref = (RefParticipante)mdl;
		if(ref.getPessoa()<1 || ref.getTarefa()<1) { throw idExp; }
		String sql = String.format("('%s', '%s');", ref.getTarefa(), ref.getPessoa());
		execute(pplInsert +sql);
	}
	
	@Override
	public void delete(Model mdl) throws SQLException {
		if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
		RefParticipante ref = (RefParticipante)mdl;
		if(ref.getPessoa()==-1 || ref.getTarefa()==-1) { throw noIdExp; }
		String sql = String.format("(idTarefa = '%d') AND (idPessoa = '%d');",
				ref.getTarefa(), ref.getPessoa());
		execute(pplDelete +sql);
	}
}