package View_Drawing;

import Scenes.NewGame;

import java.io.File;

import Scenes.GameSetup;
import Scenes.MainMenu;
import Scenes.ScreenScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Graphics extends Application {
	private ScreenScene currentScene;
	private int currentState;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		currentState = 1;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Six Men's Morris");
		currentScene = new MainMenu();
		primaryStage.setScene(currentScene.getScreen());
		primaryStage.show();
		animate(primaryStage);
	}

	private void animate(Stage primaryStage) {
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				currentScene.animate();
				currentState = currentScene.getState();
				switch (currentState) {
				case 0: // DEFAULT STAGE
					currentScene.animate();
					break;
				case 1: // STARTS NEW GAME
					currentScene = new NewGame();
					primaryStage.setScene(currentScene.getScreen());
					primaryStage.show();
					currentState = 0;
					currentScene.animate();
					break;
				case 2: // LOADS GAME FROM FILE CHOOSER
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Resource File");
					fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Six Men Morris File", (".smm")));
					Stage nS = new Stage();
					File file = fileChooser.showOpenDialog(nS);
					if (file == null) {
						nS.close();
						currentScene.setState(0);
						currentState = 0;

					} else {
						currentScene = new NewGame(file);
						nS.close();
						primaryStage.setScene(currentScene.getScreen());
						primaryStage.show();
						currentState = 0;
					}
					break;
				case 3:
					currentScene = new GameSetup();
					primaryStage.setScene(currentScene.getScreen());
					primaryStage.show();
					currentState = 0;
					currentScene.animate();
					break;
				case 4:
					stop();
					primaryStage.close();
					break;
				case 5:
				case 6:
				case 7:
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 12:
					currentScene = new MainMenu();
					primaryStage.setScene(currentScene.getScreen());
					primaryStage.show();
					currentState = 0;
					break;
				case 13:
					FileChooser fileChooser2 = new FileChooser();
					fileChooser2.setTitle("Save Resource File");
					fileChooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter("Six Men Morris File", (".smm")));
					Stage nS2 = new Stage();
					File file2 = fileChooser2.showSaveDialog(nS2);
					if (file2 == null) {
						nS2.close();
						currentScene.saveFile(file2);
						currentScene.setState(12);
						currentState = 12;

					} else {
						nS2.close();
						currentScene.saveFile(file2);
						primaryStage.setScene(currentScene.getScreen());
						primaryStage.show();
						currentState = 12;
					}
					currentScene.saveFile(file2);
				}

			}
		}.start();
	}

}