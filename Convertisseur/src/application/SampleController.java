/*
*Author:Tristan Kako
*Date:5 avril, 2023
*Description:Application qui permet de convertir des unités divers, y compris des conversions entre le système impérial et le système metrique.
*/
package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class SampleController implements Initializable // Pour initializer les combo box
{
	// Déclaration des fxId pour tous les éléments utilisés dans le fichier .fxml
    @FXML
    private ComboBox<String> ScboBox1;

    @FXML
    private TextField VtxtField1;

    @FXML
    private ComboBox<String> ScboBox2;

    @FXML
    private TextField VtxtField2;

    @FXML
    private TextField StxtField1;

    @FXML
    private TextField StxtField2;

    @FXML
    private ComboBox<String> VcboBox1;

    @FXML
    private ComboBox<String> VcboBox2;
    
    @FXML
    private TextField FtxtField1;

    @FXML
    private ComboBox<String> FcboBox2;

    @FXML
    private ComboBox<String> FcboBox1;
    
    @FXML
    private TextField FtxtField2;
    
    @FXML
    private ComboBox<String> EcboBox1;
    
    @FXML
    private ComboBox<String> EcboBox2;
    
    @FXML
    private ComboBox<String> McboBox2;

    @FXML
    private ComboBox<String> McboBox1;
    
    @FXML
    private TextField MtxtField2;
    
    @FXML
    private TextField MtxtField1;
    
    @FXML
    private TextField EtxtField1;

    @FXML
    private TextField EtxtField2;

    

    @FXML // Listes avec les unités de stockage et leurs conversions
    private ObservableList<String> listInformations=FXCollections.observableArrayList("Bit","Octet","Kilo-octet","Mégaoctet","Gigaoctet");
    private double []informations= {0.125,1.0,1000.0,1000000.0,1000000000.0};
    
    @FXML // Listes avec les unités de volume et leurs conversions
    private ObservableList<String> listVolumes=FXCollections.observableArrayList("Millilitre","Litre","Mètre cube","Pouce cube","Pied cube");
    private double []volumes= {1.0,1000.0,1000000.0,16.387,28320};
    
    @FXML // Listes avec les unités de fréquence et leurs conversions
    private ObservableList<String> listFréquences=FXCollections.observableArrayList("Hertz","Kilohertz","Mégahertz","Gigahertz");
    private double []fréquences= {1.0,1000.0,1000000.0,1000000000.0};
    
    @FXML // Listes avec les unités d'énergie et leurs conversions
    private ObservableList<String> listÉnergies=FXCollections.observableArrayList("Joule","Kilojoule","Kilowattheure","Gramme de calories","Unité thermique britannique");
    private double []énergies= {1.0,1000.0,3600000.0,4.184,1055.0};
    
    @FXML // Listes avec les unités de masse et leurs conversions
    private ObservableList<String> listMasses=FXCollections.observableArrayList("Milligramme","Gramme","Kilogramme","Livre","Tonne");
    private double []masses= {0.001,1.0,1000.0,453.6,1000000.0};
    
	@Override // Méthode qui met les options d'unité dans les combo box et selectionne la première unité de chacun
	public void initialize(URL location, ResourceBundle resources)
	{
		ScboBox1.setItems(listInformations);
		ScboBox2.setItems(listInformations);
		ScboBox1.getSelectionModel().selectFirst();
		ScboBox2.getSelectionModel().selectFirst();
		
		VcboBox1.setItems(listVolumes);
		VcboBox2.setItems(listVolumes);
		VcboBox1.getSelectionModel().selectFirst();
		VcboBox2.getSelectionModel().selectFirst();
		
		FcboBox1.setItems(listFréquences);
		FcboBox2.setItems(listFréquences);
		FcboBox1.getSelectionModel().selectFirst();
		FcboBox2.getSelectionModel().selectFirst();
		
		EcboBox1.setItems(listÉnergies);
		EcboBox2.setItems(listÉnergies);
		EcboBox1.getSelectionModel().selectFirst();
		EcboBox2.getSelectionModel().selectFirst();
		
		McboBox1.setItems(listMasses);
		McboBox2.setItems(listMasses);
		McboBox1.getSelectionModel().selectFirst();
		McboBox2.getSelectionModel().selectFirst();
		
	}
	
	@FXML // Méthode qui prend en paramètres un combo box et une liste de double
	private double setConv(ComboBox a, double b[])
	{
		int item=a.getSelectionModel().getSelectedIndex();
		double val=b[item];
		return val; // Retourne la valeur de la liste à un indexe égal à celui de l'unité selectionné dans le combo box
	}
	
	@FXML // Méthode qui prend en paramètres deux combo box, deux text fields et un liste de double, et permet la conversion d'unités
	private void convert(ComboBox a, ComboBox b, TextField c, TextField d, double e[])
	{
		double from=setConv(a,e);
		double depart=0;
		
		if (c.getText().equals("")) // S'il n'y a pas de texte dans le text field, on utilise 0 comme valeur de départ
		{
			depart=0;
		}
		else
		{
			depart=Double.parseDouble(c.getText());
		}
		double to=setConv(b,e);
		double dest=(from/to)*depart; // Fait la conversion entre l'unité du combo box a et l'unité du combo box b
		d.setText(String.valueOf(dest)); // Met la valeur obtenue dans le text field qui n'a pas encore été utilisé
	}
	
	@FXML
	private void verifNum(KeyEvent e) // Méthode qui permet de vérifier si l'entrée dans un text field est numérique
	{
		TextField txt=(TextField)e.getSource();

		txt.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$")) // Vérifie si le caractère est un chiffre entre 0 et 9
			{
				txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
			}

	});
	}
	
	public void quitter() // Méthode qui vérifie si l'utilisateur veut réellement quitter
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Confirmation");
		alert.setTitle("Sortie");
		alert.setContentText("Voudrais-tu vraiment quitter?");
		Optional<ButtonType> result=alert.showAndWait();
		if(result.get()==ButtonType.OK)
			System.exit(0); // Ferme l'application
    }
	
	@FXML // Conversion pour le text field et le combo box à gauche pour le stockage
	private void ConvertS1()
	{
		convert(ScboBox1,ScboBox2,StxtField1,StxtField2,informations);
	}
	
	@FXML // Conversion pour le text field et le combo box à droite pour le stockage
	private void ConvertS2()
	{
		convert(ScboBox2,ScboBox1,StxtField2,StxtField1,informations);
	}
	
	@FXML // Conversion pour le text field et le combo box à gauche pour le volume
	private void ConvertV1()
	{
		convert(VcboBox1,VcboBox2,VtxtField1,VtxtField2,volumes);
	}
	
	@FXML // Conversion pour le text field et le combo box à droite pour le volume
	private void ConvertV2()
	{
		convert(VcboBox2,VcboBox1,VtxtField2,VtxtField1,volumes);
	}

	@FXML // Conversion pour le text field et le combo box à gauche pour la fréquence
	private void ConvertF1()
	{
		convert(FcboBox1,FcboBox2,FtxtField1,FtxtField2,fréquences);
	}
	
	@FXML // Conversion pour le text field et le combo box à droite pour la fréquence
	private void ConvertF2()
	{
		convert(FcboBox2,FcboBox1,FtxtField2,FtxtField1,fréquences);
	}
	
	@FXML // Conversion pour le text field et le combo box à gauche pour l'énergie
	private void ConvertE1()
	{
		convert(EcboBox1,EcboBox2,EtxtField1,EtxtField2,énergies);
	}
	
	@FXML // Conversion pour le text field et le combo box à droite pour l'énergie
	private void ConvertE2()
	{
		convert(EcboBox2,EcboBox1,EtxtField2,EtxtField1,énergies);
	}
	
	@FXML // Conversion pour le text field et le combo box à gauche pour la masse
	private void ConvertM1()
	{
		convert(McboBox1,McboBox2,MtxtField1,MtxtField2,masses);
	}
	
	@FXML // Conversion pour le text field et le combo box à droite pour la masse
	private void ConvertM2()
	{
		convert(McboBox2,McboBox1,MtxtField2,MtxtField1,masses);
	}
}
