//import java.io.*;
//
//public class UserInfoFile{
//	public File userFile;
//	public String name;
//	private String path;
//	
//	public UserInfoFile(String path, String name) {
//		this.name = name;
//		path = System.getProperty("java.class.path");
//		path();
//		userFile = new File(path+name+".txt");
//		System.out.println(System.getProperty("user.dir")+"\\personalFile\\"+name+".txt");
//		
//	}
//	
//	private void path() {
//		int a = path.indexOf("\\.metadata");
//		if (path.contains(".metadata")) {
////			String path = "C:\\Users\\28743\\Eclipse_workspace_EE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\beatGoogle_DataStructure\\";
//			path = (path.substring(0, path.indexOf(".metadata"))+"beatGoogle_DataStructure\\personalFile\\");
//		}
//	}
//}