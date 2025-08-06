pipeline {
    agent any

    options {
        timestamps()   // Adds timestamps to logs
    }

    tools {
        jdk 'jdk-17'       // Name as configured in Jenkins Global Tool Configuration
        maven 'maven-3.8'  // Name as configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git'
                echo 'Source code checked out successfully.'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
                echo 'Build and tests completed.'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                echo 'Artifacts archived successfully.'
            }
        }

        // Deployment Stage (optional - only if you want automatic deployment)
        stage('Deploy') {
            steps {
                echo 'Deploying application (dummy step - replace with real deployment commands)...'
                // Example: scp JAR to EC2 or run java -jar command remotely
                // sh 'scp target/*.jar ec2-user@<EC2-IP>:/path/to/deploy'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
            deleteDir() // Clean workspace
        }
        success {
            echo 'Build successful! ✅'
        }
        failure {
            echo 'Build failed ❌. Check logs.'
        }
    }
}
