/**
 * 
 */
package cz.cuni.mff.d3s.karel.core.api.impl;

import cz.cuni.mff.d3s.karel.core.api.ExecutionEngine;
import cz.cuni.mff.d3s.karel.core.api.Karel;
import cz.cuni.mff.d3s.karel.core.api.KarelProgram;

/**
 * Simple Karel program execution engine.
 * 
 * @author Michal Malohlava
 *
 */
public class SimpleExecutionEngine<K extends Karel> implements ExecutionEngine<K> {
	
	public static <K extends Karel> SimpleExecutionEngine<K> getInstance() {
		return new SimpleExecutionEngine<K>();
	}

	private int boardWidth;
	private int boardHeight;
	
	private K karelTheRobot;
	
	private SimpleExecutionEngine() {		
	}
	
	@Override
	public void setBoardSize(int x, int y) {
		this.boardWidth = x;
		this.boardHeight = y;
	}	
	
	@Override
	public K getKarel() {
		return this.karelTheRobot;
	}
	
	@Override
	public void setKarel(K karel) {
		this.karelTheRobot = karel;
	}
	
	@Override
	public void execute(KarelProgram<K> program) {
		program.execute(this);		
	}	
}
