package gameLogic;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Note {
	private Integer type;
	private KeyCode direction;
	private Double startTime;
	private Canvas canvas;
	private Image image;

	public Note(Integer type, KeyCode direction) {

		if (direction == KeyCode.UP)
			this.image = GameManager.getnotesImages().get(0);
		if (direction == KeyCode.DOWN)
			this.image = GameManager.getnotesImages().get(1);
		if (direction == KeyCode.LEFT)
			this.image = GameManager.getnotesImages().get(2);
		if (direction == KeyCode.RIGHT)
			this.image = GameManager.getnotesImages().get(3);

		this.type = type;
		this.direction = direction;
		this.canvas = new Canvas(70, 70);
		this.canvas.getGraphicsContext2D().drawImage(this.image, 0, 0, 70, 70);
		this.canvas.setTranslateY(472);
		this.canvas.setTranslateX(-100);

	}

	public Double getStartTime() {
		return startTime;
	}

	public void setStartTime(Double startTime) {
		this.startTime = startTime;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public KeyCode getDirection() {
		return direction;
	}

	public Integer getType() {
		return type;
	}

	public void setDirection(KeyCode e) {
		direction = e;
		if (direction == KeyCode.UP)
			this.image = GameManager.getnotesImages().get(0);
		if (direction == KeyCode.DOWN)
			this.image = GameManager.getnotesImages().get(1);
		if (direction == KeyCode.LEFT)
			this.image = GameManager.getnotesImages().get(2);
		if (direction == KeyCode.RIGHT)
			this.image = GameManager.getnotesImages().get(3);
		this.canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		this.canvas = new Canvas(70, 70);
		this.canvas.getGraphicsContext2D().drawImage(this.image, 0, 0, 70, 70);
		this.canvas.setTranslateY(472);
		this.canvas.setTranslateX(-100);
	}

}
