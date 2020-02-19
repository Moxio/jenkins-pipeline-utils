Boolean call(scm) {
	if (scm instanceof hudson.scm.SubversionSCM) {
        return false
    } else {
        return scm.userRemoteConfigs.any { it.url ==~ /git@gitlab\.com:.*/ }
    }
}
