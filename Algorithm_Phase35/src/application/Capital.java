package application;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class Capital {
	private double WIDTH = 1500;
	private double HEIGHT = 750;
	private String name;
	private double longitude;
	private double latitude;
	private RadioButton radioButton = new RadioButton();
	private ToggleGroup group;

	public Capital() {
		// TODO Auto-generated constructor stub
	}

	public Capital(String line) {
		String[] arr = line.split(":");

		this.name = arr[0];

		this.latitude = Double.parseDouble(arr[1]);
		this.longitude = Double.parseDouble(arr[2]);

		radioButton.setToggleGroup(group);
		radioButton.setPadding(new Insets(-7.5));
		ImageView vi = new ImageView(new Image("locationBase.png"));
		vi.setFitHeight(16);
		vi.setFitWidth(16);
		radioButton.setGraphic(vi);

		Tooltip tip = new Tooltip(this.getName());
		tip.setFont(new Font(16));
		tip.setStyle("-fx-background-color:grey;");
		radioButton.setTooltip(tip);

		radioButton.setOnAction(o -> {
			if (Main.pointNumber == 0) {
				ImageView vi0 = new ImageView(new Image("locationStart.png"));
				vi0.setFitHeight(16);
				vi0.setFitWidth(16);
				radioButton.setGraphic(vi0);
			}
			
			if (Main.pointNumber == 1) {
				ImageView vi0 = new ImageView(new Image("locationFinish.png"));
				vi0.setFitHeight(16);
				vi0.setFitWidth(16);
				radioButton.setGraphic(vi0);
			}
			radioButton.setSelected(true);
			Main.pointNumber += 1;
			if (Main.pointNumber == 2) {
				Main.lock();
			}
			
			if (Main.chooseInMap.isSelected()) {
				if (Main.pointNumber == 2) {
					Main.destinationComboBox.getSelectionModel().select(getName());
				}
				if (Main.pointNumber == 1) {
					Main.sourceComboBox.getSelectionModel().select(getName());
				}
			}

		});
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public RadioButton getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(RadioButton radioButton) {
		this.radioButton = radioButton;
	}

	public ToggleGroup getGroup() {
		return group;
	} 

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}

	public double getX() {
		return  ((longitude + 180) / 360 * WIDTH);
	} 
	
	public double getY() { 
		return (HEIGHT - (latitude + 90) / 180 * HEIGHT);
	}
}
