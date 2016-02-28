import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.*;
import java.awt.Font;

public class Barras extends JPanel{
	
	static String titulo;
	static int n;
	static String nombres[];
	static int valores[];
	static int max;


	public static void leerTxt(){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;

 
      	try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("EjemploEntrada.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         titulo = br.readLine();
	         n = Integer.parseInt(br.readLine());
	         nombres = new String[n];
	         for(int i = 0; i < n; i++){
	         	nombres[i] = br.readLine();
	         }
	         valores = new int[n];
	         for(int i = 0; i < n; i++){
	         	valores[i] = Integer.parseInt(br.readLine());
	         }

	         System.out.println(titulo);
	         System.out.println(n);
	         for(int i = 0; i < n; i++){
	         	System.out.println(nombres[i]);
	         }
	         for(int i = 0; i < n; i++){
	         	System.out.println(valores[i]);
	         	
	         }


	    }
      	catch(Exception e){
        	e.printStackTrace();
      	}finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
        	try{                    
            	if( null != fr ){   
               		fr.close();     
            }                  
         	}catch (Exception e2){ 
            	e2.printStackTrace();
         	}
      	}
   	}

   	public static void maximo(){
   		int mayor = valores[0];
   		int x = 0;
   		for (int i = 0; i<n; i++){
   			if (mayor > valores[i]) 
   				x++;
   			else if(mayor == valores[i]){
   				mayor = valores[i];
   			}else if(mayor < valores[i]){
   				mayor = valores[i];
   			}
		}
		max = mayor;
   	}

	public void paintComponent(Graphics g){
		    super.paintComponent(g);
        int ancho = getWidth();
        int alto  = getHeight();

        g.setFont(new Font("TimesRoman", Font.BOLD, 23)); 

        int r []= {0,   255, 255, 103, 140, 201, 255, 178, 115, 41};
        int v []= {206, 64,  127, 178, 11,  135, 241, 94,  115, 178};
        int a []= {209, 64,  36,  41,  110, 255, 135, 41,  115, 140};
        String valoresString[] = new String[n];
        //pinta las grafica
        g.setColor(new Color(0,0,0));
        g.drawLine(70,520,600,520);
        g.drawLine(70,80,70,520);
        g.drawString(titulo,70,30);
        //grafica
        if(n<1){
        	g.setColor(new Color(255,0,0));
        	g.drawString("No hay valores para graficar o mando valores incorrectos...",100,300);
        } 
        g.setFont(new Font("TimesRoman", Font.PLAIN, 13)); 
        if (n>=1){
        	int enX = 0; //para separar en x cada grafica
      		int enY = 0; //para obtener el porcentaje de cada grafica para poder graficarla
   			  int constante = 440; //esta es la constante en y para el valor maximo de una grafica
    			for(int i=0;i<n;i++){
    				enY = (valores[i]*constante)/max; //multiplico el valor por la constante y lo divido entre el maximo para sacar el tamaño de esa grafica
    				enY = constante-enY;
    				g.setColor(new Color(r[i],v[i],a[i])); //obtengo colores diferentes
    				g.fillRect(73+enX,80+enY,46,440-enY); //dibujo un rectangulo que van a variar en X(con espacio de 3) y en Y (dependiendo el valor de cada valor)
    				enX = enX + 53;	//agrego 53 unidades a x para que no se peguen 
  					g.setColor(new Color(0,0,0));
  					g.drawLine(67,80+enY,70,80+enY);
  					valoresString [i] = Integer.toString(valores[i]);
  					g.drawString(valoresString[i],35+enX,75+enY);

				}
			//pinta los cuadros y los strings de los nombres de la grafica
			for(int i = 0; i<n; i++){
				g.setColor(new Color(r[i],v[i],a[i]));
				g.fillRect(600,80+(i*20),10,10);
				g.setColor(new Color(0,0,0));
				g.drawString(nombres[i],620,88+(i*20));
			}
   			
        }
	}

	public static void main(String args[]){
		leerTxt();
		maximo();
		Barras panel = new Barras();
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(800, 600);
        application.setVisible(true);
	}
}