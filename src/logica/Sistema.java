package logica;

import gui.Login;

public class Sistema {
		
	
		public void setTema(){
			try {  
				  salir:
				  //recorre todos los temas disponibles en el jdk
				  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				    switch (info.getName()) {
				      case "GTK+": //si encuentra tema gtk establece este
				    	  //javax.swing.UIManager.setLookAndFeel(info.getClassName());
				    	  javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
				        break salir;
				      case "Windows": //si encuentra tema windows establece este
				        javax.swing.UIManager.setLookAndFeel(info.getClassName());  
				        break salir;
				      default: //sino encuentra ninguno de los anteriores establece nimbus
				        javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");    
				        break;          
				    }        
				  }      
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
				  java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				}
		}
}
