package app;

public class Funcionario extends Pessoa{
    public String funcao;
public Funcionario(Pessoa pessoa, String funcao){
	
}
public Funcionario (int id, String nome, String cpf, Endereco endereco, String funcao){
				this.id=id;
        this.nome=nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.funcao=funcao;
}
}