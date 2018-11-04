package starter;

public class Space {
	
	public static void main(String[] args) {

	}

		private int row;
		private int col;
		
		public Space(int row, int col) {
			this.col = col;	
			this.row = row;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public void setCol(int col) {
			this.col = col;
		}
		
		public String toString() {
			return "r" + row + "c" + col;
		}
}
