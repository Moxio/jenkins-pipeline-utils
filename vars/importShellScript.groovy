@NonCPS
String createTempLocation(String path) {
	String tmpDir = pwd tmp: true
	return tmpDir + File.separator + new File(path).getName()
}

def call(String scriptPath) {
	destPath = createTempLocation(scriptPath)
	sh "[ -f '${destPath}' ] && rm '${destPath}' || true"
	writeFile file: destPath, text: libraryResource(scriptPath)
	sh "chmod +x '${destPath}'"
	return destPath
}
