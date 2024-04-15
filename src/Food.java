public class Food extends Square{
	public Food(int x, int y){
		setX((int)(1+Math.random()*(x-2)));	//Random x,y values for the food
		setY((int)(1+Math.random()*(y-2)));

	}
}
