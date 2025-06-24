pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "lakshmikanth79/weather-app"
    }

    stages {
        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''#!/bin/bash
docker build -t $DOCKER_IMAGE .
echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
docker push $DOCKER_IMAGE
'''
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                sh '''
cd ansible
ansible-playbook -i inventory.ini deploy-weatherapp.yml
'''
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}

