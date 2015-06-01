import java.util.ArrayList;


public class Data {
	private ArrayList<String> datas = new ArrayList<String>();
	
	public Data (String data) {
		String dataseg[] = data.split(",");
		for (int i = 0; i < dataseg.length; i++){
			datas.add(dataseg[i]);
		}
	}
}
