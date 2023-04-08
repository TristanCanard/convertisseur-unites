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

public class SampleController implements Initializable
{

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

    

    @FXML
    private ObservableList<String> listInformations=FXCollections.observableArrayList("Bit","Octet","Kilo-octet","Mégaoctet","Gigaoctet");
    private double []informations= {0.125,1.0,1000.0,1000000.0,1000000000.0};
    
    @FXML
    private ObservableList<String> listVolumes=FXCollections.observableArrayList("Millilitre","Litre","Mètre cube","Pouce cube","Pied cube");
    private double []volumes= {1.0,1000.0,1000000.0,16.387,28320};
    
    @FXML
    private ObservableList<String> listFréquences=FXCollections.observableArrayList("Hertz","Kilohertz","Mégahertz","Gigahertz");
    private double []fréquences= {1.0,1000.0,1000000.0,1000000000.0};
    
    @FXML
    private ObservableList<String> listÉnergies=FXCollections.observableArrayList("Joule","Kilojoule","Kilowattheure","Gramme de calories","Unité thermique britannique");
    private double []énergies= {1.0,1000.0,3600000.0,4.184,1055.0};
    
    @FXML
    private ObservableList<String> listMasses=FXCollections.observableArrayList("Milligramme","Gramme","Kilogramme","Livre","Tonne");
    private double []masses= {0.001,1.0,1000.0,453.6,1000000.0};
    
	@Override
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
	
	@FXML
	private double setConv(ComboBox a, double b[])
	{
		int item=a.getSelectionModel().getSelectedIndex();
		double val=b[item];
		return val;
	}
	
	@FXML
	private void convert(ComboBox a, ComboBox b, TextField c, TextField d, double e[])
	{
		double from=setConv(a,e);
		double depart=0;
		
		if (c.getText().equals(""))
		{
			depart=0;
		}
		else
		{
			depart=Double.parseDouble(c.getText());
		}
		double to=setConv(b,e);
		double dest=(from/to)*depart;
		d.setText(String.valueOf(dest));
	}
	
	@FXML
	private void verifNum(KeyEvent e)
	{
		TextField txt=(TextField)e.getSource();

		txt.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
			{
				txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
			}

	});
	}
	
	public void quitter()
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Confirmation");
		alert.setTitle("Sortie");
		alert.setContentText("Voudrais-tu vraiment quitter?");
		Optional<ButtonType> result=alert.showAndWait();
		if(result.get()==ButtonType.OK)
			System.exit(0);
    }
	
	@FXML
	private void ConvertS1()
	{
		convert(ScboBox1,ScboBox2,StxtField1,StxtField2,informations);
	}
	
	@FXML
	private void ConvertS2()
	{
		convert(ScboBox2,ScboBox1,StxtField2,StxtField1,informations);
	}
	
	@FXML
	private void ConvertV1()
	{
		convert(VcboBox1,VcboBox2,VtxtField1,VtxtField2,volumes);
	}
	
	@FXML
	private void ConvertV2()
	{
		convert(VcboBox2,VcboBox1,VtxtField2,VtxtField1,volumes);
	}

	@FXML
	private void ConvertF1()
	{
		convert(FcboBox1,FcboBox2,FtxtField1,FtxtField2,fréquences);
	}
	
	@FXML
	private void ConvertF2()
	{
		convert(FcboBox2,FcboBox1,FtxtField2,FtxtField1,fréquences);
	}
	
	@FXML
	private void ConvertE1()
	{
		convert(EcboBox1,EcboBox2,EtxtField1,EtxtField2,énergies);
	}
	
	@FXML
	private void ConvertE2()
	{
		convert(EcboBox2,EcboBox1,EtxtField2,EtxtField1,énergies);
	}
	
	@FXML
	private void ConvertM1()
	{
		convert(McboBox1,McboBox2,MtxtField1,MtxtField2,masses);
	}
	
	@FXML
	private void ConvertM2()
	{
		convert(McboBox2,McboBox1,MtxtField2,MtxtField1,masses);
	}
}
