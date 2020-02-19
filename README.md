Moxio Jenkins pipeline utils
============================
This project is a [Shared Library](https://jenkins.io/doc/book/pipeline/shared-libraries/)
with common utility functions for building your own Jenkins pipelines.

Usage
-----
There are two possible ways to reference this library in your own pipeline:

1. Using a direct reference to this library on GitHub. Add the following annotation
    to the top of your `Jenkinsfile`:
    ```groovy
    @Library('github.com/moxio/jenkins-pipeline-utils@master') _
    ```
    Please note the trailing underscore! Instead of referencing master you can (and
    actually should) fix the dependency on a specific tag or commit hash.
2. By adding this library as a global pipeline library to your Jenkins instance.
    Go to `Manage Jenkins > Configure System > Global Pipeline Libraries` and add
    this library using a reference to its GitHub repository and a name of your choice
    (e.g. `moxio-pipeline-utils`). You can then either check `Load implicitly` to
    make the library available by default, or reference it explicitly by its name at
    the top of your `Jenkinsfile`:
    ```groovy
    @Library('moxio-pipeline-utils') _
    ```
    Again, note the trailing underscore.

Functions
---------
The library defines the following functions:

### cleanCheckout
Checks out code from the SCM and ensures a clean working copy state: any local
modifications are rolled back and unversioned files are deleted. Supports both
subversion and git. In case of a git repository, any git hooks configured in the
local checkout are cleaned up as well.

You can use the function by calling `cleanCheckout scm` as a drop-in replacement
for the default `checkout scm` step. You probably want to use `skipDefaultCheckout()`
in your pipeline `options {}` block to prevent Jenkins from already checking out
the code using the 'default' `checkout` by itself.

### dirExists
Alternative for the built-in `fileExists` that only returns true if the path
actually represents a directory.

### importShellScript
In your own shared pipeline library, this function gets a shell script from the
`resources/` directory, makes it available and executable locally, and returns
a path by which it can be executed. In essence this is a variant of [`libraryResource`](https://jenkins.io/doc/pipeline/steps/workflow-cps-global-lib/#libraryresource-load-a-resource-file-from-a-shared-library)
more suitable for shell scripts.

### isCausedByCronTrigger
Returns whether the current build was started by a cron trigger. This is useful
for pipelines that need to perform different steps for builds triggered by SCM
events (e.g. only 'light' unit tests) than for nightly builds triggered by cron
(e.g. also 'heavy' integration tests).

### isGitlabRepository
Returns whether the given SCM corresponds to a repository hosted on `gitlab.com`.
This can be useful if you want to share a default pipeline between Gitlab- and
non-Gitlab-repositories, but still update the Gitlab commit status if relevant, e.g.:
```groovy
if (isGitlabRepository(scm)) {
    updateGitlabCommitStatus name: 'Jenkins', state: 'failed'
}
```

Versioning
----------
This project adheres to [Semantic Versioning](http://semver.org/).

Contributing
------------
Contributions to this project are more than welcome.

License
-------
This project is released under the MIT license.

---
Made with love, coffee and fun by the [Moxio](https://www.moxio.com) team from
Delft, The Netherlands. Interested in joining our awesome team? Check out our
[vacancies](https://werkenbij.moxio.com/) (in Dutch).
