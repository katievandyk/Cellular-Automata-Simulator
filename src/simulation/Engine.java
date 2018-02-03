package simulation;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;
import simulation.screen.SimulationScreen;
import simulation.screen.StartScreen;

/**
 * 
 * @author Benjamin Hodgson
 * @author Katherine Van Dyk
 * @author Michael Acker
 * @date 1/30/18
 *
 */
public class Engine {
	
	private final int FRAMES_PER_SECOND = 60;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final String DEFAULT_STYLESHEET = 
    		Engine.class.getClassLoader().getResource("default.css").toExternalForm();
    
    private final String PROGRAM_TITLE;   
    
    private Timeline PROGRAM_TIMELINE;
    private Stage PROGRAM_STAGE;
    private Scene PROGRAM_SCENE;
    private String SIMULATION_TYPE;
    private int GENERATION;
    
    private HashMap<String, Grid> GRIDS;
    private HashMap<String, Ruleset> RULES;

    // Give the program a title
	public Engine(String programTitle) {
		PROGRAM_TITLE = programTitle;
		GRIDS = null;
		RULES = null;
	}
	
	/**
	 * Initializes the animation time line and assigns instance variables
	 * 
	 * @param primaryStage: the Stage placed in the Application
	 */
	public void startProgram(Stage primaryStage, int width, int height) {
		PROGRAM_STAGE = primaryStage;
		PROGRAM_STAGE.setTitle(PROGRAM_TITLE);
		// attach "program loop" to time line to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        PROGRAM_TIMELINE = new Timeline();
        PROGRAM_TIMELINE.setCycleCount(Timeline.INDEFINITE);
        PROGRAM_TIMELINE.getKeyFrames().add(frame);
        playAnimation();	
        // attach a Scene to the primaryStage 
        generateStartScene(width, height);
	}
	
	/**
	 * 
	 * @param type: The type of simulation to start
	 */
	public void startSimulation(String type) {
		//System.out.println("Start simulation!");
		SIMULATION_TYPE = type;
		PROGRAM_STAGE.setTitle(SIMULATION_TYPE);
		Parent root = new SimulationScreen(this).getRoot();
		PROGRAM_SCENE.setRoot(root);
		playAnimation();
	}
	
	/**
	 * Performs one frame or step in the animation
	 */
	public void singleStep() {
		pauseAnimation();
		step(SECOND_DELAY);
	}
	
	/**
	 * Pauses the animation
	 */
	public void pauseAnimation() {
		PROGRAM_TIMELINE.pause();
	}
	
	/**
	 * Starts the animation
	 */
	public void playAnimation() {
		PROGRAM_TIMELINE.play();
	}
	
	/**
	 * 
	 * @return the Simulation titles to be displayed to the user
	 */
	public ObservableList<String> getSimulations() {
		ObservableList<String> retList = FXCollections.observableArrayList("a", "b", "c");
		return retList;
	}
	
	/**
	 * 
	 * @return PROGRAM_STAGE: the stage used by the application
	 */
	public Stage getProgramStage() {
		return PROGRAM_STAGE;
	}
	
	/**
	 * 
	 * @return SIMULATION_TYPE: the current simulation being animated 
	 */
	public String getSimulationType() {
		return SIMULATION_TYPE;
	}
	
	/**
	 * 
	 * @return GENERATION: the current generation number in the simulation
	 */
	public int getGeneration() {
		return GENERATION;
	}
	
	/**
	 * Sets grids
	 */
	public void setGrids(HashMap<String, Grid> grids) {
		GRIDS = grids;
	}
	
	/**
	 * Sets rules
	 */
	public void setRules(HashMap<String, Ruleset> rules) {
		RULES = rules;
	}

	/**
	 * Calls the Screen object to generate a start screen to display 
	 * to the user upon application start up. Assigns the instance variable
	 * @param PROGRAM_SCENE to allow for easy root changes to change scenes. 
	 */
	private void generateStartScene(int width, int height) {
		Parent root = new StartScreen(this).getRoot();
		PROGRAM_SCENE = new Scene(root, width, height);	
		PROGRAM_SCENE.getStylesheets().add(DEFAULT_STYLESHEET);
		PROGRAM_STAGE.setScene(PROGRAM_SCENE);
	}
	
	/**
	 * Change properties of shapes to animate them 
	 * 
	 * @param elapsedTime: time since last animation update
	 */
    private void step (double elapsedTime) {
    	//System.out.printf("Stepping!\n");
    }
	
}
