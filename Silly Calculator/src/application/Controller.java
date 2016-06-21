package application;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controller {
	
	@FXML
	private Label display = new Label();
	@FXML
	private HBox bDisplay = new HBox(display);
	@FXML
	private Button B0 = new Button();
	@FXML
	private Button B1 = new Button();
	@FXML
	private Button B2 = new Button();
	@FXML
	private Button B3 = new Button();
	@FXML
	private Button B4 = new Button();
	@FXML
	private Button B5 = new Button();
	@FXML
	private Button B6 = new Button();
	@FXML
	private Button B7 = new Button();
	@FXML
	private Button B8 = new Button();
	@FXML
	private Button B9 = new Button();
	@FXML
	private Button BPlus = new Button();
	@FXML
	private Button BMin = new Button();
	@FXML
	private Button BTimes = new Button();
	@FXML
	private Button BDiv = new Button();
	@FXML
	private Button BEq = new Button();
	@FXML
	private Button BDot = new Button();
	@FXML
	private Button BC = new Button();
	@FXML
	private Button BMod = new Button();
	
	private Integer posSymbol = 0;
	private Boolean done = false;
	
	@FXML
	public void addIcon(ActionEvent event){
		Button button = (Button) event.getSource();
		String text = display.getText();
		String lastChar = "";
		if(done && isNumeric(button.getText())){
			text = button.getText();
			done = false;
		}
		else if(text.isEmpty() || text.equals("Error")){
			if(isNumeric(button.getText())){
				text = button.getText();
			}
			else if(button.getText().equals("-") || button.getText().equals("+")){
				text = button.getText() + " ";
			}
		}
		else{
			done = false;
			lastChar = text.substring(text.length()-1,text.length());
			if(isNumeric(lastChar) || lastChar.equals(".")){
				if(button.getText().equals(".") && !lastChar.equals(".")){
					text = text + ".";
				}
				else if(button.getText().equals("×") || button.getText().equals("÷") || button.getText().equals("+") || button.getText().equals("-") || button.getText().equals("%")){
					text = text + " " + button.getText() + " ";
					posSymbol = text.length() - 2;
				}
				else if (isNumeric(button.getText())){
					text = text + button.getText();
				}
			}
			else if(lastChar.equals(" ") && text.length()> 2){
				if(button.getText().equals("×") || button.getText().equals("÷") || button.getText().equals("+") || button.getText().equals("-") || button.getText().equals("%")){
					text = text.substring(0, posSymbol) + button.getText() + " ";
				}
				else if(isNumeric(button.getText())){
					text = text + button.getText();
				}
			}
			else if(lastChar.equals(" ") && text.length()<=2){
				if(button.getText().equals("+") || button.getText().equals("-")){
					text = button.getText() + " ";
				}
				else if(isNumeric(button.getText())){
					text = text + button.getText();
				}
			}
		}
		if(button.getText().equals("C")){
			text = "";
			posSymbol = 0;
		}
		if(button.getText().equals("=")){
			try{
				Double res = getResult(text);
				text = String.valueOf(res);
				done = true;
			}
			catch(NumberFormatException e){
				text = "Error";
			}
		}
		display.setText(text);
	}
	
	private static Double getResult(String text){
		ArrayList<Double> numList = new ArrayList<Double>();
		ArrayList<String> symList = new ArrayList<String>();
		String number = "";
		System.out.println(text);
		for (int i = 0; i < text.length(); i++){
		    String c = text.substring(i, i+1);
		    if(i == 0 && c.equals("-")){
		    	symList.add("-");
		    }
		    else if(i == 0 && c.equals("+")){
		    	symList.add("+");
		    }
		    else if(i == 0){
		    	symList.add("+");
		    	number = number + c;
		    }
		    if(i > 0){
		    	if(isNumeric(c) || c.equals(".")){
		    		number = number + c;
		    	}
		    	else if(!c.equals(" ")){
		    		symList.add(c);
		    		numList.add(Double.parseDouble(number));
		    		number = "";
		    	}
		    }
		    if(!number.isEmpty() && i == text.length()-1){
		    	numList.add(Double.parseDouble(number));
		    }
		}
		Double res = 0.0;
		for(int i = 0; i< numList.size(); i++){
			Double num  = numList.get(i);
			String sym = symList.get(i);
			if(sym.equals("+")){
				res = res + num;
			}
			else if(sym.equals("-")){
				res = res - num;
			}
			else if(sym.equals("×")){
				res = res * num;
			}
			else if(sym.equals("÷")){
				res = res / num;
			}
			else if(sym.equals("%")){
				res = res % num;
			}
		}
		return res;
	}
	
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
