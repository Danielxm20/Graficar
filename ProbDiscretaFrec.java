import javax.swing.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import java.io.File;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import java.io.*;
import java.awt.*;

public class ProbDiscretaFrec{
	
	int i=0,a,b,j, frecuencia, cant;
	double media=0, varianza=0, desvEst, suma = 0, sum=0, sumat;
	String datMin,datMay, frecuen;
	double  valor, probX [], probMin, probMax,grafica2[];
	int sumaFrec = 0;
	int [] fr;
	int [] vax;
	boolean verdad=true;
		
	public void discreta(){
		
	
		
		String datMin = JOptionPane.showInputDialog (null,"Parametro para  Xa: ");
		a = Integer.parseInt (datMin);
		
		String datMax = JOptionPane.showInputDialog (null,"Parametro para Xb: ");
		b = Integer.parseInt (datMax);
		
		cant = b-a;
		fr = new int [cant+2];
		vax = new int [cant+2];
		probX=new double[cant+2];
		double cuentaGraf=0;
		grafica2 = new double [cant+2];
		
		
		XYSeries datos = new XYSeries("Probabilidad Discreta F(x)");
		XYSeries datos2 = new XYSeries("Probabilidad Discreta F(t)");
		
		JTextArea resultado=new JTextArea(30,30);
		resultado.setEditable(false);	
		JScrollPane vete=new JScrollPane(resultado);
		while(verdad){
		
					for(j=a; j<=b; j++){
	
	    		frecuen = JOptionPane.showInputDialog (null,"Frecuencia de eventos: ");
	    		frecuencia = Integer.parseInt (frecuen);
	    		vax [j]=j;
	    		fr [j]=frecuencia;
	    	
	    		sum = sum + fr [j];
	    		
	    		
	    		
	    		System.out.println("X= "+ vax [j]+"--- frecuencia= "+ fr [j]);
	    		
	    		
	    	
	    		resultado.append ("X= "+ vax [j]+"--- frecuencia= "+ fr [j]+ "\n" );	
			}
			
			//System.out.println(sum);
			for(j=a; j<=b; j++){
				
				probX[j]=fr[j]/sum;
				
	    		resultado.append("\nProbabilidad de X("+j+")="+probX[j]);
	    		
	    		//****//
	    		media = media + (j * probX [j]);

	    			
	    		cuentaGraf = cuentaGraf + probX [j];
	    		
	    			    		
	    		varianza  = varianza + (Math.pow(vax[j],2)* probX [j]);// - (Math.pow(media,2));
	    		//varianza= varianza * (Math.pow(b-media,2)) * (probX[j]);
	    		
			
				//suma = suma + var;
			
				//varianza = suma;

	    		grafica2 [j]= cuentaGraf;
	    			
	    		datos.add(j, probX[j]); // Datos es un objeto de la clase XYSeries o se de grafica
	    		
	    		datos2.add(j, grafica2[j]); 
	    		    		
	    			    				
			}
			
			varianza  = varianza - (Math.pow(media,2));
			
						
			//*** System.out.println ("X: "+ pos1 [i][1]+ " f(x): "+ pos2 [i][1]);**//
			System.out.println ("cuentaGraf= "+cuentaGraf);
		
			
			// Calculamos la varianza
			
			
			// Calculando la desviacion Estandar
			
			desvEst = Math.sqrt(varianza);
			
			
			
	    		//****//
			
			//***
			resultado.append("\n\nMedia = "+media+"\n  Varianza = "+varianza+"\n Desviacion estandar= "+desvEst);
			//***
			
			XYSeriesCollection juegoDatos = new XYSeriesCollection();
	       	juegoDatos.addSeries(datos);
	       	juegoDatos.addSeries(datos2);
	       	
	       	JFreeChart grafica = ChartFactory.createXYAreaChart("Probabilidad Discreta", // Title
		    "X", // x-axis Label
		    "F(X)", // y-axis Label
		     juegoDatos, // Dataset
		     PlotOrientation.VERTICAL, // Plot Orientation
		     true, // Show Legend
		     true, // Use tooltips
		     false // Configure chart to generate URLs?
		     );
		     
		     ChartPanel pantalla=new ChartPanel(grafica);
		     pantalla.setPreferredSize(new Dimension(500,500));
		     
		     JPanel area=new JPanel (new BorderLayout());
		     area.add(pantalla,BorderLayout.EAST);
		     area.add(vete,BorderLayout.WEST);
		     JFrame ventana=new JFrame("Interfacez");
		     ventana.add(area);
		     ventana.setSize(900,500);
		     ventana.setVisible(true);
		     
		     
		     
		     
		     /*try {
			 ChartUtilities.saveChartAsJPEG(new File("probabilidad.jpg"), grafica, 500,
			 300);
			 			 
			 
		     } catch (IOException e) {
			 System.err.println("Error creando grafico.");
			 
		}*/
		
			verdad=false;
		}
	}
	
	public void calcula(){
		
		//XYSeries datos2 = new XYSeries("Probabilidad Discreta");
		
			
			String opciones[]={"Continua","Discreta"   };
		String opcion=""+ JOptionPane.showInputDialog(null,
                                     "Elija La probabilidad a usar:" ,
                                     "Mensage",
                                     JOptionPane.QUESTION_MESSAGE,
                                     null,
                                     opciones,
                                     "Continua");

		if(opcion.equals("Continua")){
		
		
		String datMin = JOptionPane.showInputDialog (null,"Parametro para  Xa: ");
		double a = Double.parseDouble (datMin);
		
		String datMax = JOptionPane.showInputDialog (null,"Parametro para Xb: ");
		double b = Double.parseDouble (datMax);
		
		System.out.print ("P ("+ a + "<= X <= "+ b + ")");
		
		probMin = Math.pow(a,2)/4;
		probMax = Math.pow(b,2)/4;
		
		double probabilidad = probMax - probMin;
		
		System.out.println (" = "+ probabilidad+"\n");
		
		if (probabilidad == 1){
			
			// Calcular la media
			media = (Math.pow(b,3)/6)-(Math.pow(a,3)/6);
			
			System.out.println ("La media es: "+ media);
			
			// Calcular la Varianza
			
			varianza = ((Math.pow(b,4)/8)-(Math.pow(a,4)/8))-(Math.pow(media,2));
			System.out.println ("\nLa varianza es: "+ varianza);
			
			// Calcular la Desviacion Estandar
			
			desvEst= Math.sqrt(varianza);
			
			System.out.println ("\nLa desviación estandar es = "+ desvEst);
		}
		else{
			JOptionPane.showMessageDialog(null," NO CUMPLEN con las caracteristicas de una función de probabilidad continua ");
		}
		
		}else{discreta();
		}
	}
	
}
class app{
	
	public static void main (String arg []){
		
		ProbDiscretaFrec pd = new ProbDiscretaFrec ();
		pd.calcula ();
	
	}
}