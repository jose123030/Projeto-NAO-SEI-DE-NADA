package app;
import java.util.ArrayList;
import java.util.Arrays;

public class Professor extends Pessoa{
    public ArrayList<Turma> turmas;


	public Professor(Pessoa pessoa, Turma turmas[]){
	this(pessoa.id, pessoa.nome, pessoa.cpf, pessoa.endereco, turmas);
	
	} 
	public Professor(int id, String nome, String cpf, Endereco endereco, Turma turmas[]){
				super(id, nome, cpf, endereco);
				this.turmas= new ArrayList<Turma>();
				this.turmas=addAll(Arrays.asList(turmas));
	}
}