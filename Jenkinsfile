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
                script {
                    sh """
                        docker build -t $DOCKER_IMAGE .
                        echo "$DOCKER_HUB_PASSWORD" | docker login -u "$DOCKER_HUB_USERNAME" --password-stdin
                        docker push $DOCKER_IMAGE
                    """
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

