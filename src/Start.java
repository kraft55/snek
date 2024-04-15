
public class Start {
	public static void main(String[] args){
		Logic logic = new Logic(20,20, 10);
		try{
			logic.game();
		}catch(Exception e){System.out.println("Oh no");}
	}
}
