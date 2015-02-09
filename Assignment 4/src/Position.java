import java.util.List;

public final class Position {
	private final String path;
	private int frequency;
	private final List<Integer> pos;
	private String listofPos;
	
	public Position(String path) {
		this.frequency = 0;
		this.path = path;
		this.pos = null;
	}
	
	public Position(String path, int frequency, List<Integer> pos) {
		this.pos = pos;
		this.frequency = frequency;
		this.path = path;
	}
	
	public String getWord() {
		return path;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	
	public void addPosition(int n){
		pos.add(n);
	}
	
	public String getPositions() {
		listofPos = "";
		for (int i = 0; i<pos.size(); i++){
			String s = pos.get(i).toString();
			listofPos = listofPos + s ;
			int last = pos.size()-1;
			if(i != last){
				listofPos = listofPos + ", ";
			}
		}
		return listofPos;
	}
	
	public void incrementFrequency() {
		frequency++;
	}
	
	@Override
	public String toString() {
		return path + " : " + frequency + " : " + listofPos;
	}
}
