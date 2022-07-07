package util;

public class Sala {
    private int linha;
    private int coluna;

    public Sala(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }


    public int getLinha(){
		return linha;
	}
	
	public int getColuna(){
		return coluna;
	}

    public String getId(){
		return linha+":"+coluna;
	}

}
