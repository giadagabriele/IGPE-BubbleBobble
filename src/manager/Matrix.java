package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//CLASSE CHE CREA LA MATRICE DEL GIOCO
public class Matrix {
	private String [][]matrix;
	private int rows;
	private int columns;
	private int pos=0;
    private String[] temp=new String[31];
	public static int level=1;
	
    public Matrix(int level) {
		rows=16;
		columns=31;
		this.matrix=new String[rows][columns];
		
		if(level==1)
			readFile("Map1");
		
		else if(level==2)
			readFile("Map2");
		
		else if(level==3)
			readFile("Map3");
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void readFile(String file) {
		
		String fileName="src"+File.separator+file;
		try {
			BufferedReader bf=new BufferedReader(new FileReader(fileName));
			while(bf.ready()) 
			{
				String line=bf.readLine();
				temp=line.split(" ");
				
				for(int i=0; i<columns; i++)
					matrix[pos][i]=temp[i];
				pos++;
			}	
			
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
	  }
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}
}

