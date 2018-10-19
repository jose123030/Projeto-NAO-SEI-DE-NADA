package app;

public class Endereco {
    public String logradouro;
    public String numero;
    public String complemento;
    public String bairro;
    public String cidade;
    public String estado;
	public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String estado){
		this.logradouro=logradouro;
		this.numero=numero;
		this.complemento=complemento;
		this.bairro=bairro;
		this.cidade=cidade;
		this.estado=estado;
	}
	public String locateOnMap (){
		return "https://www.google.com/maps/search/?api=1&query="+this.logradouro replace(" ", "+")+"+"+this.numero replace(" ", "+")+"+"+this.bairro replace(" ", "+")+"+"+this.cidade replace(" ", "+")+"+"+this.estado replace(" ", "+");
	}
}