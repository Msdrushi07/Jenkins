pipeline {
    agent any

    tools {
        jdk 'java21'           // Name of JDK you configured in Jenkins
        maven 'maven3'        // Name of Maven you configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'git-credentials',
                    url: 'https://github.com/Msdrushi07/Jenkins.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') {
                    bat 'mvn sonar:sonar -Dsonar.projectKey=jenkins-demo -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqa_16c37050dd77122f93efe35805c0fb17c67b2729'
                }
            }
        }

        stage('Package') {
            steps {
                 bat 'mvn clean package -DskipTests'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
