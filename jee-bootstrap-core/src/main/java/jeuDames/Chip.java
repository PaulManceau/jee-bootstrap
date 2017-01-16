package jeuDames;

public class Chip {

	private CaseColor color;
	private int x;
	private int y;
	private boolean dame;
	
	public Chip (CaseColor color, int x, int y, boolean dame){
		this.color = color;
		this.x = x;
		this.y = y;
		this.dame = dame;
	}
	
	public CaseColor getColor() {
		return color;
	}
	public void setColor(CaseColor color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isDame() {
		return dame;
	}
	public void setDame(boolean dame) {
		this.dame = dame;
	}
	
	
}
