package atm.se.project.pacman.classes;

import atm.se.project.pacman.interfaces.Shape;


public class ShapeFactory {
	public Shape getShape(String sh){
		
		if(sh == null){
	         return null;
	      }		
	      if(sh.equalsIgnoreCase("CIRCLE")){
	         return new Circles(); 
	      } else if(sh.equalsIgnoreCase("SQUARE")){
	         return new Squares();
	      }
		
		return null;
	}
}