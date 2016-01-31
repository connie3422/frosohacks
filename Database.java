/*
 * 
 */

import java.util.*;

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class Database {
	private PriorityQueue<Event> q;


	public Database() {
		q = new PriorityQueue<Event>();
	}
	
	void add(Event event) {
		q.add(event);
	}
	
	Event getEvent(int value) { // doesnt work probably
		if(value > q.size() || value < 0) {
			//error
		}
		Object[] array = q.toArray();
		Event event = (Event) array[value - 1]; 
		return event;
	}
	
	void remove(int value) {
		Event event = getEvent(value);
		q.remove(event);
	}
	
	void edit(int value, Event a) {
		Event event = getEvent(value);
		if (q.contains(event)) {
			q.remove(event);
			q.add(a);
		} else {
			q.add(a);
			//print error item doesnt exist
		}
		System.out.println("asdf");
	}
	
	void removeWithTime() {	
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -1);	
		if (!q.isEmpty()) {
			while (q.peek().getCalendar().compareTo(now) < 0) { 
				if (q.peek() != null){
					q.poll();
				}
			}
		}
	}
	
	public String toString() {
		removeWithTime();
		Object[] array = q.toArray();
		String s;
		if (array.length > 0) {
			s = "1" + ". " + array[0] + "\n";
			for (int i = 1; i < array.length; i++) {
				s += (i + 1) + ". " + array[i] + "\n";  
			}
		} else {
			s = "no events :(";
		}
		return s;
	}
	
	
	
}
