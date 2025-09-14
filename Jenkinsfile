pipeline {
    agent any

    tools {
        jdk 'java21'           // Name of JDK you configured in Jenkins
        maven 'maven3'        // Name of Maven you configured in Jenkins
    }
    
    environment {
        DOCKER_IMAGE = "msdrushi07/jenkins-demo:latest" // Change with your DockerHub repo
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
        
        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }
        
         stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    script {
                        bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                        bat "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Stop and remove old container if running
                    bat 'docker rm -f jenkins-demo-container || echo "No container to remove"'
                    // Run new container
                    bat "docker run -d -p 8080:9090 --name jenkins-demo-container ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        }
        }
        
        
      
   
