pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven-3.8'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git', branch: 'main'
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
               
            }
        }
    }
}
