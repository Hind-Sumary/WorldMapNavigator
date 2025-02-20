package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	public static File file;
	static ComboBox<String> sourceComboBox = new ComboBox<String>();
	static ComboBox<String> destinationComboBox = new ComboBox<String>();
	static ToggleButton chooseInMap = new ToggleButton("Choose From Map");
	static ToggleButton chooseFromComboBox = new ToggleButton("ComboBox");
	static int pointNumber = 0;
	static Pane pane2;
	private Alert error = new Alert(AlertType.ERROR);
	private Stage primaryStage;
	private TextArea pathTextArea;

	static ArrayList<vertex> Capitals = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		StackPane root = new StackPane();

		Image backgroundImage = new Image("Travel_BackGround.jpg");

		ImageView imageView = new ImageView(backgroundImage);
		imageView.setPreserveRatio(true);
		imageView.fitWidthProperty().bind(primaryStage.widthProperty());
		imageView.fitHeightProperty().bind(primaryStage.heightProperty());

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setSpacing(20);
		hbox.setPadding(new Insets(40));

		VBox overlayBox = new VBox();
		overlayBox.setAlignment(Pos.CENTER);
		overlayBox.setSpacing(20);
		overlayBox.setPadding(new Insets(40));

		Text titleText = new Text("International Navigator");
		titleText.setFont(Font.font("Impact", FontWeight.BOLD, 55));
		titleText.setFill(Color.web("#75739b"));

		Text subText = new Text("Advanced Country-to-Country Route System");
		subText.setFont(Font.font("Impact", FontWeight.MEDIUM, 30));
		subText.setFill(Color.web("#75739b"));

		Button button = new Button();
		button.setText("Start");
		button.setFont(Font.font("Impact", FontWeight.BOLD, 35));
		button.setStyle("-fx-font-size: 35;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
		addHoverEffectStart(button, "Start ➡", "Start");
		button.setOnAction(e -> {
			Scene sc = new Scene(get2ndPane());
			primaryStage.setScene(sc);
			primaryStage.setFullScreen(true);
		});

		overlayBox.getChildren().addAll(titleText, subText, button);

		hbox.getChildren().addAll(overlayBox);

		root.getChildren().addAll(imageView, hbox);

		Scene scene = new Scene(root);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public BorderPane get2ndPane() {
		Image m = new Image("mmm.png");
		ImageView image = new ImageView(m);
		image.setFitHeight(780);
		image.setFitWidth(1503);
		image.setStyle("-fx-border-radius: 50;\n" + "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;"
				+ "-fx-background-radius: 50");

		pane2 = new Pane();
		pane2.getChildren().add(image);

		primaryStage.getIcons().add(new Image("plane.png"));
		primaryStage.setTitle("International Navigator");
		file = new File("input.txt");
		readFile(file);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(0, 20, 20, 0));
		pane.setStyle("-fx-background-color: #fee5e1;");

		ToggleGroup tg = new ToggleGroup();

		chooseInMap.setToggleGroup(tg);
		chooseFromComboBox.setToggleGroup(tg);

		chooseInMap.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		chooseFromComboBox
				.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
						+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		chooseInMap.setOnAction(e -> {
			chooseInMap
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #e6899b;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #fee5e1;");
			chooseFromComboBox
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		});

		chooseFromComboBox.setOnAction(e -> {
			chooseFromComboBox
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #e6899b;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #fee5e1;");
			chooseInMap
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		});

		sourceComboBox.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		destinationComboBox
				.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
						+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

		sourceComboBox.setOnAction(e -> {
			if (chooseFromComboBox.isSelected()) {
				for (int i = 0; i < Capitals.size(); i++) {
					if (Capitals.get(i).getCapital().getName()
							.equals(sourceComboBox.getSelectionModel().getSelectedItem())) {
						ImageView vi0 = new ImageView(new Image("locationStart.png"));
						vi0.setFitHeight(16);
						vi0.setFitWidth(16);
						Capitals.get(i).getCapital().getRadioButton().setGraphic(vi0);
						Capitals.get(i).getCapital().getRadioButton().setSelected(true);
						pointNumber += 1;
						if (pointNumber == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		destinationComboBox.setOnAction(e -> {
			if (chooseFromComboBox.isSelected()) {
				for (int i = 0; i < Capitals.size(); i++) {
					if (Capitals.get(i).getCapital().getName()
							.equals(destinationComboBox.getSelectionModel().getSelectedItem())) {
						ImageView iv = new ImageView(new Image("locationFinish.png"));
						iv.setFitHeight(16);
						iv.setFitWidth(16);
						Capitals.get(i).getCapital().getRadioButton().setGraphic(iv);
						Capitals.get(i).getCapital().getRadioButton().setSelected(true);
						pointNumber += 1;
						if (pointNumber == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		HBox hx = new HBox(10, chooseInMap, chooseFromComboBox);
		hx.setAlignment(Pos.CENTER);
		hx.setPadding(new Insets(3));

		Label sourcetxt = new Label("Sourse :");
		sourcetxt.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #e6899b;");
		sourcetxt.setPadding(new Insets(7));
		sourceComboBox.setMinWidth(150);
		for (int i = 0; i < Capitals.size(); i++) {
			sourceComboBox.getItems().add(Capitals.get(i).getCapital().getName());
		}

		IconedTextFieled(sourceComboBox);
		HBox h1 = new HBox(sourcetxt, sourceComboBox);
		h1.setAlignment(Pos.CENTER);

		Label targettxt = new Label("Target :");
		targettxt.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #e6899b;");
		targettxt.setPadding(new Insets(7));
		for (int i = 0; i < Capitals.size(); i++) {
			destinationComboBox.getItems().add(Capitals.get(i).getCapital().getName());
		}
		destinationComboBox.setMinWidth(150);
		IconedTextFieled(destinationComboBox);

		HBox h2 = new HBox(targettxt, destinationComboBox);
		h2.setAlignment(Pos.CENTER);

		Button run = new Button("Run");
		Button reset = new Button("Reset");

		HBox butBox = new HBox(20, run, reset);
		butBox.setAlignment(Pos.CENTER);

		reset.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
		addHoverEffect(reset, "Reset", "Reset");
		run.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
		addHoverEffect(run, "Run", "Run");

		Label shortestPathtxt = new Label("Shortest Path :");
		shortestPathtxt.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #e6899b;");
		addHoverEffect(reset, "Reset", "Reset");
		shortestPathtxt.setPadding(new Insets(7));
		shortestPathtxt.setMinHeight(200);
		shortestPathtxt.setPadding(new Insets(7));

		pathTextArea = new TextArea();
		pathTextArea.setStyle("-fx-font-size: 16;" + "-fx-font-weight: Bold;" + "-fx-background-color: #f6f6f6;"
				+ "-fx-border-color: #e6899b;" + "-fx-border-width: 2;" + "-fx-text-fill: #e6899b;"
				+ "-fx-background-radius: 50;");

		pathTextArea.setMaxSize(200, 150);

		HBox h3 = new HBox(shortestPathtxt, pathTextArea);
		h3.setAlignment(Pos.TOP_LEFT);

		Label distancetxt = new Label("Distance :");
		distancetxt.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #e6899b;");
		distancetxt.setPadding(new Insets(7));
		TextField distanceText = new TextField();

		IconedTextFieled(distanceText);
		HBox h4 = new HBox(distancetxt, distanceText);
		h4.setAlignment(Pos.TOP_LEFT);

		VBox v = new VBox(30, hx, h1, h2, butBox);
		v.setAlignment(Pos.TOP_LEFT);
		v.setPadding(new Insets(10));

		VBox v1 = new VBox(30, h3, h4);
		v1.setAlignment(Pos.TOP_LEFT);
		v1.setPadding(new Insets(10));

		VBox mix = new VBox(10, v, v1);
		mix.setAlignment(Pos.TOP_LEFT);

		VBox Vmap = new VBox(pane2);
		Vmap.setAlignment(Pos.TOP_LEFT);

		HBox mainBox = new HBox(20, Vmap, mix);
		mainBox.setAlignment(Pos.TOP_LEFT);

		pane.setCenter(mainBox);

		run.setOnAction(e -> {
			vertex vertx1 = null;
			vertex vertx2 = null;
			String s1 = sourceComboBox.getValue();
			System.out.println(s1);
			String s2 = destinationComboBox.getValue();
			System.out.println(s2);

			for (int i = 0; i < Capitals.size(); i++) {
				if (Capitals.get(i).getCapital().getName().equals(s1)) {
					vertx1 = Capitals.get(i);
				}
				if (Capitals.get(i).getCapital().getName().equals(s2)) {
					vertx2 = Capitals.get(i);
				}
			}

			if (vertx1 != null && vertx2 != null) {
				int i = drawLine(Dijkstra(vertx1, vertx2));
				if (i == 0)
					distanceText.setText("0");
				else if (i == 1)
					distanceText.setText(String.valueOf(vertx2.distance));
			}

		});

		reset.setOnAction(l -> {
			pane2.getChildren().clear();
			destinationComboBox.getSelectionModel().select(null);
			sourceComboBox.getSelectionModel().select(null);
			distanceText.setText("");
			pathTextArea.clear();
			pointNumber = 0;

			chooseInMap
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

			chooseFromComboBox
					.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
							+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");

			pane2.getChildren().add(image);
			Image Image = new Image("locationBase.png");
			for (vertex cou : Capitals) {
				ImageView vi = new ImageView(Image);
				vi.setFitHeight(17);
				vi.setFitWidth(16);
				cou.getCapital().getRadioButton().setGraphic(vi);
				cou.getCapital().getRadioButton().setSelected(false);
				free();

			}

			for (int i = 0; i < Capitals.size(); i++) {
				Capitals.get(i).visited = false;
				Capitals.get(i).previous = null;
			}

			addPoint();

		});

		addPoint();

		return pane;
	}

	private void addHoverEffect(Button button, String s1, String s2) {
		button.setOnMouseEntered(e -> {
			button.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #e6899b;\n"
					+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #f6f6f6;");
			button.setText(s1);
		});

		button.setOnMouseExited(e -> {
			button.setStyle("-fx-font-size: 18;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
					+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
			button.setText(s2);
		});
	}

	private void addHoverEffectStart(Button button, String s1, String s2) {
		button.setOnMouseEntered(e -> {
			button.setStyle("-fx-font-size: 35;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #e6899b;\n"
					+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #f6f6f6;");
			button.setText(s1);
		});

		button.setOnMouseExited(e -> {
			button.setStyle("-fx-font-size: 35;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
					+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
			button.setText(s2);
		});
	}

	private int drawLine(vertex Destination) {
		if (Destination == null) {
			error.setContentText("No path");
			error.show();
			return 0;
		} else {
			StringBuilder sb = null;
			List<vertex> p = new ArrayList<>();
			for (vertex v = Destination; v != null; v = v.previous) {
				sb = new StringBuilder();
				sb.append("\n");
				sb.append("-->" + v.capital.getName() + " ");
				sb.append("\n");
				pathTextArea.appendText(sb.toString());
				System.out.print("-->" + v.capital.getName() + " ");
				p.add(v);
			}
			System.out.println();

			if (p.size() <= 1) {
				error.setContentText("No path");
				error.show();
			}

			for (int i = 0; i < p.size() - 1; i++) {
				vertex u = p.get(i);
				vertex v = p.get(i + 1);

				if (i != 0 && i != p.size() - 1) {
					ImageView vi0 = new ImageView(new Image("locationEx.png"));
					vi0.setFitHeight(16);
					vi0.setFitWidth(16);
					u.getCapital().getRadioButton().setGraphic(vi0);
				}

				Line line = new Line(u.capital.getX(), u.capital.getY(), v.capital.getX(), v.capital.getY());
				line.setStrokeWidth(2);
				line.setStyle("-fx-stroke: #ffffff;");
				pane2.getChildren().add(line);
			}
		}
		return 1;

	}

	private void addPoint() {
		for (int i = 0; i < Capitals.size(); i++) {
			RadioButton r = Capitals.get(i).getCapital().getRadioButton();
			r.setLayoutX(Capitals.get(i).getCapital().getX());
			r.setLayoutY(Capitals.get(i).getCapital().getY());
			pane2.getChildren().add(r);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void IconedTextFieled(javafx.scene.Node t) {
		t.setStyle("-fx-font-size: 16;\n" + "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-border-color: #e6899b;\n" + "-fx-border-width:  2;" + "-fx-text-fill: #e6899b;");
	}

	public static void lock() {
		try {
			for (int i = 0; i < Capitals.size(); i++) {
				Capitals.get(i).getCapital().getRadioButton().setDisable(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void free() {
		try {
			for (int i = 0; i < Capitals.size(); i++) {
				Capitals.get(i).getCapital().getRadioButton().setDisable(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public vertex Dijkstra(vertex source, vertex destination) {
		source.distance = 0;

		if (source == destination) {
			return null;
		}

		IyadHeap<vertex> heap = new IyadHeap<>();
		heap.add(source);

		while (!heap.isEmpty()) {
			vertex u = heap.remove();
			u.visited = true;

			if (u.getCapital().equals(destination.getCapital())) {
				break;
			}

			for (edges edge : u.getEdge()) {
				vertex v = edge.getDestination();
				if (!v.visited) {
					double weight = edge.weight;
					double distanceThroughU = u.distance + weight;
					if (distanceThroughU < v.distance) {
						v.distance = distanceThroughU;
						v.previous = u;
						heap.add(v);
					}
				}
			}
		}

		return destination;
	}

	public void readFile(File file) {
		try {
			Scanner sc = new Scanner(file);
			String[] l = sc.nextLine().split(":");
			int numCounter = Integer.parseInt(l[0]);
			int numEdge = Integer.parseInt(l[1]);
			int count = 0;
			int num = 0;

			while (count < numCounter - 1) {
				String line = sc.nextLine();
				System.out.println(line);
				vertex ver = new vertex(new Capital(line), num++);
				Capitals.add(ver);
				count++;

			}

			count = 0;
			while (count < numEdge) {
				String tokens[] = sc.nextLine().split(":");
				for (int i = 0; i < Capitals.size(); i++) {
					if (Capitals.get(i).getCapital().getName().compareToIgnoreCase(tokens[0]) == 0) {
						for (int j = 0; j < Capitals.size(); j++) {

							if (Capitals.get(j).getCapital().getName().compareToIgnoreCase(tokens[1]) == 0) {

								Capitals.get(i).e.add(new edges(Capitals.get(i), Capitals.get(j),
										Distance(Capitals.get(i), Capitals.get(j))));
							}

						}
					}
				}
				count++;
			}
			sc.close();
		} catch (FileNotFoundException t) {
			System.out.println(t);
		}
	}

	public double Distance(vertex a, vertex b) {

		final int EARTH_RADIUS = 6371;
		double lat1Rad = Math.toRadians(a.getCapital().getLatitude());
		double lat2Rad = Math.toRadians(b.getCapital().getLatitude());
		double deltaLat = Math.toRadians(b.getCapital().getLatitude() - a.getCapital().getLatitude());
		double deltaLon = Math.toRadians(b.getCapital().getLongitude() - a.getCapital().getLongitude());

		double dis = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(dis), Math.sqrt(1 - dis));

		return EARTH_RADIUS * c;

	}
}