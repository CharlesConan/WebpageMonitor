
public class ControlThread extends Thread {
	
	private int threadType;
	private String textPane1;
	private String textPane2;
	Monitor monitor;
	//GUIMain guiMain;
	
	public ControlThread(int type, String text1, String text2){
		//guiMain = new GUIMain();
		this.threadType=type;
		textPane1=text1;
		textPane2=text2;
		monitor = new Monitor();
		System.out.println(this.getId());
	}
	
	public String getOutput(){
		return monitor.output;		
	}
	
	public void run(){
		switch (threadType) {
			case 1:
				monitor.setTargetURL(textPane1);
		        monitor.setSearchContent(textPane2);
		        monitor.readSourceCode(null);	
		        break;
			case 2:
				monitor.setTargetURL(textPane1);
		        monitor.setSearchContent("variant");    
		        monitor.readSourceCode(null);	
		        break;
		}
		this.interrupt();
	}

}
