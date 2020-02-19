void call(scm) {
	if (scm instanceof hudson.scm.SubversionSCM) {
		checkout(changelog: true, scm: [$class: 'SubversionSCM', locations: scm.locations as java.util.List<hudson.scm.SubversionSCM$ModuleLocation>, workspaceUpdater: [$class: 'UpdateWithCleanUpdater']])
	} else {
		if (fileExists('.git') && fileExists('.git/hooks')) {
			// Cleanup git hooks as part of a clean checkout
			sh 'find .git/hooks -type f ! -name "*.sample" -delete'
		}

		checkout(changelog: true, scm: [$class: 'GitSCM', extensions: scm.extensions + [[$class: 'CleanCheckout'], [$class: 'LocalBranch', localBranch: "**"], [$class: 'CloneOption', noTags: false]], userRemoteConfigs: scm.userRemoteConfigs, branches: scm.branches])
	}
}
