import java.util.ArrayList;
import java.util.List;

public final class Position {
	private final String path;
	//private final String word;
	private int frequency;
	private final List<Integer> pos;
	private String listofPos;
	
	public Position(String path) {
		this.frequency = 0;
		this.path = path;
		this.pos = new ArrayList<Integer>();
		//this.word = word;
	}
	
	public Position(String path, int frequency, List<Integer> pos) {
		this.pos = pos;
		this.frequency = frequency;
		this.path = path;
		//this.word = word;
	}
	
	//public String getWord() {
	//	return word;
	//}
	
	public String getPath() {
		return path;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	
	public void addPosition(int n){
		pos.add(n);
	}
	
	public String getPositions() {
		String list = pos.toString().replace("[", "");
		list = list.replace("]", "");
		return list;
	}
	
	public void incrementFrequency() {
		frequency++;
	}
	
	@Override
	public String toString() {
		return path + " : " + frequency + " : " + getPositions();
	}
}
