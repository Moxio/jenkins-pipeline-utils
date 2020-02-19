Boolean call(String path) {
	return sh(script: "[ -d '${path}' ]", returnStatus: true) == 0
}
