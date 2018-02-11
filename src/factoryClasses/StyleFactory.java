package factoryClasses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import configuration.datatemplates.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simulation.Engine;
import simulation.ruleSet.FireRuleset;
import simulation.ruleSet.GameOfLifeRuleset;
import simulation.ruleSet.SegregationRuleset;
import simulation.ruleSet.WaTorRuleset;

/**
 * Grid styling choices
 * 
 * @author Katherine Van Dyk
 *
 */
public class StyleFactory extends Engine {
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";

    public List<String> getParameters(String simType){
	if(simType.equals(FIRE)) {
	    FireXMLData fire = new FireXMLData();
	    return fire.getParameterFields();
	}
	else if(simType.equals(WATOR)) {
	    WaTorXMLData wator = new WaTorXMLData();
	    return wator.getParameterFields();
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationXMLData segregation = new SegregationXMLData();
	    return segregation.getParameterFields();
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    FireXMLData gol = new FireXMLData();
	    return gol.getParameterFields();
	}
	return null;
    }

    /**
     * Returns list of ways for grid edges to be handled
     */
    public ObservableList<String> getEdgeHandling(){
	List<String> EDGE_HANDLING_FIELDS = Arrays.asList(new String[] {
		"finite",
		"toroidal"
	});
	ArrayList<String> options = new ArrayList<>();
	options.addAll(EDGE_HANDLING_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }

    /**
     * Returns list of different shapes
     */
    public ObservableList<String> getShapes(){
	List<String> SHAPE_FIELDS = Arrays.asList(new String[] {
		"rectangle",
		"triangle"
	});
	ArrayList<String> options = new ArrayList<>();
	options.addAll(SHAPE_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }

    public void setParameter(Engine engine, String param, double newVal){
	String simType = engine.currentGrid().getType();
	if(simType.equals(FIRE)) {
	    FireRuleset fr = ((FireRuleset) engine.currentRules());
	    fr.setProbCatch(newVal);
	}
	else if(simType.equals(WATOR)) {
	    WaTorRuleset wr = ((WaTorRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		wr.setFishBreedTime((int) newVal);
	    }
	    else if(param.equals(parameters.get(1))) {
		wr.setFishInitEnergy((int) newVal);
	    }
	    else if(param.equals(parameters.get(2))) {
		wr.setSharkInitEnergy((int) newVal);
	    }
	    else if(param.equals(parameters.get(3))){
		wr.setSharkBreedEnergy((int) newVal);
	    }		
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationRuleset sr = ((SegregationRuleset) engine.currentRules());
	    sr.setTolerance(newVal);
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    GameOfLifeRuleset gr = ((GameOfLifeRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		gr.setMinLife((int) newVal);
	    }
	    else if(param.equals(parameters.get(2))) {
		gr.setMaxLife((int) newVal);
	    }
	    else if(param.equals(parameters.get(3))){
		gr.setBirth((int) newVal);
	    }		
	}
    }
}
