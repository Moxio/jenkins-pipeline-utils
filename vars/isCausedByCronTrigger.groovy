@NonCPS
Boolean call() {
	return currentBuild.rawBuild.getCause(hudson.triggers.TimerTrigger$TimerTriggerCause) != null
}
