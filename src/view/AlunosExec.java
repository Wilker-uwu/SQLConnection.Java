package view;

import controller.AlunosJdbcDAO;
import model.Alunos;

public class AlunosExec {

	public static void main(String[] args) {
		try {
			Alunos aln = new Alunos("My Name", "Somewhere", "Somewhere else");
			AlunosJdbcDAO.delete(aln);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
