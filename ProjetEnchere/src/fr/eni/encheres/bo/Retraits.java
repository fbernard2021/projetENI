package fr.eni.encheres.bo;

public class Retraits {
	
	private int numArticle;
	private String rue;
	private int codePostal;
	private String ville;
	
	public Retraits(int numArticle, String rue, int codePostal, String ville)
	{
		this.numArticle = numArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getNumArticle() {
		return numArticle;
	}

	public void setNumArticle(int numArticle) {
		this.numArticle = numArticle;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	

}
