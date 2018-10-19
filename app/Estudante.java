package app;

public class Estudante extends Pessoa{
    public String matricula;
    public int falta;
    public int presenca;
	
	public Estudante(Pessoa pessoa, String matricula){
	
	}

	public Estudante(int id, String nome, String cpf, Endereco endereco, String matricula) {
    this.id=id;
    this.nome= nome;
    this.cpf = cpf;
    this.endereco = endereco; 
    this.matricula= matricula;
    }

    public void contarFalta(){
    	this.falta=falta+1;
    	
    }

    public void contarPresenca(){
    	this.presenca=presenca+1;
    }

    public getFaltas(int falta){
    	super(falta);
    	return falta;
    }

    public getPresenca(int presenca){
    	super(presenca);
    	return presenca;
    }
}
