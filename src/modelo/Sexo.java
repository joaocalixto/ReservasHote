package modelo;

public enum Sexo {
	
	M("Masculido"),F("Feminino");
	
	private String nome;

	private Sexo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
