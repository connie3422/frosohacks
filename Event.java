import java.text.SimpleDateFormat;
import java.util.*;
import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class Event implements Comparable<Event>{
	private String event;
	private Calendar calendar;
	
	public Event (int year, int month, int day, int hour, int minute, String event) {
		this.event = event;
		calendar = new GregorianCalendar(year, month, day, hour, minute);
	}
	
	Calendar getCalendar() {
		return calendar;
	}
	
	String getEvent() {
		return event;
	}
	
	public int compareTo(Event other) {
		if (this.calendar.equals(other.calendar)) {
			return this.event.compareTo(other.event);
		} else {
			return this.calendar.compareTo(other.calendar);
		}
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");	

		return sdf.format(calendar.getTime()) + ": " + event;
	}
	
}
