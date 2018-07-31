package view;

import controller.AlunosJdbcDAO;
import controller.JdbUtil;
import model.Alunos;

public class AlunosExec {

	public static void main(String[] args) {
		try {
			JdbUtil.connect();
			Alunos aln = new Alunos("My Name", "Somewhere", "Somewhere else");
			AlunosJdbcDAO.insert(aln);
			JdbUtil.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
