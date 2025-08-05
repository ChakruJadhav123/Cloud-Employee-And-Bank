pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven-3.8'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // For now, just echo. Later we can add script to run app on EC2
            }
        }
    }
}
