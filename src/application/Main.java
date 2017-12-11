package application;

import gameLogic.MusicControl;
import javafx.application.Application;
import javafx.stage.Stage;
import sharedObject.ThreadHolder;
import window.SceneManager;

public class Main extends Application {

	private static MusicControl musicControl;

	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			primaryStage.setTitle("GAME");
			primaryStage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {

		musicControl.end();
		SceneManager.getBGM().stop();
		for (int i = 0; i < ThreadHolder.threads.size(); i++) {
			ThreadHolder.threads.get(i).interrupt();
		}
	}
}
