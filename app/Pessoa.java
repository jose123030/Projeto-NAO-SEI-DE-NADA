package app;

public class Pessoa{
	public int id;
	public String nome;
	public String cpf;
	public Endereco endereco;
	public boolean estarNoPredio;
public Pessoa(){

}
	public Pessoa(int id, String nome, String cpf, Endereco endereco){
		this.id=id;
		this.nome=nome;
		this.cpf=cpf;
		this.endereco=endereco;
	}
	public void entrar(){
		this.estarNoPredio=true;
	}
	public void sair(){
		this.estarNoPredio=false;
	}
	public boolean estarNoPredio(){
		if (this.estarNoPredio){
			return true;
		}
		else{
			return false;
		}
	}
}