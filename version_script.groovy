def project =  binding.getVariable("manager").build.project
def projectPath = manager.build.getEnvironment(manager.listener)['PROJECT_PATH']
def propertiesFile = project.getWorkspace().child(projectPath+"/gradle.properties");

Scanner lineScanner = new Scanner(propertiesFile.readToString())
String line
String versionStr
while (lineScanner.hasNextLine()){
	line=lineScanner.nextLine()
	if (line.contains("version")){
		versionStr=line.replace("version","v")
	}
}

manager.addShortText("$versionStr")
