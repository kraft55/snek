
public class Start {
	public static void main(String[] args){
		Logic logic = new Logic(25,25, 10);
		try{
			logic.game();
		}catch(Exception e){System.out.println("Oh no");}
	}
}
