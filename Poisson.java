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

public class Poisson {
	
	double varianza, media, desvEst, sumatoria, cuenta;
	double t, k, intensidad;
	int [] inter;
	int [] frecuen;
	int max, min;
	int [] varX;
	double[] funDist;
	double[] valMed;
	int i, frecuencia;
	
	public Poisson(){
		
		String ab= JOptionPane.showInputDialog(null, "Introduce el valor minimo del intervalo");
		min= Integer.parseInt (ab);
		
		String ac= JOptionPane.showInputDialog(null, "Introduce el valor maximo del intervalo");
		max= Integer.parseInt(ac);
		
		inter= new int [max+1];
		frecuen= new int[max+1];
		varX= new int[max+2];
		funDist= new double[max+2];
		valMed= new double[max+2];
		
		//String cb= JOptionPane.showInputDialog (null, "Introduce la cantidad esperada de eventos por unidad de tiempo");
		//intensidad= Integer.parseInt (cb); 
		
		XYSeries valores= new XYSeries("Probabilidad discreta f(x)");
		XYSeries valores2= new XYSeries("Probabilidad discreta f(t)");
		
		JTextArea datos=new JTextArea(30,30);
		datos.setEditable(false);	
		JScrollPane sp=new JScrollPane(datos);
		
		
				
		desvEst= Math.sqrt(varianza);
		
		//JOptionPane.showMessageDialog (null,"La media es ="+ media+"\n"+"La desviacion estandar es= "+desvEst,"desviacion", JOptionPane.WARNING_MESSAGE);
		
		
		
		for( i=min; i<=max; i++){
			
			String cb= JOptionPane.showInputDialog (null, "Introduce la frecuencia de eventos");
			frecuencia = Integer.parseInt (cb); 
			
			varX[i]=i;
			frecuen[i]= frecuencia;
						
			sumatoria= sumatoria + frecuen[i];
			
			System.out.println("X ="+varX[i]+" Frecuencia= "+frecuen[i]);
			
			datos.append("X = "+varX[i]+" Frecuencia= "+frecuen[i]+"\n");
			
			
		}
		
		for( i=min; i<=max; i++){
			
			funDist[i]= frecuen[i]/sumatoria;
			
			datos.append("\nProbabilidad de X("+i+")="+funDist[i]);
			
			media= media + (i * funDist[i]);
			
			cuenta= cuenta + funDist[i];
			
			varianza  = varianza + (Math.pow(varX[i],2)* funDist [i]);
			
			valMed[i]= cuenta;
			
			valores.add(i, funDist[i]);
			valores2.add(i, valMed[i]);
		}
		
		varianza  = varianza - (Math.pow(media,2));
		
		desvEst= Math.sqrt(varianza);
		
		datos.append("\n\nMedia = "+media+"\n  Varianza = "+varianza+"\n Desviacion estandar= "+desvEst);
	
		
		XYSeriesCollection juegoValores = new XYSeriesCollection();
	    juegoValores.addSeries(valores);
	    juegoValores.addSeries(valores2);
	    
	    JFreeChart grafica= ChartFactory.createXYAreaChart("Funcion de Probabilidad de Poisson", "X", "F(x)", juegoValores, PlotOrientation.VERTICAL, true, true, false);
	    
	    ChartPanel pantalla=new ChartPanel(grafica);
		pantalla.setPreferredSize(new Dimension(500,500));
		     
		JPanel area=new JPanel (new BorderLayout());
		area.add(pantalla,BorderLayout.EAST);
		area.add(sp,BorderLayout.WEST);
		JFrame ventana=new JFrame("Simulacion");
		ventana.add(area);
		ventana.setSize(900,500);
		ventana.setVisible(true);
		     
		
	}
	
	public static void main(String arg[]){
		
		Poisson objeto= new Poisson();
	}
	
}