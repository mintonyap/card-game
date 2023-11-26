public class GUIGameModule {

private Menu menu;
	
	public GUIGameModule(){
		menu = new Menu();
	}
	
	public void run(){
		menu.run();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIGameModule().run();
	}
	
}
