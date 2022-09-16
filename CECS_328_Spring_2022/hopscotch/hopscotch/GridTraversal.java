package hopscotch;

public class GridTraversal {
	
	

	//returns the new coordinate pair associated with the given direction 
	//0 is 1 unit to the right
	//1 is 1 unit up and 1 unit to the right
	//-1 is 1 unit down and 1 unit to the right
	public static Pair<Integer,Integer> getNewCoords(Pair<Integer,Integer> oldCoords, int m,
			int n, int command){
		
		Pair<Integer, Integer> newCoords = new Pair<Integer,Integer>();
		
		//All directions move right if possible.
		if (oldCoords.second==n-1) {
			newCoords.second=0;
		}
		else {
			newCoords.second=oldCoords.second+1;
		}
		
		
		
		if (command == 0) {
			newCoords.first=oldCoords.first;
		}
		
		else if (command == 1) {
			if (oldCoords.first==0) {
				newCoords.first=m-1;
			}
			else {
				newCoords.first=oldCoords.first-1;
			}
		}
		else {
			if (oldCoords.first==m-1) {
				newCoords.first=0;
			}
			else {
				newCoords.first=oldCoords.first+1;
			}
		}
		
		return newCoords;
	}
}
