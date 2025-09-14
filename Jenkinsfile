pipeline {
    agent any

    tools {
        jdk 'java21'           // Name of JDK you configured in Jenkins
        maven 'maven3'        // Name of Maven you configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'maser', url: 'https://github.com/Msdrushi07/Jenkins.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
   
   
   