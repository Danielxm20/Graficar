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

public class Geometrica {
	
	double varianza, desvEst, p, prob, ex;
	int numi, i;
	
	
	public Geometrica() {
		
		String ab= JOptionPane.showInputDialog(null, "¿Cual es la probabilida de exito? entre 0 y 1 ");
		p= Double.parseDouble(ab);
		
		if(p>0 && p<1){
		
		
		String bc= JOptionPane.showInputDialog(null, "Numero de intentos a calcular");
		numi= Integer.parseInt(bc);	
		
		}
		else{
			System.exit(0);
		}
		
		XYSeries valores= new XYSeries("Probabilidad ");
		
		JTextArea datos=new JTextArea(30,30);
		datos.setEditable(false);	
		JScrollPane sp=new JScrollPane(datos);
		
		for(i=1; i<numi; i++) {
			
			prob= prob + (p * (Math.pow((1-p),i-1)));
			
			valores.add(i, prob);
			
			datos.append("\n Probabilidad de cada intento  "+prob+"\n");
						
		}
		
		System.out.println("probab"+ prob);
		
		ex= 1/p; // numero de veces que es de esperar que tengamos que intentar para lograrlo
		datos.append("\n Numero de veces esperadas para tener exito "+ex+"\n"); 
		
		varianza=(1-p) / Math.pow(p,2);
		desvEst= Math.sqrt(varianza);
		
		datos.append("\n La varianza es = "+varianza+"\n La desviacion Estandar es= "+desvEst);
		
		XYSeriesCollection juegoDatos = new XYSeriesCollection();
	       	juegoDatos.addSeries(valores);
	       	//juegoDatos.addSeries(valores2);
	       	
	       	JFreeChart grafica = ChartFactory.createXYBarChart("Funcion de Distribucion de Probabilidad Geomatrica", // Title
		    "X", // x-axis Label
		    false,
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
		     area.add(sp,BorderLayout.WEST);
		     JFrame ventana=new JFrame("Grafica");
		     ventana.add(area);
		     ventana.setSize(900,500);
		     ventana.setVisible(true);	
		     
		
	}
	
	public static void main (String args[]){
		
		Geometrica objeto= new Geometrica();
	} 
}