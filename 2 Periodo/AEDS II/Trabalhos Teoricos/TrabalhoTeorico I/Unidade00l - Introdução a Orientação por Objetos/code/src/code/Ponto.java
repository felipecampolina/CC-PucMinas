package code;

public class Ponto {
	private double x , y;
	private int id,nextID = 0;
	private Scanner sc = new Scanner (System.in);
	
	public Ponto() {
		x = 0;
		y = 0;
		id = nextID;
		nextID++;
	}
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = nextID;
		nextID++;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}

}
class LixaoPonto extends Ponto {
	private double dist;
	
	 public static void main (String[] args){
	 Ponto p1 = new Ponto(4,3);
	 Ponto p2 = new Ponto(8,5);
	 Ponto p3 = new Ponto(9.2,10);
	 System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
	 System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5,2));
	 System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4,3,5,2));
	 System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1,p2,p3));
	 System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
	 System.out.println("ID de P1: " + p1.getID());
	 System.out.println("ID de P2: " + p2.getID());
	 System.out.println("ID de P3: " + p3.getID());
	 System.out.println("Next ID: " + Ponto.getNextID());
	 }
	}
