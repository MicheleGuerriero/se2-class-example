package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author micheleguerriero
 */
@Stateful
public class ListElement {

	List<Integer> values = new ArrayList<Integer>();

	public void addElement(int i) {
		values.add(i);
	}

	public void removeElement(int i) {
		values.remove(new Integer(i));
	}

	public List<Integer> getElements() {
		return values;
	}
	
	public Integer sumElements() {
		
		Integer sum = new Integer(0);
		for (int value : values) {
			sum = sum + value;
		}	
		
		return sum;
	}
}
