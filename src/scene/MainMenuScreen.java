package scene;

import gameLogic.GameManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainMenuScreen extends Pane {
	private static final Font MODE_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("south park.ttf"), 80);
	private static final Font CHA_INFO_FONT = Font
			.loadFont(ClassLoader.getSystemResourceAsStream("Inconsolata-Regular.ttf"), 18);
	private static final Font CHA_NAME_FONT = Font
			.loadFont(ClassLoader.getSystemResourceAsStream("Inconsolata-Bold.ttf"), 20);
	private Canvas knightCanvas, spellCasterCanvas, clownCanvas, priestCanvas, farm, boss;
	private Canvas opacityChaCanvas, opacityFarmCanvas, opacityBossCanvas;

	private int width = SceneManager.SCENE_WIDTH;
	private int height = SceneManager.SCENE_HEIGHT;
	private Image bg, img;
	private ImageView ivBg;

	public MainMenuScreen() {
		super();

		bg = new Image("bg22.png");
		ivBg = new ImageView(bg);

		ivBg.setFitWidth(width);
		ivBg.setFitHeight(height);
		ivBg.setTranslateX(0);
		ivBg.setTranslateY(0);

		opacityChaCanvas = draw("BoardCha", width, 150, 0, 0);

		opacityFarmCanvas = draw("BoardFarm", width / 3, width * 2 / 5, width / 8, width / 4);
		opacityBossCanvas = draw("BoardFarm", width / 3, width * 2 / 5, width - width / 8 - width / 3, width / 4);

		knightCanvas = draw("Knight", width / 4, 150, 0, 0);
		spellCasterCanvas = draw("SpellCaster", width / 4, 150, width / 4, 0);
		clownCanvas = draw("Clown", width / 4, 150, width / 2, 0);
		priestCanvas = draw("Priest", width / 4, 150, width * 3 / 4, 0);

		farm = draw("FARM", width / 3, width * 2 / 5, width / 8, width / 4);
		addCanvasEvents(farm, "FARM");

		boss = draw("BOSS", width / 3, width * 2 / 5, width - width / 8 - width / 3, width / 4);
		addCanvasEvents(boss, "BOSS");

		getChildren().addAll(ivBg, opacityChaCanvas, opacityFarmCanvas, opacityBossCanvas, knightCanvas,
				spellCasterCanvas, clownCanvas, priestCanvas, farm, boss);

	}

	private Canvas draw(String name, double width, double height, int posX, int posY) {

		Canvas btn = new Canvas(width, height);
		GraphicsContext gc = btn.getGraphicsContext2D();

		btn.setTranslateX(posX);
		btn.setTranslateY(posY);

		if (name == "BoardFarm" || name == "BoardBoss") {
			gc.setFill(Color.CORAL.desaturate());
			gc.fillRoundRect(5, 5, width - 10, height - 10, 50, 50);
			btn.setOpacity(0.5);

		}

		else if (name == "FARM" || name == "BOSS") {
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(5);
			gc.strokeRoundRect(5, 5, width - 10, height - 10, 50, 50);
			gc.setFont(MODE_FONT);
			gc.setFill(Color.BLACK);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.CENTER);
			gc.fillText(name, width / 2, height / 2);

		} else if (name == "BoardCha") {
			gc.setFill(Color.BISQUE);
			gc.fillRect(0, 0, width, height);

		}

		else {

			if (name == "Knight")
				img = new Image("Knight1_head.png");
			else if (name == "SpellCaster")
				img = new Image("Spellcaster1_head.png");
			else if (name == "Clown")
				img = new Image("Clown1_head.png");
			else
				img = new Image("Priest1_head.png");

			gc.drawImage(img, 10, 45, 90, 90);

			gc.setStroke(Color.BLACK);
			gc.setLineWidth(7);
			gc.strokeLine(0, 0, width, 0);
			gc.strokeLine(0, 0, 0, height);
			gc.strokeLine(0, height, width, height);
			if (name == "Priest") {
				gc.strokeLine(width, 0, width, height);
			}

			gc.setFont(CHA_NAME_FONT);
			gc.setFill(Color.BLACK);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.CENTER);
			gc.fillText(name, width / 2, height / 8 + 5);
			gc.setTextAlign(TextAlignment.LEFT);
			gc.setFont(CHA_INFO_FONT);
			if (name == "Knight") {
				gc.fillText("Level: " + GameManager.getKnight().getLevel() + '\n' + "Atk: "
						+ String.format("%.1f", GameManager.getKnight().getAtk()) + '\n' + "Exp: "
						+ GameManager.getKnight().getCurrentExp() + '\n', width * 6 / 10 - 20, height * 2 / 3);
			} else if (name == "SpellCaster") {
				gc.fillText(
						"Level: " + GameManager.getSpellCaster().getLevel() + '\n' + "Atk: "
								+ String.format("%.1f", GameManager.getSpellCaster().getAtk()) + '\n' + "Exp: "
								+ GameManager.getSpellCaster().getCurrentExp() + '\n',
						width * 6 / 10 - 20, height * 2 / 3);
			} else if (name == "Clown") {
				gc.fillText("Level: " + GameManager.getClown().getLevel() + '\n' + "Atk: "
						+ String.format("%.1f", GameManager.getClown().getAtk()) + '\n' + "Exp: "
						+ GameManager.getClown().getCurrentExp() + '\n', width * 6 / 10 - 20, height * 2 / 3);
			} else {
				gc.fillText("Level: " + GameManager.getPriest().getLevel() + '\n' + "Atk: "
						+ String.format("%.1f", GameManager.getPriest().getAtk()) + '\n' + "Exp: "
						+ GameManager.getPriest().getCurrentExp() + '\n', width * 6 / 10 - 20, height * 2 / 3);
			}
		}

		return btn;
	}

	public void drawHoverIndicator(Canvas canvas, String name) {
		// TODO Fill Code
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.CORAL);
		gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 50, 50);

	}

	public void undrawHoverIndicator(Canvas canvas, String name) {
		// TODO Fill Code
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.CORAL.desaturate());
		gc.fillRoundRect(5, 5, canvas.getWidth() - 10, canvas.getHeight() - 10, 50, 50);

	}

	private void addCanvasEvents(Canvas canvas, String canvasName) {
		canvas.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (canvasName == "FARM") {
					GameManager.setCurrentMode("Farm");
					GameManager.setIsGameFinished(false);
					Pane chaselect = new CharacterSelectScreen();
					SceneManager.gotoSceneOf(chaselect);
				} else if (canvasName == "BOSS") {
					GamePlayScreen.isCreated(false);
					GameManager.setCurrentMode("Boss");
					GameManager.setIsGameFinished(false);
					GameManager.getCurrentMon().setAlreadyDead(false);
					GamePlayScreen gamePlayScreen = new GamePlayScreen();
					new Thread(() -> {
						try {
							SceneManager.getFadeIn().join();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						(gamePlayScreen).start();
					}).start();
					SceneManager.gotoSceneOf(gamePlayScreen);
				}

			}
		});

		canvas.setOnMouseEntered(new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (canvasName == "FARM")
					drawHoverIndicator(opacityFarmCanvas, canvasName);
				else {
					drawHoverIndicator(opacityBossCanvas, canvasName);
				}

			}
		});

		canvas.setOnMouseExited(new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub

				if (canvasName == "FARM")
					undrawHoverIndicator(opacityFarmCanvas, canvasName);
				else {
					undrawHoverIndicator(opacityBossCanvas, canvasName);
				}

			}

		});
	}
}