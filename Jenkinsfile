// Define the entire pipeline
pipeline {
    // Agent specifies where the pipeline will run. 'any' means any available agent.
    agent any

    // Options for the pipeline, like adding timestamps to console output.
    options {
        // timestamps() adds a timestamp to each line of the console output, useful for debugging.
        timestamps()
    }

    // Tools section defines the JDK and Maven installations to use.
    // The names 'jdk-17' and 'maven-3.8' MUST exactly match the names configured
    // in Jenkins' Global Tool Configuration (Manage Jenkins -> Global Tool Configuration).
    tools {
        jdk 'jdk-17'   // Use the name configured in Jenkins for your JDK 17 installation
        maven 'maven-3.8' // Use the name configured in Jenkins for your Maven 3.8 installation
    }

    // Stages define the logical steps of your pipeline.
    stages {
        // Stage for checking out the source code from Git.
        stage('Checkout') {
            steps {
                // The 'git' step checks out the specified branch from the URL.
                // Jenkins automatically handles this when 'Pipeline script from SCM' is used,
                // but explicitly defining it here ensures clarity and allows for more options.
                git branch: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git'
                echo 'Source code checked out successfully from Git.'
            }
        }

        // Stage for building and testing the Maven project.
        stage('Build and Test') {
            steps {
                // The 'sh' step executes shell commands.
                // 'mvn clean package' is a standard Maven command that:
                // - 'clean': Deletes the target directory.
                // - 'package': Compiles the source code, runs tests, and packages the compiled code
                //              into a JAR/WAR file in the 'target' directory.
                // If this command fails (e.g., compilation errors, failing tests),
                // the stage will be marked as failed, and the pipeline will stop.
                sh 'mvn clean package'
                echo 'Maven build and tests completed.'
            }
        }

        // Optional stage for archiving the build artifacts (e.g., the JAR/WAR file).
        stage('Archive Artifacts') {
            steps {
                // 'archiveArtifacts' makes the specified files available for download from the build page.
                // We assume 'mvn clean package' creates a JAR file in the 'target' directory.
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                echo 'Build artifacts archived.'
            }
        }
    }

    // Post-build actions that run after all stages have completed, regardless of success or failure.
    post {
        // 'always' block executes regardless of the build result.
        always {
            echo 'Pipeline execution finished.'
            // Clean up the workspace to free up disk space.
            cleanup {
                deleteDir()
            }
        }
        // 'success' block executes only if the pipeline completes successfully.
        success {
            echo 'Build successful! ✅ All stages passed.'
        }
        // 'failure' block executes only if the pipeline fails at any stage.
        failure {
            echo 'Build failed. ❌ Please check the console output for errors.'
        }
        // 'unstable' block executes if the build was successful but some tests failed.
        unstable {
            echo 'Build was unstable. ⚠️ Some tests might have failed.'
        }
    }
}
