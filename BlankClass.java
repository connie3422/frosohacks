
/*
 * File: BlankClass.java
 * ---------------------
 * This implements the graphics for our program.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import acmx.export.javax.swing.*;

public class BlankClass extends GraphicsProgram {
	
	private static final double NUM_HORIZONTAL_LINES = 9;
	private static final double NUM_VERTICAL_LINES = 14;
	private static final int PERSON_DIAMETER = 40;
	private static final int PERSON_RADIUS = 20;
	private static final double MOVE_AMOUNT = 5;
	
	public void run() {
		Database a = new Database();
		Event e = new Event(2016, 2, 23, 12, 34, "event");
		a.add(e);
		System.out.println(a);
		
		addMouseListeners();
		addKeyListeners();
		//welcome = new JLabel("Welcome to FroSoCo. (Excuse the dim lighting.)");
		//add(welcome, SOUTH);
		//remove(welcome);
		text = new JTextField(50);
		text.addActionListener(this);
		//add(new JLabel("Event"), SOUTH);
		add(text, SOUTH);
		createScene();
	}
	
	private void createScene() {
		createBackground();
		createCharacter();
		while(true) {
			createCharacterInteractions();
		}
	}
	
	// The created background would most likely resemble a hallway scene, but for the sake of
	// checking functionality, some of the doors will be message boards.
	private void createBackground() {
		setBackground(Color.DARK_GRAY);
		for(int i = 0; i <= NUM_VERTICAL_LINES; i++) {
			GLine line = new GLine((getWidth()/NUM_VERTICAL_LINES) * i, 0, 
					(getWidth()/NUM_VERTICAL_LINES) * i, getHeight());
			line.setColor(Color.WHITE);
			//add(line);
		}
		for(int i = 0; i <= NUM_HORIZONTAL_LINES; i++) {
			GLine line = new GLine(0, getHeight()/NUM_HORIZONTAL_LINES * i, 
					getWidth(), getHeight()/NUM_HORIZONTAL_LINES * i);
			line.setColor(Color.WHITE);
			//add(line);
		}
		for(int i = 0; i < NUM_VERTICAL_LINES; i++) {
			double x = i * (getWidth()/NUM_VERTICAL_LINES);
			double y = 0;
			drawColoredBox(x,y,i);
			y = getHeight() - 1.5 * (getHeight()/NUM_HORIZONTAL_LINES);
			drawColoredBox(x,y,i);
		}
		createBulletin();
		createDoor();
	}
	
	// The character is temporarily represented by a black circle.
	private void createCharacter() {
		double x = (getWidth()/NUM_VERTICAL_LINES)/2 - PERSON_RADIUS;
		double y = getHeight()/2 - PERSON_RADIUS;
		person = new GOval(x, y, PERSON_DIAMETER, PERSON_DIAMETER);
		person.setFilled(true);
		add(person);
	}
	
	// When the user reaches a message board, a menu will prompt the user if he/she would like to view
	// or add events. When the user reaches a person's door, his/her status message will appear. If the
	// user reaches a discussion board, a menu will prompt the user to he/she would like to view
	// discussion sessions or create one. This method will require the use of the Database class.
	private void createCharacterInteractions() {
		GObject collided = getCollidingObject(person.getX(), person.getY());
		if(collided != bulletin) {
			collided = getCollidingObject(person.getX() + PERSON_DIAMETER, person.getY());
		}
		if(collided != bulletin) {
			collided = getCollidingObject(person.getX(), person.getY() + PERSON_DIAMETER);
		}
		if(collided != bulletin) {
			collided = getCollidingObject(person.getX() + PERSON_DIAMETER, person.getY() + PERSON_DIAMETER);
		}
		if(collided == bulletin) {
			pause(30);
			removeAll();
			openBulletin();
		}		
	}
		
	public void openBulletin() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//Input year, month, day, hour, minute, (ints) event name (string)
	}
	
	private GObject getCollidingObject(double x, double y) {
		GObject collided = getElementAt(x, y);
		return collided;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:	
				if(person.getY() > (getHeight()/NUM_HORIZONTAL_LINES)) {
					person.move(0, -MOVE_AMOUNT); break;
				}
			case KeyEvent.VK_DOWN:	
				if(person.getY() < getHeight() - PERSON_DIAMETER - getHeight()/NUM_HORIZONTAL_LINES) {
					person.move(0, MOVE_AMOUNT); break;
				}
			case KeyEvent.VK_LEFT:	person.move(-MOVE_AMOUNT, 0); break;
			case KeyEvent.VK_RIGHT:	person.move(MOVE_AMOUNT, 0); break;
		}
	}
	
	private void drawColoredBox(double x, double y, int i) {
		GRect box = new GRect(x, y, getWidth()/NUM_VERTICAL_LINES, getHeight()/NUM_HORIZONTAL_LINES);
		box.setFilled(true);
		box.setFillColor(Color.BLACK);
		box.setColor(Color.WHITE);
		if((i > 1 && i < 4) || (i > 9 && i < 12)) {
			box.setFillColor(Color.GRAY);
		}
		add(box);
	}
	
	private void createDoor() {
		door = new GRect(2 * getWidth()/NUM_VERTICAL_LINES, 0, 2 * (getWidth()/NUM_VERTICAL_LINES), 
				getHeight()/NUM_HORIZONTAL_LINES);
		door.setFilled(true);
		door.setFillColor(Color.GREEN);
		door.setColor(Color.WHITE);
		add(door);
	}
	// When the user goes to a door, the information of the person who lives there will appear.
	
	private void createBulletin() {
		bulletin = new GRect(2 * getWidth()/NUM_VERTICAL_LINES, getHeight() - 1.5 * (getHeight()/NUM_HORIZONTAL_LINES), 
				2 * (getWidth()/NUM_VERTICAL_LINES), getHeight()/NUM_HORIZONTAL_LINES);
		bulletin.setFilled(true);
		bulletin.setFillColor(Color.ORANGE);
		bulletin.setColor(Color.WHITE);
		add(bulletin);
	}
	
	private JButton enterButton;
	private JLabel welcome;
	private JLabel event;
	private JTextField text;
	private GOval person;
	private GRect bulletin;
	private GRect door;
}


