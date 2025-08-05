pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChakruJadhav123/Cloud-Employee-And-Bank.git'
            }
        }

        stage('Build') {
            steps {
                sh '''
                mkdir -p bin
                javac -d bin src/com/project//*.java
                '''
            }
        }

        stage('Package') {
            steps {
                sh '''
                jar cvf CloudEMS-Banking.jar -C bin .
                '''
            }
        }
    }
}
