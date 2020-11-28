import javax.swing.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import java.io.File;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.axis.*;
import java.io.*;
import java.awt.*;

//n : la cantidad de veces que se hace el experimento
//p : la probabilidad de que un experimento arroje éxito.
//k : la cantidad de veces que se obtiene éxito en las n veces que se hace el experimento.

public class Binomial{
	
	double k;
	double p=0;
	int res, n, i, cex, cfr, j, sum1, sum2, r, resu;
	double comb, x, prob; 
	int fact1=1;
	int fact2=1;
	int fact3=1;
	int a=1;
	double xyz, klm, varianza, desvEst;
	
	public Binomial(){
		
		String vec=JOptionPane.showInputDialog(null, "Introduce el numero de veces que se hace el experimento");
		n=Integer.parseInt(vec);
		
		String posr= JOptionPane.showInputDialog(null, "¿Cuantos posible resultados puede tener el experimento?");
		res= Integer.parseInt(posr);
		
		resu=res;
		
		//p= (a/res);
		//p=0.5;
		p=(p+1)/res;
				
		XYSeries valores1= new XYSeries("Numero de Exitos ");
		XYSeries valores2= new XYSeries("Numero de Fracasos ");
		
		JTextArea resultados=new JTextArea(30,30);
		resultados.setEditable(false);	
		JScrollPane sp=new JScrollPane(resultados);
		
						
		
		for(i=1; i<=n; i++){
			
			int cd= JOptionPane.showConfirmDialog(null, "El experimento numero "+i+" fue exitoso");
			
			if(cd==JOptionPane.YES_OPTION){
				
				cex= cex + 1;				
				
			}
			
			if(cd==JOptionPane.NO_OPTION){
				
				cfr= cfr + 1;
			}
			
			if(cd==JOptionPane.CANCEL_OPTION){
				
				System.exit(0);
				
			}
			
			valores1.add(i, cex);
			valores2.add(i, cfr);
			
			resultados.append("Experimento "+i+" Resultado "+cd+"\n");
			
		}
		
		r= cex;
		sum1= n - r;
		
		for(j=1; j<=n; j++){
			
			fact1= fact1 * j;
		}
		
		for(i=1; i<=sum1; i++){
			
			fact2= fact2 * i;
		}
		
		for (j=1; j<=cex; j++){
			
			fact3= fact3 * j;
		}
		
		varianza= ((n*p) * (1-p));
		
		desvEst= Math.sqrt(varianza);
		
		comb= fact1 / ((fact2) * (fact3));
		
	
		prob= comb * ((Math.pow(p,cex)) * (Math.pow(p,cfr)));//probabilidad de obtener el num de exitos
		
					
		resultados.append("\n\nProbabilidad "+prob+"\n varianza "+varianza+"\n Desviacion Estandar "+desvEst);
		
		XYSeriesCollection juegoDatos = new XYSeriesCollection();
	       	juegoDatos.addSeries(valores1);
	       	juegoDatos.addSeries(valores2);
	       	
	       	JFreeChart grafica = ChartFactory.createXYLineChart("Funcion de Distribucion de Probabilidad Binomial", // Title
		    "X", // x-axis Label
		    //false,
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
		     JFrame ventana=new JFrame("Simulacion");
		     ventana.add(area);
		     ventana.setSize(900,500);
		     ventana.setVisible(true);     
		     
		     
	}
	
	public static void main(String agrs[]){
		
		Binomial objeto= new Binomial();
	}
}