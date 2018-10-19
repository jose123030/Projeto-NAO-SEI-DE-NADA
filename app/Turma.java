package app;
import java.util.ArrayList;
import java.util.Arrays;

public class Turma{
    public String nome;
    public String descricao;
    public String turno;
	public ArrayList<Estudante> estudantes;

	public Turma(String nome, String descricao, String turno, Estudante estudantes[]){
		this.nome=nome;
		this.descricao=descricao;
		this.turno=turno;
		this.estudantes= new ArrayList<Estudante>;
		this.estudantes= addAll(Arrays.asList(estudantes));
	}

	public adicionarEstudante(Estudante aluno){
		this(aluno.matricula, aluno.falta, aluno.presenca, aluno.id, aluno.cpf, aluno.nome, aluno.endereco);
	}

	public void realizarChamada(){
		super(matricula, falta, presenca, id, cpf, nome, endereco);
		if(this.estarNoPredio=true){
			this.presenca=presenca;
		}
		else{
			this.falta=falta;
		}
	}
}