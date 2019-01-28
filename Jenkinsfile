// Script to build application under Jenkins
// Use with 'Pipeline script from SCM'
// Includes running unit tests and generating javadocs
pipeline {
    agent {
        docker { 
            image 'clml/pett-build:latest'
        }
    }
    
    stages {
        stage ('Build JAR') {
            steps {
                git 'https://github.com/cwld/pett_app.git'
                sh './gradlew jar'
                archiveArtifacts 'build/libs/pett-*.jar'
            }
        }
        stage ('Run unit tests') {
            steps {
                script {
                    // Always archive the tests on failure so we can see which ones failed
                    try {
                        sh './gradlew test --info'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                    } finally {
                        sh 'tar -C build/test-results -czvf pett_junit_test_results.tar.gz test/'
                    }
                }
                archiveArtifacts 'pett_junit_test_results.tar.gz'
            }
        }
        stage ('Generate documentation') {
            steps {
                sh './gradlew javadoc'
                sh 'tar -C build/docs -czvf pett_docs.tar.gz javadoc/'
                archiveArtifacts 'pett_docs.tar.gz'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
