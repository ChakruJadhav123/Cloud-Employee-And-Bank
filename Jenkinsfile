pipeline {
    agent any

    tools {
        // Ensure 'JDK 11' and 'Maven 3.8.1' are configured in Jenkins' Global Tool Configuration
        jdk 'JDK 11'   // Replace with the name of your JDK installation in Jenkins
        maven 'Maven 3.8.1' // Replace with the name of your Maven installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Jenkins automatically checks out the code when using "Pipeline script from SCM"
                // but you can explicitly define it if needed, or just keep a placeholder.
                git branch: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git'
                echo 'Code checked out from Git.'
            }
        }
        
        stage('Build and Test') {
            steps {
                // Use Maven to clean, compile, and package the project, including running tests.
                // 'mvn clean package' will handle compilation, dependency resolution, and packaging.
                sh 'mvn clean package'
            }
        }

        // Optional: Add a stage for deploying or archiving artifacts
        stage('Archive Artifacts') {
            steps {
                // Assuming your 'mvn clean package' creates a JAR/WAR in the 'target' directory
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                // Or if it creates a WAR: archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
    }
    
    post {
        // Post-build actions for notification and cleanup
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build successful! ✅'
        }
        failure {
            echo 'Build failed. ❌ Check console output for errors.'
        }
        unstable {
            echo 'Build was unstable, possibly due to failing tests. ⚠️'
        }
        cleanup {
            // Clean up workspace after build
            deleteDir()
        }
    }
}
